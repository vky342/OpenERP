package com.vky342.openerp.ui.screens.ACCOUNTS


import android.content.Context
import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.ViewModels.Account.Add_Account_VM
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color


@Composable
fun Add_Account_Screen( viewModel: Add_Account_VM = hiltViewModel()){

    val context : Context = LocalContext.current

    val name = remember{ mutableStateOf("")}

    var address by remember {
        mutableStateOf("")
    }
    var contact by remember {
        mutableStateOf("")
    }

    var type by remember {
        mutableStateOf("customer")
    }

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
                Text(text = "New account", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            // Account Type Selector
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                account_registration_type_selector(selected_type = type, customer_click = {type = "customer"}, supplier_click = {type = "supplier"}, regular_click = {type = "regular"})
            }

            // Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(icon = Icons.Outlined.Person, onVc = {name.value = it},value = name.value,label = "Name",modifier = Modifier.padding(horizontal = sidePadding.dp).align(Alignment.CenterStart))
            }

            // Address
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                form_fields(icon = Icons.Outlined.LocationOn, onVc = {address = it}, value = address,label = "Address",modifier = Modifier.padding(horizontal = sidePadding.dp).align(Alignment.CenterStart))
            }
            // Contact
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                form_fields(icon = Icons.Outlined.Call, value = contact, onVc = {contact = it},label = "Contact",modifier = Modifier.padding(horizontal = sidePadding.dp).align(Alignment.CenterStart))
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                Save_button(modifier = Modifier.align(Alignment.Center), onClick = {
                    viewModel.save_account( name = name.value,address = address,contact=contact,type= type )

                    if(name.value != "" && address != "" && contact != ""){
                        Toast.makeText(context,"Account saved", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context,"Please fill the form", Toast.LENGTH_SHORT).show()
                    }

                    name.value = ""
                    address = ""
                    contact = ""
                    type = "customer" }, label = "Save")
            }
        }
    }
}

