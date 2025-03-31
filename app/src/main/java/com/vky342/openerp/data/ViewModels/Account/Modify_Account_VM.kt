package com.vky342.openerp.data.ViewModels.Account

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Repositories.AccountRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class modify_Account_vm @Inject constructor(private val accountRepo: AccountRepo) : ViewModel(){

    var account_to_modify : Account = Account(0,"","","", "")
    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    init {
        viewModelScope.launch {
            accountRepo.get_every_Account().collect(){
                newData -> old_Account_list.value = newData
                Log.d("STATUS", "collected accounts line 24 -vm")
            }
        }
    }


    private fun validateInput(name: String, address: String, contact: String): Boolean {
        val nameRegex = "^[a-zA-Z\\s]+$".toRegex()
        if (name.isEmpty() || !name.matches(nameRegex)) {
            Log.d("INVALID", "invalid input for name")
            return false
        }

        if (address.isEmpty()) {
            Log.d("INVALID", "invalid input for address")
            return false
        }

        val contactRegex = "^[0-9]{10}$".toRegex()
        if (!contact.matches(contactRegex)) {
            Log.d("INVALID", "invalid input for contact")
            return false
        }

        return true
    }

    private fun update_Account_private(old_Account : Account, new_Account : Account ){
        viewModelScope.launch (Dispatchers.IO) {
            accountRepo.update_Account(old_Account, new_Account)
            Log.d("status", "EXECUTED line 38")
        }
    }

    fun update_Account( new_Account: Account) : Boolean{

        if(validateInput(account_to_modify.name, account_to_modify.address, account_to_modify.contact.toString()) && validateInput(new_Account.name, new_Account.address, new_Account.contact.toString())){
            update_Account_private(account_to_modify, new_Account)
            return true
        }
        else{
            Log.e("ERROR","error validating new account")
            return false
        }
    }

    private fun get_Account_by_name(name : String) : Account?{

        for (account in old_Account_list.value){
            if (account.name == name){
                account_to_modify = account
                return account
            }
        }
        Log.d("NOT FOUND", "line 74")
        return null
    }

    fun search(name : String){
        get_Account_by_name(name)
    }

}