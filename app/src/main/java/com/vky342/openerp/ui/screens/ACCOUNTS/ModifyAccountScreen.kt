package com.vky342.openerp.ui.screens.ACCOUNTS

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
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
fun ModifyAccountScreen(){
    val height = LocalConfiguration.current.run { screenHeightDp.dp  }
    val topPadding = height.value * 0.1

    var name by remember {
        mutableStateOf("")
    }

    var newname by remember {
        mutableStateOf("")
    }

    var newaddress by remember {
        mutableStateOf("")
    }

    var newcontact by remember {
        mutableStateOf("")
    }

    var newstartingbalance by remember {
        mutableStateOf("")
    }

    val scrollState  = rememberScrollState()

    Column(modifier = Modifier
        .background(color = Greye)
        .fillMaxSize()
        .padding(top = topPadding.dp, bottom = topPadding.dp)
        .verticalScroll(scrollState)
    ) {
        Text(color = Color.LightGray,text = "Modify Account", modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 10.dp), fontSize = 26.sp)

        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),label = { Text(text = "Name")},value = name, onValueChange = { name = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            trailingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "search",
                modifier = Modifier.clickable {

                    // CALL back for Account to ViewModel

                })}
        )

        Text(color = Color.LightGray,text = "New Values", modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 22.sp)

        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),label = { Text(text = "New name")},value = newname, onValueChange = { newname = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next))

        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),label = { Text(text = "New address")},value = newaddress, onValueChange = { newaddress = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next))

        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),label = { Text(text = "New contact")},value = newcontact, onValueChange = { newcontact = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next))

        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),label = { Text(text = "new starting balance")},value = newstartingbalance, onValueChange = { newstartingbalance = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done))

        Button(colors = ButtonColors(containerColor = Color.LightGray, disabledContainerColor = Color.LightGray, contentColor = Greye, disabledContentColor = Greye),modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally)
            .wrapContentSize(),onClick = {
            // View Model event call back for updating account

            Log.d("MODIFY", "Account MOdified")
        }) {
            Text(text = "Save", fontSize = 24.sp)

        }

    }
}