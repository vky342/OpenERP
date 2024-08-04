package com.vky342.openerp.data.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Repositories.AccountRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Add_Account_VM @Inject constructor( private val accountRepo: AccountRepo): ViewModel(){

    override fun onCleared() {
        Log.d("Account_VM", "ended")
        super.onCleared()
    }

    fun Status_A(){
        Log.d("Account_VM", "on Add_Account_VM")
    }


}