package com.vky342.openerp.ui.screens.ACCOUNTS

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vky342.openerp.ui.theme.Greye

@Preview
@Composable
fun AddA_Account_Screen(){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1
    var name by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }
    var contact by remember {
        mutableStateOf("")
    }
    var starting_balance by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier
        .background(color = Greye)
        .fillMaxSize()
        .padding(top = topPadding.dp)
    ) {
        Text(color = Color.LightGray,fontSize = 26.sp,text = "ADD ACCOUNT", modifier = Modifier.align(Alignment.CenterHorizontally))
        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),label = { Text(text = "Name")},value = name, onValueChange = { name = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next))

        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),label = { Text(text = "Address")},value = address, onValueChange = { address = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),label = { Text(text = "Contact")},value = contact, onValueChange = { contact = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),label = { Text(text = "Starting Balance")},value = starting_balance, onValueChange = { starting_balance = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        Button(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally)
            .wrapContentSize(),onClick = {
                // View Model event call back for saving account
                Log.d("SAVE", "Account - $name, $address, $contact, $starting_balance")
                name = ""
                address = ""
                contact = ""
                starting_balance = ""
        }) {
            Text(text = "Save", fontSize = 24.sp)
            
        }
    }
}