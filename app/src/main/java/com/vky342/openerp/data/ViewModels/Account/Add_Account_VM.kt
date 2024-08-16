package com.vky342.openerp.data.ViewModels.Account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Repositories.AccountRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Add_Account_VM @Inject constructor( private val accountRepo: AccountRepo): ViewModel(){


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

    fun save_account(name :  String, address: String, contact: String){
        if(validateInput(name = name, address = address, contact = contact)){
            viewModelScope.launch (Dispatchers.IO) { accountRepo.insert_Account(name, address, contact)}
        }else{
            Log.e("47 Add-account-Vm", "ERROR validating")
        }
    }



}
