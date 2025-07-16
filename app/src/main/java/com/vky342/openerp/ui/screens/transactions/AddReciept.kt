package com.vky342.openerp.ui.screens.transactions

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.ViewModels.transaction.Add_Receipt_Vm
import com.vky342.openerp.ui.screens.ACCOUNTS.Save_button
import com.vky342.openerp.ui.screens.ACCOUNTS.account_search_bar_for_edit_account
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.utility.getTodayDate
import com.vky342.openerp.utility.parseStrictDouble

@Composable
fun AddReceiptScreen(viewModel : Add_Receipt_Vm = hiltViewModel()){
    val context : Context = LocalContext.current

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    var old_Account by remember { mutableStateOf(Account(0,"","","","")) }

    var ID by viewModel.receiptID

    var selectedOptionText by remember { mutableStateOf("") }

    val options = viewModel.old_Account_list.value

    val expanded = remember { mutableStateOf(false) }

    val select_account_selected_enalbled = remember { mutableStateOf(true) }

    var amount by remember { mutableStateOf("") }

    val selectedDate = remember { mutableStateOf(getTodayDate()) }

    val filteringOptions = options.filter {
        it.name.contains(selectedOptionText, ignoreCase = true) || it.address.contains(selectedOptionText, ignoreCase = true) || it.contact.contains(selectedOptionText, ignoreCase = true)
    }

    LaunchedEffect(viewModel.receiptID.value) {
        ID = viewModel.receiptID.value
    }

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
                Text(text = "Receipt : " + if(ID == 0) 1 else ID+ 1, color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            // Account search bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {

                account_search_bar_for_edit_account(onReset = {
                    select_account_selected_enalbled.value = true
                    old_Account = Account(0,"","","","")
                    selectedOptionText = ""
                    viewModel.balance.value = 0.0
                    Toast.makeText(context,"Select an account please", Toast.LENGTH_SHORT).show()
                },
                    enabled = select_account_selected_enalbled.value,modifier = Modifier
                    ,current_value = selectedOptionText
                    ,onVc = {
                        selectedOptionText = it
                        expanded.value = true
                    })
            }

            // Date
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = sidePadding.dp)
                    .height(70.dp)
            ) {

                DatePickerComposable(
                    enabled = !select_account_selected_enalbled.value,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxHeight(),
                    label = "Date",
                    value = selectedDate.value,
                    onDateSelected = { selectedDate.value = it })


            }

            // balance
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(50.dp)
            ) {
                Text(text = "Old balance : "+ kotlin.math.abs(viewModel.balance.value) + if (viewModel.balance.value < 0.0) " Dr" else " Cr", color = New_account_title_color,fontSize = 24.sp, modifier = Modifier.align(Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            // amount
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(trailing_icon = Icons.Default.Clear,keyboardOptions = KeyboardOptions(autoCorrect = false, keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
                    enabled = old_Account != Account(0,"","","",""),onVc = {amount = it},value = amount,icon = Icons.Default.Create,label = "Amount",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                        Alignment.CenterStart))
            }
            // save button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                Save_button(enabled = old_Account != Account(0,"","","","") && selectedDate.value != "" && amount != "",onClick = {
                    val amountTobePassed = try {
                        parseStrictDouble(amount)
                    }catch (e : Exception){
                        amount = ""
                        Toast.makeText(context, "Invalid amount", Toast.LENGTH_SHORT).show()
                        0.0
                    }
                    if (amountTobePassed != 0.0){
                        if(viewModel.AddReceipt(old_Account.name, Date = selectedDate.value, amount = amount.toDouble())){
                            Toast.makeText(context,"Payment saved", Toast.LENGTH_SHORT).show()
                        }else{Toast.makeText(context,"Payment Invalid", Toast.LENGTH_SHORT).show()}
                        old_Account = Account(0,"","","","")
                        selectedOptionText = ""
                        amount = ""
                        selectedDate.value = ""
                        viewModel.balance.value = 0.0
                        select_account_selected_enalbled.value = true
                    }
                }
                    ,modifier = Modifier.align(Alignment.Center), label = "Save")
            }
        }

        if (expanded.value) {

            BackHandler (enabled = expanded.value) {
                expanded.value = false
            }

            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp)
                    .padding(top = 150.dp)
                    .padding(horizontal = (sidePadding/2).dp)
                    .align(Alignment.TopCenter)
                    .shadow(elevation = 4.dp,shape = RoundedCornerShape(10f))
                    .background(color = Color.White, shape = RoundedCornerShape(10f))
            ){
                items (filteringOptions){ account ->
                    Text(text = account.name + " " + "(" +account.type+ ")",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(350),
                        modifier = Modifier
                            .padding(vertical = 1.dp, horizontal = 2.dp)
                            .fillMaxWidth()
                            .clickable{
                                old_Account = account
                                selectedOptionText = account.name + " " + "(" +account.type+ ")"
                                select_account_selected_enalbled.value = false
                                expanded.value= false
                                viewModel.load_balance(account.name)
                                Toast.makeText(context,"Account selected", Toast.LENGTH_SHORT).show()
                            }
                    )
                }
            }
        }

    }
}