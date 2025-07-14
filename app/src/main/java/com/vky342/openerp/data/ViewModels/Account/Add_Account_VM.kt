package com.vky342.openerp.data.ViewModels.Account

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
class Add_Account_VM @Inject constructor( private val accountRepo: AccountRepo): ViewModel(){

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

        if (old_Account_list.value.any{it.name == name}){
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

    fun save_account(name :  String, address: String, contact: String, type : String) : Boolean {
        if(validateInput(name = name, address = address, contact = contact)){
            viewModelScope.launch (Dispatchers.IO) {
                accountRepo.insert_Account(name, address, contact, type)
            }
            return true

        }else{
            Log.e("47 Add-account-Vm", "ERROR validating")
            return false
        }
    }



}
