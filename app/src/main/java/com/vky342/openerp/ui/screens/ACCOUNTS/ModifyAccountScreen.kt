package com.vky342.openerp.ui.screens.ACCOUNTS

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.ViewModels.Account.modify_Account_vm
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color


@Composable
fun ModifyAccountScreen( viewModel: modify_Account_vm = hiltViewModel() ){

    val old_Account = Account(0,"","","", "")
    Log.d("OLD ACCOUNT 18" , old_Account.toString())
    
    val new_Account : MutableState<Account> = remember{mutableStateOf(Account(0,"","","", ""))}

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)

    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp)
                .background(color = background_color)
                .verticalScroll(rememberScrollState())
        ) {
            // Title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(50.dp)
            ) {
                Text(text = "Edit account", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(
                    Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            // Account Type Selector
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                account_search_bar_for_edit_account()
            }

            // Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(icon = Icons.Outlined.Person,label = "Name",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }

            // Address
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                form_fields(icon = Icons.Outlined.LocationOn,label = "Address",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }
            // Contact
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                form_fields(icon = Icons.Outlined.Call,label = "Contact",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }
            // save button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                Save_button(modifier = Modifier.align(Alignment.Center), label = "Save")
            }
        }
    }
}