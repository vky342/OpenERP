package com.vky342.openerp.ui.screens.transactions

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.vky342.openerp.data.Entities.BillEntry
import com.vky342.openerp.ui.Graphs.TransactionScreen
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.Purple40
import kotlinx.coroutines.launch
import kotlin.math.round


@Composable
fun AddPurchaseScreen(navController: NavHostController){

    val height = LocalConfiguration.current.run { screenHeightDp.dp}
    val topPadding = height.value * 0.1
    val newTopseperator = topPadding + 10
    val sidePadding = 8.dp
    val RowHeight = topPadding/2.4

    val bottompadding = height.value * .35

    val coroutineScope = rememberCoroutineScope()

    var accountNameExpander by remember {
        mutableStateOf(false)
    }


    // To be Saved

    val listofbillid : List<Int> = listOf(2,3,5,6,7) // to be provided by Bills Database

    val billId = remember {
        mutableStateOf(listofbillid.last() + 1)
    } // to be provided by Bills Database

    var billdate by remember {
        mutableStateOf("08/06/24") // to be provided by Date provider
    }

    var accountName by remember {
        mutableStateOf("kunal sahu")
    }

    var billtype = 0 // 0 for sale

    val BillEntries : MutableList<BillEntry> = remember{ mutableListOf()}

    val List_of_All_account = listOf(
        "Food",
        "Beverages",
        "Sports",
        "Learning",
        "Travel",
        "Rent",
        "Bills",
        "Fees",
        "Others")

    val list_of_all_items = listOf( /// item names
        "Food",
        "Beverages",
        "Sports",
        "Learning",
        "Travel",
        "Rent",
        "Bills",
        "Fees",
        "Others",
    )

    fun newBill(){
        navController.navigate(TransactionScreen.AddSale){
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }

    }

    Column(modifier = Modifier
        .background(color = Greye)
        .fillMaxSize()
        .padding(
            top = newTopseperator.dp,
            bottom = bottompadding.dp,
            end = sidePadding,
            start = sidePadding
        )
    ) {

        val scrollState = rememberScrollState()

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(RowHeight.dp)){
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .3f)
                .background(color = Color.White)
                .border(width = .3.dp, color = Greye)){

                Text(fontSize = 16.sp,text = "id : ${billId.value}", color = Color.Black, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))
            }
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .35f)
                .background(color = Color.White)
                .border(width = 0.3.dp, color = Greye)){

                Row (modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                    .background(color = Color.White)
                    .padding(horizontal = 3.dp)){

                    Text(text = "date : ", color = Color.Black, modifier = Modifier
                        .wrapContentHeight()
                        .align(Alignment.CenterVertically))

                    BasicTextField(value = billdate, onValueChange = {billdate = it}, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 2.dp))
                }



            }
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .35f)
                .background(color = Color.White)
                .border(width = 0.3.dp, color = Greye)){

                Text(fontSize = 16.sp,text = "type : Purchase", color = Color.Black, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(RowHeight.dp)){
            Box (modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize()
                .background(color = Color.White)
                .weight(weight = 1f)
                .border(width = 0.3.dp, color = Greye)){

                Row (modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                    .background(color = Color.White)
                    .padding(horizontal = 3.dp)){

                    Text(text = "To account : ", color = Color.Black, modifier = Modifier
                        .wrapContentHeight()
                        .align(Alignment.CenterVertically))

                    BasicTextField(value = accountName, onValueChange = {accountName = it
                        accountNameExpander = true}, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 2.dp))
                }
            }
        }


        Row (modifier = Modifier
            .fillMaxWidth()
            .height(RowHeight.dp)){
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .07f)
                .background(color = Color.Red)
                .border(width = 0.3.dp, color = Greye)){

                Text(fontSize = 13.sp,text = "Sn.", color = Color.White, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))
            }
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .33f)
                .background(color = Color.LightGray)
                .border(width = 0.3.dp, color = Greye)){

                Text(fontSize = 13.sp,text = "Item name", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .1f)
                .background(color = Color.Yellow)
                .border(width = 0.3.dp, color = Greye)){

                Text(fontSize = 13.sp,text = "No.", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .14f)
                .background(color = Color.Cyan)
                .border(width = 0.3.dp, color = Greye)){

                Text(fontSize = 13.sp,text = "Price", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .1f)
                .background(color = Color.Green)
                .border(width = 0.3.dp, color = Greye)){

                Text(fontSize = 13.sp,text = "disc", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(weight = .14f)
                .background(color = Color.LightGray)
                .border(width = 0.3.dp, color = Greye)){

                Text(fontSize = 13.sp,text = "Total", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))

            }
            if (accountNameExpander){
                Popup(alignment = Alignment.TopCenter, offset = IntOffset(x = 0, y = 0),properties = PopupProperties(excludeFromSystemGesture = true), onDismissRequest = {accountNameExpander = false}) {
                    val columheight = RowHeight * 4
                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = columheight.dp)
                            .background(color = Color.White),
                    ) {

                        if (accountName.isNotEmpty()) {
                            items(
                                List_of_All_account.filter {
                                    it.lowercase()
                                        .contains(accountName.lowercase()) || it.lowercase()
                                        .contains("others")
                                }
                                    .sorted()
                            ) {
                                PartsItems(name = it) { name ->
                                    accountName = name
                                    accountNameExpander = false
                                }
                            }
                        } else {
                            items(
                                List_of_All_account.sorted()
                            ) {
                                PartsItems(name = it) { name ->
                                    accountName = name
                                    accountNameExpander = false
                                }
                            }
                        }



                    }
                }
            }
        }
        fun AddbilltoBillEntries(BillEntry : BillEntry){
            BillEntries.add(BillEntry)
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.7f)
            .verticalScroll(state = scrollState)) {

            var shownTillIndex by remember {
                mutableStateOf(5)
            }

            val focusManager = LocalFocusManager.current

            repeat(shownTillIndex ){
                ItemEntryRowPurchase(billId = billId.value,index = it + 1, categories = list_of_all_items,addNewItemEntryRow = {
                    shownTillIndex += 1

                    coroutineScope.launch {
                        scrollState.scrollTo(scrollState.maxValue)
                    }
                },
                    focusChange = {focusManager.moveFocus(FocusDirection.Next)},
                    removeItemEntryRow = {
                        if (shownTillIndex > 5){
                            shownTillIndex -= 1
                        }else{
                            //do nothing
                        }
                    },
                    billEntryAddition = {
                        BillEntries.add(it)
                    },
                    removefromBillEntries = {BillEntries.removeAt(it - 1)}
                )
            }



        }

        Button(modifier = Modifier
            .height((RowHeight * 1.7).dp)
            .padding(5.dp)
            .align(Alignment.CenterHorizontally),
            colors = ButtonColors(contentColor = Greye, containerColor = Color.LightGray, disabledContentColor = Color.White, disabledContainerColor = Purple40),
            onClick = {
                // save Bill first
                // then run a loop for saving every billEntry
                Log.d("SAVING", "Saved BIll - $billId")

                Log.d("STATUS OF BillEntries", "$BillEntries")

                for (billentry in BillEntries){
                    Log.d("SAVING", "SAVED Bill entry - ${billentry.entryId} :: ${billentry.itemNameFk}")
                }

                newBill()
            }
        ) {
            Text(text = "save", fontWeight = FontWeight(500),fontSize = 20.sp)
        }

    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ItemEntryRowPurchase(billId : Int, index : Int,
                 addNewItemEntryRow : () -> Unit,
                 categories : List<String>,
                 focusChange : () -> Unit,
                 removeItemEntryRow : () -> Unit,
                 billEntryAddition : (BillEntry) -> Unit,
                 removefromBillEntries : (Int) -> Unit){

    val (itemname, itemquantity, itemprice,itemdisc) = remember { FocusRequester.createRefs() }
    val focusManager = LocalFocusManager.current

    val height = LocalConfiguration.current.run { screenHeightDp.dp}
    val topPadding = height.value * 0.1
    val RowHeight = topPadding/1.8

    var item_name by remember {
        mutableStateOf("")
    }
    var item_quantity by remember {
        mutableStateOf("")
    }
    var item_Price by remember {
        mutableStateOf("")
    }
    var item_discount by remember {
        mutableStateOf("")
    }
    var item_total_price : Double? by remember {
        mutableStateOf(null)
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    var AddedToBillEntries by remember {
        mutableStateOf(false)
    }


    Row (modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .height(RowHeight.dp)){
        Box (modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .07f)
            .border(width = 0.3.dp, color = Greye)
            .background(color = Color.Red)){

            Text(fontSize = 13.sp,text = index.toString(), color = Color.White, modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
                .clickable {
                    item_name = ""
                    item_quantity = ""
                    item_Price = ""
                    item_discount = ""
                    item_total_price = null

                    removeItemEntryRow()

                    if (AddedToBillEntries) {
                        removefromBillEntries(index)
                    }

                })
        }
        Box (modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .33f)
            .background(color = Color.LightGray)
            .border(width = 0.3.dp, color = Greye)){

            BasicTextField(value = item_name, onValueChange = { item_name = it
                expanded = true}, modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .wrapContentHeight()
                .padding(horizontal = 2.dp)
                .focusRequester(itemname)
                .focusProperties { next = itemquantity },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    if (item_name != ""){
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                    else{
                        Log.d("NAME", "ItemEntryRow: Type name please")
                    }
                })
            )

        }

        Box (modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .1f)
            .background(color = Color.Yellow)
            .border(width = 0.3.dp, color = Greye)){

            BasicTextField(value = item_quantity, onValueChange = {

                if(item_name.isNotEmpty()){
                    item_quantity = it
                }
                else{
                    Log.d("QUANTITY", "ItemEntryRow: name is empty")
                }

            }, modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .wrapContentHeight()
                .padding(horizontal = 2.dp)
                .focusRequester(itemquantity)
                .focusProperties { next = itemprice }, singleLine = true,

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {

                    if (item_quantity != ""){
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                    else{
                        Log.d("QUANTITY", "ItemEntryRow: please type quantity")
                    }

                })
            )

        }
        Box (modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .14f)
            .background(color = Color.Cyan)
            .border(width = 0.3.dp, color = Greye)){

            BasicTextField(value = item_Price, onValueChange = {

                if(item_quantity.isNotEmpty()){
                    item_Price = it
                }
                else{
                    Log.d("PRICE", "ItemEntryRow: quantity is empty")
                }

            }, modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .wrapContentHeight()
                .padding(horizontal = 2.dp)
                .focusRequester(itemprice)
                .focusProperties { next = itemdisc }, singleLine = true,

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    if (item_Price != ""){

                        focusManager.moveFocus(FocusDirection.Next)
                    }else{
                        Log.d("PRICE","please enter price")
                    }
                })
            )

        }
        Box (modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .1f)
            .background(color = Color.Green)
            .border(width = 0.3.dp, color = Greye)){

            BasicTextField(value = item_discount, onValueChange = {

                if (item_Price.isNotEmpty()){
                    item_discount = it
                }
                else{
                    Log.d("DISCOUNT", "item price is empty")
                }

            }, modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .wrapContentHeight()
                .padding(horizontal = 2.dp)
                .focusRequester(itemdisc),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {

                    if(item_discount != ""){
                        addNewItemEntryRow()
                        val itemno : Int = item_quantity.toInt()
                        val itemtempprice : Double = item_Price.toDouble()
                        val discountamnt = item_discount.toDouble()
                        val upperfract : Double = 100 - discountamnt
                        val finaldiscamnt : Double = upperfract / 100

                        item_total_price = itemno * itemtempprice * finaldiscamnt

                        item_total_price = round(item_total_price!! * 100) / 100

                        val tempBillEntry = BillEntry(entryId = 0, entryQuantity = itemno, entryPrice = itemtempprice, discount = discountamnt, finalPrice = item_total_price, billIdFk = billId, itemNameFk = item_name)

                        billEntryAddition(tempBillEntry)

                        AddedToBillEntries = true

                        focusChange()

                    }else{
                        Log.d("DISCOUNT", "please enter discount")
                    }

                })
            )

        }
        Box (modifier = Modifier
            .fillMaxHeight()
            .weight(weight = .14f)
            .background(color = Color.LightGray)
            .border(width = 0.3.dp, color = Greye)){

            if(item_total_price != null){
                Text(fontSize = 13.sp,text = item_total_price.toString(), modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))
            }else{
                Text(fontSize = 13.sp,text = "", modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center))
            }
        }


        if (expanded){
            Popup(alignment = Alignment.TopCenter, offset = IntOffset(x = 0, y = RowHeight.toInt() * 3),properties = PopupProperties(excludeFromSystemGesture = true), onDismissRequest = {expanded = false}) {
                val columheight = RowHeight * 2.8
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = columheight.dp)
                        .background(color = Color.White),
                ) {

                    if (item_name.isNotEmpty()) {
                        items(
                            categories.filter {
                                it.lowercase()
                                    .contains(item_name.lowercase()) || it.lowercase()
                                    .contains("others")
                            }
                                .sorted()
                        ) {
                            PartsItems(name = it) { name ->
                                item_name = name
                                expanded = false
                            }
                        }
                    } else {
                        items(
                            categories.sorted()
                        ) {
                            PartsItems(name = it) { name ->
                                item_name = name
                                expanded = false
                            }
                        }
                    }



                }
            }
        }
    }

}