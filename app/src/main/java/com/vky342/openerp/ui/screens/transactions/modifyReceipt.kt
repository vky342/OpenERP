package com.vky342.openerp.ui.screens.transactions

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Receipt
import com.vky342.openerp.data.ViewModels.transaction.Add_Receipt_Vm
import com.vky342.openerp.ui.screens.ACCOUNTS.Save_button
import com.vky342.openerp.ui.screens.ACCOUNTS.account_search_bar_for_edit_account
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.sale_button_box_color
import com.vky342.openerp.utility.parseStrictDouble

@Composable
fun ModifyReceiptScreen(viewModel : Add_Receipt_Vm = hiltViewModel()){
    val oldReceipt = viewModel.oldReceipt.observeAsState(Receipt(0,"",0.0,0))
    val oldLedger = viewModel.oldLedger.observeAsState(Ledger(0,0.0,""))

    val newReceipt = remember { mutableStateOf(Receipt(0,"",0.0,0)) }
    val newAccountName = remember { mutableStateOf("") }
    val context: Context = LocalContext.current
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    val is_bill_selected = remember { mutableStateOf(false) }

    val bill_id = remember { mutableStateOf("") }

    var selectedOptionText by remember { mutableStateOf("") }

    val ID = remember { derivedStateOf{ newReceipt.value.receiptId } }

    val options = viewModel.old_Account_list.value

    val expanded = remember { mutableStateOf(false) }

    val select_account_selected_enalbled = remember { mutableStateOf(false) }

    var amount by remember { mutableStateOf("") }

    val selectedDate = remember { mutableStateOf("") }

    val filteringOptions = options.filter {
        it.name.contains(selectedOptionText, ignoreCase = true) || it.address.contains(selectedOptionText, ignoreCase = true) || it.contact.contains(selectedOptionText, ignoreCase = true)
    }

    LaunchedEffect(oldReceipt.value) {
        if (oldReceipt.value != Receipt(0, "", 0.0, 0)) {
            newReceipt.value = oldReceipt.value
            amount = oldReceipt.value.receiptAmount.toString()
            selectedDate.value = oldReceipt.value.receiptDate
            is_bill_selected.value = true
            select_account_selected_enalbled.value = false
        }
    }

    LaunchedEffect(oldLedger.value) {
        if (oldLedger.value != Ledger(0, 0.0, "")) {
            newAccountName.value = oldLedger.value.accountName
            selectedOptionText = oldLedger.value.accountName
        }
    }

    if (!is_bill_selected.value) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = background_color)

        )
        {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = background_color)
                    .verticalScroll(rememberScrollState())
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Select bill",
                        color = New_account_title_color,
                        fontSize = 32.sp,
                        modifier = Modifier
                            .align(
                                Alignment.CenterStart
                            )
                            .padding(horizontal = sidePadding.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .padding(horizontal = sidePadding.dp)
                    ) {
                        billSearchbar(
                            modifier = Modifier,
                            current_value = bill_id.value.toString(),
                            label = "Invoice ID...",
                            onVC =
                                { newValue ->
                                    // Allow empty input or valid numeric string
                                    if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                                        bill_id.value = newValue
                                    }
                                },
                            onTrailingIconClick = {
                                try{
                                    viewModel.getReceiptByID(bill_id.value.toInt()) { success ->
                                        if (success) {
                                            Toast.makeText(context, "bill found", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Toast.makeText(context, "No bill found", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }catch (e : Exception){
                                    Toast.makeText(context, "Invalid search", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(vertical = 150.dp)
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(color = sale_button_box_color)
                        .align(Alignment.CenterHorizontally),
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            Icons.Default.Refresh, contentDescription = "",
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                                .size(25.dp)
                                .align(Alignment.CenterVertically),
                            tint = Color.White
                        )
                        Text(
                            "Load recent receipt...",
                            color = Color.White,
                            fontSize = 22.sp,
                            modifier = Modifier
                                .align(
                                    Alignment.CenterVertically
                                )
                                .clickable {
                                    viewModel.getRecentReceipt { success ->
                                        if (success) {
                                            Toast.makeText(
                                                context,
                                                "bill found",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "No recent bill found",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                        )
                    }
                }
            }
        }
    } else {

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
                    Text(text = "Receipt : " + ID.value,
                        color = New_account_title_color,
                        fontSize = 29.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(horizontal = sidePadding.dp))
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
                        newAccountName.value = ""
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

                // amount
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(70.dp)
                ) {
                    form_fields(trailing_icon = Icons.Default.Clear,keyboardOptions = KeyboardOptions(autoCorrect = false, keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
                        enabled = !select_account_selected_enalbled.value,onVc = {amount = it},value = amount,icon = Icons.Default.Create,label = "Amount",modifier = Modifier
                            .padding(horizontal = sidePadding.dp)
                            .align(
                                Alignment.CenterStart
                            ))
                }
                // save button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(70.dp)

                ) {
                    Save_button(enabled = !select_account_selected_enalbled.value && selectedDate.value != "" && amount != "" ,onClick = {

                        val amountTobePassed = try {
                            parseStrictDouble(amount)
                        }catch (e : Exception){
                            amount = ""
                            Toast.makeText(context, "Invalid amount", Toast.LENGTH_SHORT).show()
                            0.0
                        }
                        if(newAccountName.value == "" || selectedDate.value == ""){
                            Toast.makeText(context, "field empty!", Toast.LENGTH_SHORT).show()
                        }else{
                            if (amountTobePassed != 0.0){
                                viewModel.updateReceipt(name = newAccountName.value, Date = selectedDate.value, amount = amount.toDouble() )
                                newReceipt.value = Receipt(0,"",0.0,0)
                                amount = ""
                                selectedDate.value = ""
                                selectedOptionText = ""
                                newAccountName.value = ""
                                select_account_selected_enalbled.value = true
                                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                is_bill_selected.value = false
                                bill_id.value = ""
                            }
                            amount = ""
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
                        .padding(horizontal = (sidePadding / 2).dp)
                        .align(Alignment.TopCenter)
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(10f))
                        .background(color = Color.White, shape = RoundedCornerShape(10f))
                ){
                    items (filteringOptions){ account ->
                        Text(text = account.name + " " + "(" +account.type+ ")",
                            fontSize = 18.sp,
                            fontWeight = FontWeight(350),
                            modifier = Modifier
                                .padding(vertical = 1.dp, horizontal = 2.dp)
                                .fillMaxWidth()
                                .clickable {
                                    newAccountName.value = account.name
                                    selectedOptionText =
                                        account.name + " " + "(" + account.type + ")"
                                    select_account_selected_enalbled.value = false
                                    expanded.value = false
                                    Toast.makeText(context, "Account selected", Toast.LENGTH_SHORT)
                                        .show()
                                })
                    }
                }
            }

        }
    }

}