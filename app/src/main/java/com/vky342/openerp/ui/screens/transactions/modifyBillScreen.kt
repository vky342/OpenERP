package com.vky342.openerp.ui.screens.transactions

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.vky342.openerp.data.Entities.Bill
import com.vky342.openerp.data.Entities.BillEntry
import com.vky342.openerp.ui.theme.Greye
import kotlin.math.round


var list_0f_Bill_entries : List<BillEntry> = listOf(
    BillEntry(
        entryId = 1,
        billIdFk = 1001,
        itemNameFk = "Item 1",
        entryPrice = 10.0,
        entryQuantity = 2,
        discount = 1.0,
        finalPrice = calculateFinalPrice(10.0, 2, 1.0)
    ),
    BillEntry(
        entryId = 2,
        billIdFk = 1002,
        itemNameFk = "Item 2",
        entryPrice = 20.0,
        entryQuantity = 1,
        discount = 2.0,
        finalPrice = calculateFinalPrice(20.0, 1, 2.0)
    ),
    BillEntry(
        entryId = 3,
        billIdFk = 1003,
        itemNameFk = "Item 3",
        entryPrice = 15.0,
        entryQuantity = 3,
        discount = 1.5,
        finalPrice = calculateFinalPrice(15.0, 3, 1.5)
    ))


val list_of_all_account_name = listOf("kunal", "rahul", "amit", "shyam", "ram", "others", "lalu", "kalu", "pintu", "chintu")


@Composable
fun modifyBillScreen(){

    /*
    * search bar for id
    * bill editor
    * save button
    *
    * */

    // screen configurations

    val height = LocalConfiguration.current.run { screenHeightDp.dp}
    val topPadding = height.value * 0.1

    val bottompadding = height.value * .28

    // Original data

    val list_of_all_existing_billId = listOf(1232,1231,2423,32423,1313)

    val Bill_to_Modiy = Bill(billId = 234, billDate = 123454, billAmount = 200.0, ledgerType = 0, accountNameFk = "kunal sahu", ledgerIdFk = 1233)

    val list_of_all_account_name = listOf("kunal", "rahul", "amit", "shyam", "ram", "others", "lalu", "kalu", "pintu", "chintu")


    // All States

    val Bill_Id_to_Modify : MutableState<String> = remember {
        mutableStateOf(Bill_to_Modiy.billId.toString())
    }

    val loadBillEditor = remember {
        mutableStateOf(false)
    }

    // Final DATA TO BE SAVED

    var Modified_Bill : Bill = Bill(billId = 2314, billDate = 34234, billAmount = 2342.0, ledgerType = 1, accountNameFk = "kunal", ledgerIdFk = 23423)
    var Modified_List_of_Bill_entries : List<BillEntry>

    /// WHOLE SCREEN

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = topPadding.dp, bottom = bottompadding.dp)
        .background(color = Greye)
    ) {
        TextField(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(vertical = 4.dp),value = Bill_Id_to_Modify.value, onValueChange = {Bill_Id_to_Modify.value = it}, label = { Text(
            text = "Bill id to modify"
        )}, trailingIcon = { IconButton(onClick = {

            if (Bill_Id_to_Modify.value == ""){
                Log.d("INPUT ERR", "please provide bill id")
            }
            else if (Bill_Id_to_Modify.value != ""){
                if (Bill_Id_to_Modify.value.toInt() in list_of_all_existing_billId){
                    // load Bill associated with this bill id
                    // load list of bill entries associated with this Bill
                    // pass it to bill editor

                    // continue to load bill editor

                    loadBillEditor.value = true

                }
                else {
                    Log.d("INPUT ERR", "please provide a valid bill id")
                }
            }

        }) {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = "search")


        }})

        if (loadBillEditor.value){
            Bill_Editor(
                Bill = Modified_Bill,
                list_of_original_bill_entries = list_0f_Bill_entries,
                list_of_all_account_name = list_of_all_account_name,
                ONSAVE = { Bill, list ->
                    Modified_Bill = Bill
                    Modified_List_of_Bill_entries = list
                    loadBillEditor.value = false
                    Log.d("RESULT", "$Modified_List_of_Bill_entries")
                    Log.d("RESULT", "$Modified_Bill")
                }
            )
        }

    }

}


@Composable
fun Bill_Editor(Bill : Bill,
                list_of_original_bill_entries : List<BillEntry>,
                list_of_all_account_name : List<String>,
                ONSAVE : (Bill, List<BillEntry>) -> Unit
){

    // UI STATES

    var accountNameExpander by remember {
        mutableStateOf(false)
    }

    // Bill info States

    val billdate = remember {
        mutableStateOf(
            Bill.billDate
        )
    }
    val ledgertype = remember {
        mutableStateOf(

            if(Bill.ledgerType == 1){
                "purchase"
            }else{
                "sale"
            }
        )
    }

    val accountnameFk = remember {
        mutableStateOf(
            Bill.accountNameFk
        )
    }


    // Bill editor configurations
    val height = LocalConfiguration.current.run { screenHeightDp.dp}
    val Row_height_for_Billinfo = height.value * 0.1 / 2.4
    val Row_height_for_Bill_entry_fields = Row_height_for_Billinfo

    // Whole editor

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {

        // Bill info / editor

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(Row_height_for_Billinfo.dp)){

            // ID // non editable
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(.3f)
                .background(color = Color.LightGray)
                .border(width = 0.3.dp, color = Color.Black)){

                Text(fontSize = 15.sp,text = "id : ${Bill.billId}", color = Greye, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }


            //DATE
            Box(modifier = Modifier
                .fillMaxHeight()
                .background(color = Color.LightGray)
                .weight(.35f)
                .border(width = 0.3.dp, color = Color.Black)){
                Row (modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                    .background(color = Color.LightGray)
                    .padding(horizontal = 3.dp)){
                    Text(text = "date : ", color = Greye, modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .wrapContentHeight()
                        .align(Alignment.CenterVertically))

                    BasicTextField(singleLine = true,value = billdate.value.toString(), onValueChange = {billdate.value = it.toInt()}, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically))
                }
            }


            // TYPE
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(.35f)
                .border(width = 0.3.dp, color = Color.Black)
                .background(color = Color.LightGray)){
                Row (modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                    .background(color = Color.LightGray)
                    .padding(horizontal = 3.dp)){
                    Text(text = "type : ", color = Greye, modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .wrapContentHeight()
                        .align(Alignment.CenterVertically))

                    BasicTextField(singleLine = true,value = ledgertype.value, onValueChange = { ledgertype.value = it }, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically))
                }
            }

        }
        //ACCOUNT
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .border(width = 0.3.dp, color = Color.Black)
            .background(color = Color.LightGray)
            .height(Row_height_for_Billinfo.dp)){

            Box (modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .weight(weight = 1f)){

                Row (modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                    .background(color = Color.LightGray)
                    .padding(horizontal = 3.dp)){

                    Text(text = " to account : ", color = Greye, modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(horizontal = 4.dp)
                        .align(Alignment.CenterVertically))

                    BasicTextField(singleLine = true,textStyle = TextStyle(color = Greye, fontWeight = FontWeight(450)),value = accountnameFk.value, onValueChange = {accountnameFk.value = it
                                                                                              accountNameExpander = true}, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically))
                }
            }

        }

        // Bill info / editor - ENDED

        // bill entry fields - START

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .align(Alignment.CenterHorizontally)
            .height(Row_height_for_Bill_entry_fields.dp)){

            // SNo.
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .07f)
                .background(color = Color.Red)
                .border(width = 0.3.dp, color = Color.Black)){

                Text(fontSize = 13.sp,text = "Sn.", color = Color.White, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))
            }

            // ITEM NAME
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .33f)
                .background(color = Color.LightGray)
                .border(width = 0.3.dp, color = Color.Black)){

                Text(fontSize = 13.sp,text = "Item name", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }

            // Quantity
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .1f)
                .background(color = Color.Yellow)
                .border(width = 0.3.dp, color = Color.Black)){

                Text(fontSize = 13.sp,text = "No.", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }

            // Price
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .14f)
                .background(color = Color.Cyan)
                .border(width = 0.3.dp, color = Color.Black)){

                Text(fontSize = 13.sp,text = "Price", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }

            //DISCOUNT
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .1f)
                .background(color = Color.Green)
                .border(width = 0.3.dp, color = Color.Black)){

                Text(fontSize = 13.sp,text = "disc", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }

            // TOTAL PRICE
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .14f)
                .background(color = Color.LightGray)
                .border(width = .3.dp, color = Color.Black)){

                Text(fontSize = 13.sp,text = "Total", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }

        } // Bill entry fields - ENDED


        /// BILL ENTRY EDITOR
        Bill_entry_editor(list_of_original_bill_entries = list_of_original_bill_entries, billId = Bill.billId, onSAVE = { listofbillentry ->

            var billamountsum = 0.0
            for(entry in listofbillentry){
                billamountsum = billamountsum + entry.finalPrice!!
            }

            ONSAVE(
                Bill(billId = Bill.billId, billDate = billdate.value, ledgerType = if (ledgertype.value.lowercase() == "sale"){0}else if(ledgertype.value.lowercase() == "purchase"){1}else{Bill.ledgerType}, accountNameFk = accountnameFk.value, billAmount = billamountsum, ledgerIdFk = Bill.ledgerIdFk),
                listofbillentry
                )
            })

        /// POP UPS

            // POP UP for Account name
        if (accountNameExpander){
            Popup(alignment = Alignment.TopCenter, 
                offset = IntOffset(x = 0, y = Row_height_for_Billinfo.toInt() * 5),
                properties = PopupProperties(excludeFromSystemGesture = true), 
                onDismissRequest = {accountNameExpander = false}) {
                val columheight = Row_height_for_Billinfo * 4
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = columheight.dp)
                        .background(color = Color.White),
                ) {

                    if (accountnameFk.value.isNotEmpty()) {
                        items(
                            list_of_all_account_name.filter {
                                it.lowercase()
                                    .contains(accountnameFk.value.lowercase()) || it.lowercase()
                                    .contains("others")
                            }
                                .sorted()
                        ) {
                            PartsItems(name = it) { name ->
                                accountnameFk.value = name
                                accountNameExpander = false
                            }
                        }
                    } else {
                        items(
                            list_of_all_account_name.sorted()
                        ) {
                            PartsItems(name = it) { name ->
                                accountnameFk.value = name
                                accountNameExpander = false
                            }
                        }
                    }
                    
                }
            }
        }


    }

}


@Composable
fun Bill_entry_editor(list_of_original_bill_entries: List<BillEntry>,
                      billId : Int,
                      onSAVE : (List<BillEntry>) -> Unit){

    val ScrollState = rememberScrollState()

    val modified_list_of_bill_entries = remember {
        mutableStateOf(list_of_original_bill_entries)
    }


    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .fillMaxHeight(.8f)){

        Column(modifier = Modifier
            .verticalScroll(ScrollState)
            .fillMaxHeight(.7f)) {
            Column (modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()){
                for (BillEntrye in modified_list_of_bill_entries.value){
                    Bill_entry_editor_row(index = modified_list_of_bill_entries.value.indexOf(BillEntrye) + 1,
                        Bill_entry = BillEntrye,
                        billIdFk = billId,
                        onDelete = {
                            modified_list_of_bill_entries.value = modified_list_of_bill_entries.value.minus(BillEntrye)


                        },
                        onDone = {billid, entryid, itemnamefk, itemquantity, itemprice, itemdiscount, finalprice ->
                            BillEntrye.billIdFk = billId
                            BillEntrye.entryId = entryid
                            BillEntrye.itemNameFk = itemnamefk
                            BillEntrye.entryQuantity = itemquantity
                            BillEntrye.entryPrice = itemprice
                            BillEntrye.discount = itemdiscount
                            BillEntrye.finalPrice = finalprice

                        }

                    )
                }

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                for (i in modified_list_of_bill_entries.value.size..modified_list_of_bill_entries.value.size + 3 )
                {
                    Bill_entry_editor_row(
                        billIdFk = billId,
                        index = i + 1,
                        onDelete = {},
                        onDone =
                        {billid, entryid, itemnamefk, itemquantity, itemprice, itemdiscount, finalprice ->
                        val newBillEntry : BillEntry = BillEntry(billIdFk = billId,
                            entryId = entryid,
                            itemNameFk = itemnamefk,
                            entryQuantity = itemquantity,
                            entryPrice = itemprice,
                            discount = itemdiscount,
                            finalPrice = finalprice)

                            modified_list_of_bill_entries.value = modified_list_of_bill_entries.value.plus(newBillEntry)
                        }
                    )
                }
            }
        }

    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()){
        Button(colors = ButtonColors(containerColor = Color.LightGray, contentColor = Color.Black, disabledContentColor = Color.Black, disabledContainerColor = Color.Black),modifier = Modifier
            .wrapContentSize()
            .align(Alignment.Center)
            .padding(top = 20.dp),onClick = {
            onSAVE(modified_list_of_bill_entries.value)
        }) {
            Text(text = "SAVE")
        }
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Bill_entry_editor_row(
    index: Int,
    Bill_entry : BillEntry? = null,
    billIdFk : Int,
    onDone : (Int,Int,String,Int,Double,Double,Double) -> Unit,
    onDelete : () -> Unit){

    // LOCAL CONFIGURATIONS
    val height = LocalConfiguration.current.run { screenHeightDp.dp}
    val Row_height = height.value * 0.1 / 1.8

    // UI STATES

    val itemNameExpander = remember {
        mutableStateOf(false)
    }


    val (ITEMNAME, QUANTITY, PRICE, DISC) = remember {
        FocusRequester.createRefs()
    }
    val focusManager = LocalFocusManager.current

    val bill_IdFk = billIdFk

    val entryId = if (Bill_entry != null){Bill_entry.entryId}else{0}

    val itemNameFk = remember {
        mutableStateOf(

            if (Bill_entry != null){Bill_entry.itemNameFk}else{""}
        )
    }

    val entryQuantity = remember {
        mutableStateOf(if(Bill_entry != null){Bill_entry.entryQuantity.toString()}else{""})
    }

    val entryPrice = remember {
        mutableStateOf(
            if (Bill_entry != null){Bill_entry.entryPrice.toString()}else{""}
        )
    }

    val discount = remember {
        mutableStateOf(

            if (Bill_entry != null){Bill_entry.discount.toString()}else{""}
        )
    }

    val finalPrice = remember {
        mutableStateOf(

            if (Bill_entry != null){Bill_entry.finalPrice.toString()}else{""}
        )
    }


    // Modified STATED

    fun ClearAll(){
        itemNameFk.value = ""
        entryPrice.value = ""
        entryQuantity.value = ""
        discount.value = ""
        finalPrice.value = ""
    }

    // ROW
    Row (modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .height(Row_height.dp)){


        // SNo.
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .07f)
            .border(width = .3.dp, color = Color.Black)
            .background(color = Color.Red)){


            Text(fontSize = 13.sp,text = index.toString(), color = Color.White, modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
                .clickable { onDelete() }
            )


        }


        // ITEM NAME
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(0.33f)
            .background(color = Color.LightGray)
            .border(width = .3.dp, color = Color.Black)){

            BasicTextField(value = itemNameFk.value, onValueChange = { itemNameFk.value = it
                                                                     itemNameExpander.value = true},
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center)
                    .wrapContentHeight()
                    .padding(horizontal = 2.dp)
                    .focusRequester(ITEMNAME)
                    .focusProperties { next = QUANTITY },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                })
            )

        }

        // QUANTITY
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .1f)
            .background(color = Color.Yellow)
            .border(width = .3.dp, color = Color.Black)){

            BasicTextField(value = entryQuantity.value, onValueChange = {

                if(itemNameFk.value != ""){
                    entryQuantity.value = it
                }
                else{
                    Log.d("QUANTITY", "ItemEntryRow: name is empty")
                }

            }, modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .wrapContentHeight()
                .padding(horizontal = 2.dp)
                .focusRequester(QUANTITY)
                .focusProperties { next = PRICE },
                singleLine = true,

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {

                    focusManager.moveFocus(FocusDirection.Next)
                }
                )
            )

        }

        // PRICE
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .14f)
            .background(color = Color.Cyan)
            .border(width = .3.dp, color = Color.Black)){

            BasicTextField(value = entryPrice.value, onValueChange = {

                if(entryQuantity.value != ""){
                    entryPrice.value = it
                }
                else{
                    Log.d("PRICE", "ItemEntryRow: quantity is empty")
                }

            }, modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .wrapContentHeight()
                .padding(horizontal = 2.dp)
                .focusRequester(PRICE)
                .focusProperties { next = DISC },
                singleLine = true,

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {

                    focusManager.moveFocus(FocusDirection.Next)
                }
                )
            )

        }

        // DISCOUNT
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .1f)
            .background(color = Color.Green)
            .border(width = .3.dp, color = Color.Black)){

            BasicTextField(value = discount.value, onValueChange = {

                if (entryPrice.value != ""){
                    discount.value = it
                }
                else{
                    Log.d("DISCOUNT", "item price is empty")
                }

            }, modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .wrapContentHeight()
                .padding(horizontal = 2.dp)
                .focusRequester(DISC),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {

                    if (discount.value != ""){
                        finalPrice.value = calculateFinalPrice(entryPrice = entryPrice.value.toDouble(), quantity = entryQuantity.value.toInt(), discount = discount.value.toDouble()).toString()

                        onDone(bill_IdFk,entryId, itemNameFk.value, entryQuantity.value.toInt(), entryPrice.value.toDouble(), discount.value.toDouble(),
                            round(finalPrice.value.toDouble() * 100) / 100)

                        ClearAll()
                    }
                    else{
                       Log.d("DISC", "PLEASE ENTER DISCOUNT FIRST")
                    }
                }
                )
            )

        }

        // TOTAL PRICE
        Box(modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .14f)
            .background(color = Color.LightGray)
            .border(width = .3.dp, color = Color.Black)){

            if(finalPrice.value != ""){
                Text(fontSize = 13.sp,text = finalPrice.value, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))
            }else{
                Text(fontSize = 13.sp,text = "", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))
            }

        }

        if (itemNameExpander.value){
            Popup(alignment = Alignment.TopCenter,
                offset = IntOffset(x = 0, y = Row_height.toInt() * 3),
                properties = PopupProperties(excludeFromSystemGesture = true),
                onDismissRequest = {itemNameExpander.value = false}) {
                val columheight = Row_height * 3
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = columheight.dp)
                        .background(color = Color.White),
                ) {

                    if (itemNameFk.value.isNotEmpty()) {
                        items(
                            list_of_all_account_name.filter {
                                it.lowercase()
                                    .contains(itemNameFk.value.lowercase()) || it.lowercase()
                                    .contains("others")
                            }
                                .sorted()
                        ) {
                            PartsItems(name = it) { name ->
                                itemNameFk.value = name
                                itemNameExpander.value = false
                            }
                        }
                    } else {
                        items(
                            list_of_all_account_name.sorted()
                        ) {
                            PartsItems(name = it) { name ->
                                itemNameFk.value = name
                                itemNameExpander.value = false
                            }
                        }
                    }

                }
            }
        }

    }
}

fun calculateFinalPrice(entryPrice: Double, quantity: Int, discount: Double): Double {
    return (entryPrice - (entryPrice * discount / 100)) * quantity
}

@Composable
fun PartsItems(
    name: String,
    onSelect: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(name)
            }
    ) {
        Text(text = name, fontSize = 16.sp)
    }

}