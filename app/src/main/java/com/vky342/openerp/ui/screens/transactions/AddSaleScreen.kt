package com.vky342.openerp.ui.screens.transactions

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Entities.Purcahase
import com.vky342.openerp.data.Entities.PurchaseEntry
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.ViewModels.transaction.Add_sale_Vm
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.Typography
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.title_color
import com.vky342.openerp.ui.theme.var_amount_row_colour
import com.vky342.openerp.utility.getTodayDate
import java.util.UUID

@Composable
fun AddSaleScreen( viewModel: Add_sale_Vm = hiltViewModel()){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08
    val context: Context = LocalContext.current

    val options = viewModel.old_Account_list.value
    val item_options = viewModel.all_items_in_inventory

    val item_fill_popUp_status = remember { mutableStateOf(false) }

    var ID by viewModel.saleID

    val selectedAccount = remember { mutableStateOf((Account(0, "", "", "", ""))) }

    val payment_mode = remember { mutableStateOf("") }

    val selectedDate = remember { mutableStateOf(getTodayDate()) }

    var selectedAccountText by remember { mutableStateOf("") }

    // itemForm popUP
    val currentItem = remember { mutableStateOf(Item("",0.0,0.0,0)) }
    val fieldsEnabled = remember { mutableStateOf(false) }
    val itemNameEnabled = remember { mutableStateOf(true) }
    val selectedItemName = remember { mutableStateOf("") }
    val selectedItemPrice = remember { mutableStateOf("") }
    val selectedItemDiscount = remember { mutableStateOf("") }
    val selectedItemQuantity = remember { mutableStateOf("") }

    val itemsList = remember { mutableStateListOf<item_popup>() }

    val checkOutEnabled by remember { derivedStateOf { itemsList.isNotEmpty() } }
    val partyEnabled = remember { mutableStateOf(true) }
    val addItemEnabled = remember { mutableStateOf(false)}

    // purchase summary
    val totalItems by remember { derivedStateOf { itemsList.size } }
    val totalAmount = remember {derivedStateOf { itemsList.sumOf { it.totalAmount.value } } }

    // Items Summary
    val listState = rememberLazyListState()
    LaunchedEffect(itemsList.size) {
        listState.scrollToItem(0)
    }

    LaunchedEffect(viewModel.saleID.value) {
        ID = viewModel.saleID.value
    }

    //Suggestions
    val filteringOptions = options.filter {
        it.name.contains(selectedAccountText, ignoreCase = true) || it.address.contains(
            selectedAccountText,
            ignoreCase = true
        ) || it.contact.contains(selectedAccountText, ignoreCase = true)
    }
    val filtering_items_Options = item_options.value.filter {
        it.itemName.contains(selectedItemName.value, ignoreCase = true)
    }
    val expanded_item_name_suggestion = remember { mutableStateOf(false) }
    val expanded_account_suggestion = remember { mutableStateOf(false) }

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
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { expanded_account_suggestion.value = false },
                        onPress = { expanded_account_suggestion.value = false }
                    )
                }
        ) {

            // Title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(30.dp)
            ) {
                Text(
                    text = "New sale : " + if(ID == 0) 1 else ID+ 1,
                    color = New_account_title_color,
                    style = Typography.titleLarge,
                    modifier = Modifier
                        .align(
                            Alignment.CenterStart
                        )
                        .padding(horizontal = sidePadding.dp)
                )
            }
            // Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 5.dp)
                    .height(55.dp)
            ) {
                form_fields(
                    trailing_icon_enabled = if(selectedAccount == Account(0, "", "", "", "")) false else{true},
                    onTrailingIconClick = {
                        Log.d("Status", "on Trailing Icon Clicked")
                        addItemEnabled.value = false
                        selectedAccountText = ""
                        partyEnabled.value = true
                        payment_mode.value = ""
                        selectedAccount.value = Account(0, "", "", "", "")
                    }, enabled = partyEnabled.value,
                    onVc = {
                        selectedAccountText = it
                        expanded_account_suggestion.value = true
                    },
                    value = selectedAccountText,
                    icon = Icons.Outlined.Person,
                    trailing_icon = if (partyEnabled.value) Icons.Default.Search else Icons.Default.Lock,
                    label = "Party",
                    modifier = Modifier
                        .padding(horizontal = sidePadding.dp)
                        .align(Alignment.CenterStart)
                )
            }

            // Payment mode selector
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(horizontal = sidePadding.dp)
                    .fillMaxWidth()
                    .padding(vertical = 7.dp)
                    .height(60.dp)
            ) {

                payment_mode_type_selector(
                    enabled = !partyEnabled.value,
                    onCredit = { payment_mode.value = it },
                    onCash = { payment_mode.value = it },
                    payment_mode = payment_mode.value,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight()
                )


                DatePickerComposable(
                    enabled = !partyEnabled.value,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight(),
                    label = "Date",
                    value = selectedDate.value,
                    onDateSelected = { selectedDate.value = it })

            }

            // Sale Summary
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 25.dp)) {

                Text(
                    "S u m m a r y",
                    style = Typography.titleMedium, color = title_color,
                    modifier = Modifier
                        .padding(horizontal = sidePadding.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Variable_Amount_Row_2(totalAmount = totalAmount.value, totalItems = totalItems,modifier = Modifier.background(color = var_amount_row_colour).height(65.dp))

                Row (modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                    checkout_Strip(modifier = Modifier.weight(1f),enabled = checkOutEnabled, onClick = {

                        if (selectedDate.value != ""){
                            viewModel.add_sale(
                                name = selectedAccount.value.name,
                                sale = Sale(saleId = 0, saleDate = selectedDate.value, ledgerId = 0, saleAmount = totalAmount.value, saleType = payment_mode.value),
                                listOfEntry = itemsList.map { item ->
                                    SaleEntry(
                                        entryId = 0,
                                        entryQuantity = item.quantity,
                                        entryPrice = item.price,
                                        discount = item.disc,
                                        finalPrice = item.totalAmount.value,
                                        itemName = item.name,
                                        saleId = 0
                                    )
                                }
                            )
                            itemsList.clear()
                            selectedAccountText = ""
                            selectedAccount.value = Account(0,"","","","")
                            payment_mode.value = ""
                            partyEnabled.value = true
                            currentItem.value = Item("",0.0,0.0,0)
                            fieldsEnabled.value = false
                            itemNameEnabled.value = true
                            selectedItemName.value = ""
                            selectedItemPrice.value = ""
                            selectedItemDiscount.value = ""
                            selectedItemQuantity.value = ""
                        }

                    })

                    Add_button_Strip(modifier = Modifier.weight(1f),enabled =addItemEnabled.value,onClick = { item_fill_popUp_status.value = true })
                }


                // Items List
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)) {
                    //padding
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 1000.dp)) {

                    LazyColumn (state = listState){
                        items(itemsList.reversed(), key = { it.id }) {
                            item_Card(
                                onQuantityDecrease = {
                                    it.quantity -= 1
                                    Log.d("Quant D",it.quantity.toString())
                                },
                                onQuantityIncrease = {
                                    it.quantity += 1
                                    Log.d("Quant I",it.quantity.toString())
                                },
                                onDelete = {
                                    itemsList.removeAt(itemsList.indexOf(it))
                                },
                                onAmountChange = { amount -> it.totalAmount.value = amount},
                                quantity = it.quantity,
                                discount = it.disc,
                                price = it.price,
                                name = it.name,
                                modifier = Modifier
                                    .padding(
                                        horizontal = (sidePadding / 2).dp,
                                        vertical = 8.dp
                                    )
                            )
                        }
                    }
                }



            }
        }

        if (item_fill_popUp_status.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.DarkGray.copy(alpha = 0.5f))
                    .clickable { }) {

                // touch barrier
                BackHandler {
                    item_fill_popUp_status.value = false
                    selectedItemName.value = ""
                    selectedItemPrice.value = ""
                    selectedItemDiscount.value = ""
                    selectedItemQuantity.value = ""
                    currentItem.value = Item("",0.0,0.0,0)
                    itemNameEnabled.value = true
                }

            }

            item_fill_popUp(nameEnabled = itemNameEnabled.value,fieldsEnabled = fieldsEnabled.value,
                name = selectedItemName.value,
                price = selectedItemPrice.value,
                discount = selectedItemDiscount.value,
                quantity = selectedItemQuantity.value,
                onNameChange = {expanded_item_name_suggestion.value = true; selectedItemName.value = it},
                onPriceChange = {selectedItemPrice.value = it},
                onDiscountChange = {selectedItemDiscount.value = it},
                onQuantityChange = {selectedItemQuantity.value = it},
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 40.dp)
                    .align(Alignment.TopCenter),
                onCancel = {
                    item_fill_popUp_status.value = false
                    expanded_item_name_suggestion.value = false
                    selectedItemName.value = ""
                    selectedItemPrice.value = ""
                    selectedItemDiscount.value = ""
                    selectedItemQuantity.value = ""
                    currentItem.value = Item("",0.0,0.0,0)
                    fieldsEnabled.value =false
                    itemNameEnabled.value = true}
                ,
                onDone = {
                    expanded_item_name_suggestion.value = false
                    val res = viewModel.validateItemPopUPInput(selectedItemName.value,selectedItemPrice.value,selectedItemDiscount.value,selectedItemQuantity.value)
                    if (res && currentItem.value != Item("",0.0,0.0,0)){
                        if (selectedItemQuantity.value.toInt() <= currentItem.value.itemQuantity){
                            itemsList.add(item_popup(
                                name = selectedItemName.value,
                                price = selectedItemPrice.value.toDouble(),
                                disc = selectedItemDiscount.value.toDoubleOrNull() ?: 0.0,
                                quantity = selectedItemQuantity.value.toInt())
                            )
                            item_fill_popUp_status.value = false
                            selectedItemName.value = ""
                            selectedItemPrice.value = ""
                            selectedItemDiscount.value = ""
                            selectedItemQuantity.value = ""
                            currentItem.value = Item("",0.0,0.0,0)
                            fieldsEnabled.value = false
                            itemNameEnabled.value = true
                        } else{
                            Toast.makeText(context,"Inventory Shortage!", Toast.LENGTH_SHORT).show()
                            selectedItemQuantity.value = ""
                        }
                    }
                    else{
                        Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }


        // account suggestion
        if (expanded_account_suggestion.value) {

            BackHandler(enabled = expanded_account_suggestion.value) {
                expanded_account_suggestion.value = false
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp)
                    .padding(top = 100.dp)
                    .padding(horizontal = (sidePadding).dp)
                    .align(Alignment.TopCenter)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(10f))
                    .background(color = Color.White, shape = RoundedCornerShape(10f))
            ) {
                items(filteringOptions) { account ->
                    Text(
                        text = account.name + " " + "(" + account.type + ")",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(350),
                        modifier = Modifier
                            .padding(vertical = 1.dp, horizontal = 4.dp)
                            .fillMaxWidth()
                            .clickable {
                                selectedAccountText = account.name
                                expanded_account_suggestion.value = false
                                partyEnabled.value = false
                                addItemEnabled.value = true
                                selectedAccount.value = account
                                Toast.makeText(context, "Account selected", Toast.LENGTH_SHORT).show()
                            }
                    )
                }
            }
        }

        if (expanded_item_name_suggestion.value){

            BackHandler(enabled = expanded_item_name_suggestion.value) {
                expanded_item_name_suggestion.value = false
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = ((height.value / 2) - 110).dp)
                    .padding(top = 110.dp)
                    .padding(end = (width.value / 6).dp)
                    .padding(horizontal = 10.dp)
                    .align(Alignment.TopStart)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(10f))
                    .background(color = Color.White, shape = RoundedCornerShape(10f))
            ) {
                items(filtering_items_Options) { item ->
                    Text(
                        text = item.itemName + " " + "(" + item.itemQuantity + " psc )",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(350),
                        modifier = Modifier
                            .padding(vertical = 1.dp, horizontal = 4.dp)
                            .fillMaxWidth()
                            .clickable {
                                if (item.itemQuantity == 0) {
                                    Toast.makeText(context,"Not available in inventory", Toast.LENGTH_SHORT).show()
                                } else{
                                    currentItem.value = item
                                    fieldsEnabled.value = true
                                    itemNameEnabled.value = false
                                    selectedItemName.value = item.itemName
                                    selectedItemPrice.value = item.itemSellingPrice.toString()
                                    expanded_item_name_suggestion.value = false
                                }
                            }
                    )
                }
            }
        }
    }
}


