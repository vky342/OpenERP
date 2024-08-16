package com.vky342.openerp.ui.screens.ACCOUNTS

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.ViewModels.Account.modify_Account_vm


@Composable
fun ModifyAccountScreen( viewModel: modify_Account_vm = hiltViewModel() ){

    val old_Account = Account(0,"","",0)
    Log.d("OLD ACCOUNT 18" , old_Account.toString())
    
    val new_Account : MutableState<Account> = remember{mutableStateOf(Account(0,"","",0))}
    
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()){
            Text(text = old_Account.toString(), modifier = Modifier.align(Alignment.Center))

            Button(modifier = Modifier.align(Alignment.CenterStart),onClick = {

                new_Account.value = old_Account.copy(name = "kunal")

                viewModel.update_Account(new_Account.value)}) {
                Text(text = "update")
            }
        }
    }
}