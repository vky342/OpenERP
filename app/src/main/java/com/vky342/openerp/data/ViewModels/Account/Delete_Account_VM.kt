package com.vky342.openerp.data.ViewModels.Account

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Repositories.AccountRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class delete_Account_vm @Inject constructor( private val accountRepo: AccountRepo ) : ViewModel() {

    var account_to_delete : Account? = null
    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    init {
        viewModelScope.launch {
            accountRepo.get_every_Account().collect(){
                    newData -> old_Account_list.value = newData
                    Log.d("STATUS", "collected accounts line 24 -vm")
            }
        }
    }

    private fun validateInput(name: String): Boolean {
        val nameRegex = "^[a-zA-Z\\s]+$".toRegex()
        if (name.isEmpty() || !name.matches(nameRegex)) {
            Log.d("INVALID", "invalid input for name")
            return false
        }
        return true
    }


    private fun delete_Account(account: Account) {
        viewModelScope.launch {
            accountRepo.delete_Account(account)
        }
    }

    private fun get_Account_by_name(name : String) : Account?{

        if (validateInput(name)){
            for (account in old_Account_list.value){
                if (account.name == name){
                    account_to_delete = account
                    return account
                }
            }
            Log.d("NOT FOUND", "line 54")
            return null
        }


        Log.d("Validation error", "line 60")
        return null

    }

    fun search(name : String){
        get_Account_by_name(name)
    }

    fun deleteAccount() {

        if (account_to_delete != null){
            viewModelScope.launch (Dispatchers.IO) {
                accountRepo.delete_Account(account_to_delete!!)
            }
        }

        Log.d("Not found", "line 77 account to delete is null")

    }

}