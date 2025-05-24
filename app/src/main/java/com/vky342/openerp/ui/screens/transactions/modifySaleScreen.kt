package com.vky342.openerp.ui.screens.transactions


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.ViewModels.transaction.Add_sale_Vm
import com.vky342.openerp.data.ViewModels.transaction.Modify_Sale_Vm
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.sale_button_box_color
import com.vky342.openerp.ui.theme.search_item_border_color
import com.vky342.openerp.ui.theme.search_item_card_shadow_color
import com.vky342.openerp.ui.theme.search_item_container_colour
import com.vky342.openerp.ui.theme.search_item_content_color
import com.vky342.openerp.ui.theme.search_item_focused_container_colour
import com.vky342.openerp.ui.theme.title_color
import com.vky342.openerp.ui.theme.var_amount_row_colour


@Composable
fun modifySaleScreen(viewModel: Modify_Sale_Vm = hiltViewModel()) {

    val context: Context = LocalContext.current

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    val is_bill_selected = remember { mutableStateOf(false) }
    val is_accountName_loaded = remember { mutableStateOf(false) }
    val is_bill_entries_loaded = remember { mutableStateOf(false) }


    val old_sale = remember { mutableStateOf(Sale(0, "", 0, 0.0, "")) }

    val bill_id = remember { mutableStateOf("") }

    var options = viewModel.old_Account_list.value
    var item_options = viewModel.all_items_in_inventory

    val item_fill_popUp_status = remember { mutableStateOf(false) }

    var selectedAccount = remember { mutableStateOf("") }

    var payment_mode = remember { mutableStateOf("") }

    var selectedDate = remember { mutableStateOf("") }

    var selectedAccountText by remember { mutableStateOf("") }

    // itemForm popUP
    var currentItem = remember { mutableStateOf(Item("",0.0,0.0,0)) }
    var selectedItemName = remember { mutableStateOf("") }
    var selectedItemPrice = remember { mutableStateOf("") }
    var selectedItemDiscount = remember { mutableStateOf("") }
    var selectedItemQuantity = remember { mutableStateOf("") }

    var itemsList = remember { mutableStateListOf<item_popup>() }

    val checkOutEnabled by remember { derivedStateOf { itemsList.isNotEmpty() && selectedAccount.value != "" && payment_mode.value != "" } }
    var partyEnabled = remember { mutableStateOf(false) }
    var addItemEnabled = remember { mutableStateOf(false)}

    // summary
    val totalItems by remember { derivedStateOf { itemsList.size } }
    var totalAmount = remember {derivedStateOf { itemsList.sumOf { it.totalAmount.value } } }

    // Items Summary
    val listState = rememberLazyListState()
    LaunchedEffect(itemsList.size) {
        listState.scrollToItem(0)
    }

    LaunchedEffect(viewModel.old_Account_Name.value) {
        if (viewModel.accountNameLoaded.value == true && is_accountName_loaded.value == false){

            selectedAccount.value = viewModel.old_Account_Name.value
            selectedAccountText = viewModel.old_Account_Name.value
            Log.d("DEBUG","line 136 : " + selectedAccount.value + "  " + selectedAccountText)
            addItemEnabled.value = true
        }
    }

    LaunchedEffect(viewModel.old_list_of_saleEntries.value) {
        if (viewModel.saleEntriesLoaded.value == true && is_bill_entries_loaded.value == false){
            viewModel.old_list_of_saleEntries.value.forEach { saleEntry ->
                val price = saleEntry.entryPrice
                val quantity = saleEntry.entryQuantity
                val discount = saleEntry.discount
                val finalPrice : Double = saleEntry.finalPrice!!

                itemsList.add(
                    item_popup(
                        name = saleEntry.itemName,
                        price = price,
                        disc = discount,
                        quantity = quantity,
                        totalAmount = mutableStateOf(finalPrice)
                    )
                )
            }
        }
    }

    //Suggestions
    var filteringOptions = options.filter {
        it.name.contains(selectedAccountText, ignoreCase = true) || it.address.contains(
            selectedAccountText,
            ignoreCase = true
        ) || it.contact.contains(selectedAccountText, ignoreCase = true)
    }
    var filtering_items_Options = item_options.value.filter {
        it.itemName.contains(selectedItemName.value, ignoreCase = true)
    }
    var expanded_item_name_suggestion = remember { mutableStateOf(false) }
    var expanded_account_suggestion = remember { mutableStateOf(false) }

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
                                if (viewModel.sale_list.value.any { it.saleId == bill_id.value.toInt() }) {
                                    viewModel.bill_id_to_modify.value = bill_id.value.toInt()
                                    is_bill_selected.value = true
                                    old_sale.value = viewModel.getSaleByID(bill_id.value.toInt())
                                    payment_mode.value = old_sale.value.saleType
                                    selectedDate.value = old_sale.value.saleDate

                                } else {
                                    Toast.makeText(context, "No bill found!", Toast.LENGTH_SHORT).show()
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
                            "Load recent sale...",
                            color = Color.White,
                            fontSize = 22.sp,
                            modifier = Modifier
                                .align(
                                    Alignment.CenterVertically
                                )
                                .clickable {
                                    // load recent Bill
                                    if (viewModel.latestSaleId.value != 0){
                                        viewModel.bill_id_to_modify.value = viewModel.latestSaleId.value
                                        bill_id.value = viewModel.latestSaleId.value.toString()
                                        is_bill_selected.value = true
                                        old_sale.value = viewModel.getSaleByID(viewModel.latestSaleId.value)
                                        payment_mode.value = old_sale.value.saleType
                                        selectedDate.value = old_sale.value.saleDate
                                    }else {
                                        Toast.makeText(context, "No bill found!", Toast.LENGTH_SHORT).show()
                                    }

                                }
                        )
                    }
                }
            }
        }
    }else{

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
                        .height(45.dp)
                ) {
                    Text(
                        text = " sale : " + bill_id.value.toString(),
                        color = New_account_title_color,
                        fontSize = 24.sp,
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
                        .height(70.dp)
                ) {
                    form_fields(
                        trailing_icon_enabled = if(selectedAccount.value == "") false else{true},
                        onTrailingIconClick = {
                            Log.d("Status", "on Trailing Icon Clicked")
                            addItemEnabled.value = false
                            selectedAccountText = ""
                            partyEnabled.value = true
                            payment_mode.value = ""
                            selectedAccount.value = ""
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

                // Purchase Summary
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 25.dp)) {

                    Text(
                        "S u m m a r y",
                        fontSize = 20.sp, color = title_color,
                        modifier = Modifier
                            .padding(horizontal = sidePadding.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Variable_Amount_Row_2(totalAmount = totalAmount.value, totalItems = totalItems,modifier = Modifier.background(color = var_amount_row_colour))

                    checkout_Strip(enabled = checkOutEnabled, onClick = {

                        if (selectedDate.value != ""){
                            viewModel.update_Sale(
                                account_name = selectedAccount.value,
                                sale = Sale(saleId = old_sale.value.saleId, saleDate = selectedDate.value, ledgerId = 0, saleAmount = totalAmount.value, saleType = payment_mode.value),
                                new_list_of_saleEntries = itemsList.map { item ->
                                    SaleEntry(
                                        entryId = 0,
                                        entryQuantity = item.quantity,
                                        entryPrice = item.price,
                                        discount = item.disc,
                                        finalPrice = item.totalAmount.value,
                                        itemName = item.name,
                                        saleId = old_sale.value.saleId
                                    )
                                }
                            )
                            itemsList.clear()
                            selectedAccountText = ""
                            selectedAccount.value = ""
                            selectedDate.value = ""
                            payment_mode.value = ""
                            partyEnabled.value = true
                            currentItem.value = Item("",0.0,0.0,0)
                            old_sale.value = Sale(0, "", 0, 0.0, "")
                            bill_id.value = ""

                            is_bill_selected.value = false

                        }


                    })

                    Add_button_Strip(addItemEnabled.value,onClick = { item_fill_popUp_status.value = true })


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
                Log.d("DEBUG","itempopup")
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
                    }


                }

                item_fill_popUp(
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
                        selectedItemName.value = ""
                        selectedItemPrice.value = ""
                        selectedItemDiscount.value = ""
                        selectedItemQuantity.value = ""
                        currentItem.value = Item("",0.0,0.0,0)
                    },
                    onDone = {

                        if (item_options.value.any{ item -> item.itemName == selectedItemName.value}){

                            if (selectedItemQuantity.value.toInt() <= currentItem.value.itemQuantity){
                                itemsList.add(item_popup(
                                    name = selectedItemName.value,
                                    price = selectedItemPrice.value.toDouble(),
                                    disc = selectedItemDiscount.value.toDoubleOrNull() ?: 0.0,
                                    quantity = selectedItemQuantity.value.toInt())
                                )
                                item_fill_popUp_status.value = false
                                currentItem.value = Item("",0.0,0.0,0)
                            }else{
                                Toast.makeText(context,"Inventory Shortage!", Toast.LENGTH_SHORT).show()
                                selectedItemQuantity.value = ""
                            }

                        }
                        else{
                            Toast.makeText(context, "No Item found named " + selectedItemName.value, Toast.LENGTH_SHORT).show()
                            selectedItemName.value = ""
                            selectedItemPrice.value = ""
                            selectedItemDiscount.value = ""
                            selectedItemQuantity.value = ""
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
                        .padding(top = 130.dp)
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
                                    selectedAccount.value = account.name
                                    Toast.makeText(context, "Account selected", Toast.LENGTH_SHORT)
                                        .show()
                                })
                    }
                }
            }

            if (expanded_item_name_suggestion.value){

                BackHandler(enabled = expanded_account_suggestion.value) {
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
                                    }
                                    else{
                                        currentItem.value = item
                                        selectedItemName.value = item.itemName
                                        selectedItemPrice.value = item.itemSellingPrice.toString()
                                        expanded_item_name_suggestion.value = false
                                    }
                                })
                    }
                }
            }

        }

    }
}

@Composable
fun billSearchbar(
    modifier: Modifier,
    current_value: String,
    label: String,
    onVC: (String) -> Unit,
    onTrailingIconClick: () -> Unit = {}
) {


    TextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth(0.95f)
            .border(
                width = 1.dp, color = search_item_border_color, shape = CircleShape.copy(
                    CornerSize(20f)
                )
            )
            .shadow(
                elevation = 5.dp,
                shape = CircleShape.copy(CornerSize(20f)),
                ambientColor = search_item_card_shadow_color,
                spotColor = search_item_card_shadow_color
            ),
        value = current_value,
        placeholder = { Text(label, color = search_item_content_color) },
        onValueChange = {
            onVC(it)

        },
        trailingIcon = {
            IconButton(onClick = { onTrailingIconClick() }) {
                Icon(
                    Icons.Default.Search,
                    modifier = Modifier,
                    contentDescription = "",
                    tint = search_item_content_color
                )
            }
        },
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = search_item_focused_container_colour,
            unfocusedContainerColor = search_item_container_colour
        )
    )

}
