package com.vky342.openerp.ui.screens.ACCOUNTS


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.ViewModels.Add_Account_VM
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.GreyeHome
import com.vky342.openerp.ui.theme.ReceiptIconPin

@Preview
@Composable
fun AddA_Account_Screen( viewModel: Add_Account_VM = hiltViewModel()){


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

    var balanceType by remember {
        mutableStateOf(false) // CRED and true means // DEBIT
    }

    Column(modifier = Modifier
        .background(color = GreyeHome)
        .fillMaxSize()
        .padding(top = topPadding.dp, bottom = topPadding.dp)
    ) {
        Text(color = Color.LightGray,fontSize = 26.sp,text = "ADD ACCOUNT", modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 10.dp))
        Field_for_Add_Account_Card(label = "Name", field_value = name, onValueChanged = {name = it})

        Field_for_Add_Account_Card(label = "Address", field_value = address) { address = it}

        Field_for_Add_Account_Card(label = "Contact", field_value = contact) {contact = it}

        Field_for_Add_Account_Card(label = "Starting Balance", field_value = starting_balance) {starting_balance = it}

        Switch_for_BalanceType(defaultPos = balanceType) { balanceType = it}

        Button(colors = ButtonColors(containerColor = Color.LightGray, disabledContainerColor = Color.LightGray, contentColor = Greye, disabledContentColor = Greye),modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally)
            .wrapContentSize(),onClick = {
                // View Model event call back for saving account
                val starting_balance_Type = if(balanceType == false){"DEBIT"}else{"CREDIT"}

                viewModel.add_account(Account(name,address,contact.toInt(),starting_balance.toInt(),starting_balance_Type))
                name = ""
                address = ""
                contact = ""
                starting_balance = ""
                balanceType = false
        }) {
            Text(text = "Save", fontSize = 24.sp)
            
        }
    }
}



@Composable
fun Field_for_Add_Account_Card(label : String , field_value : String, onValueChanged : (String) -> Unit){
    val width = LocalConfiguration.current.run { screenWidthDp.dp }
    val cardWidth = width.value * 1
    val sidePadding = 16.dp

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .width(cardWidth.dp)
            .padding(horizontal = sidePadding)
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Greye),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(2.dp)
        ) {
            TextField(keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = field_value,
                onValueChange = { onValueChanged(it) },
                textStyle = TextStyle(fontSize = 16.sp),
                label = { Text(text = label, fontSize = 14.sp) },
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp, vertical = 7.dp),
                colors = TextFieldDefaults.colors().copy(cursorColor = ReceiptIconPin,focusedContainerColor = Greye,unfocusedContainerColor = GreyeHome, focusedLabelColor = ReceiptIconPin, unfocusedLabelColor = ReceiptIconPin, focusedTextColor = Color.White, unfocusedTextColor = Color.White)
            )
        }
    }
}

@Composable
fun Switch_for_BalanceType(defaultPos : Boolean, onValueChanged: (Boolean) -> Unit){
    val width = LocalConfiguration.current.run { screenWidthDp.dp }
    val cardWidth = width.value * 1
    val sidePadding = 16.dp

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .width(cardWidth.dp)
            .padding(horizontal = sidePadding)
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Greye),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(2.dp)
        ) {
            Row (modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(Alignment.Center)){
                Text(text = "DEBIT", modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterVertically))

                Switch(colors = SwitchDefaults.colors(),checked = defaultPos, onCheckedChange = { onValueChanged(it)})

                Text(text = "CREDIT", modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterVertically))

            }
        }
    }
}