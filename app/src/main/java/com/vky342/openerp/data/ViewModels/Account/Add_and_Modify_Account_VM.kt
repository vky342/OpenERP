package com.vky342.openerp.data.ViewModels.Account

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Repositories.AccountRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Add_and_Modify_Account_VM @Inject constructor( private val accountRepo: AccountRepo): ViewModel(){

}
