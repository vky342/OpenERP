package com.vky342.openerp.ui.screens.Ledgers

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.room.Index
import com.vky342.openerp.data.dataInterface.ledgerItem
import com.vky342.openerp.ui.screens.transactions.PartsItems
import com.vky342.openerp.ui.screens.transactions.list_of_all_account_name
import com.vky342.openerp.ui.theme.Greye
import java.time.LocalDate


@Composable
fun AccountLedgerScreen(){

    /*
    * ledger item {
    * val localIndex, val date , val ledgerType, val amount, val TransactionType, val ID
    * }
    *
    * */

    val sampleData = listOf(
        ledgerItem(
            date = LocalDate.of(2023, 1, 15),
            ledgerType = 1,
            amount = 500,
            transactionType = "sale",
            ForeignId = 101
        ),
        ledgerItem(
            date = LocalDate.of(2023, 2, 20),
            ledgerType = 0,
            amount = 1000,
            transactionType = "purchase",
            ForeignId = 102
        ),
        ledgerItem(
            date = LocalDate.of(2023, 3, 5),
            ledgerType = 1,
            amount = 750,
            transactionType = "payment",
            ForeignId = 103
        ),
        ledgerItem(
            date = LocalDate.of(2023, 4, 10),
            ledgerType = 0,
            amount = 200,
            transactionType = "receipt",
            ForeignId = 104
        ),
        ledgerItem(
            date = LocalDate.of(2023, 5, 18),
            ledgerType = 1,
            amount = 1250,
            transactionType = "Credit",
            ForeignId = 105
        ),
        ledgerItem(
            date = LocalDate.of(2023, 2, 20),
            ledgerType = 0,
            amount = 1000,
            transactionType = "purchase",
            ForeignId = 102
    ),
    ledgerItem(
        date = LocalDate.of(2023, 3, 5),
        ledgerType = 1,
        amount = 750,
        transactionType = "payment",
        ForeignId = 103
    ),
    ledgerItem(
        date = LocalDate.of(2023, 4, 10),
        ledgerType = 0,
        amount = 200,
        transactionType = "receipt",
        ForeignId = 104
    ),
    ledgerItem(
        date = LocalDate.of(2023, 5, 18),
        ledgerType = 1,
        amount = 1250,
        transactionType = "Credit",
        ForeignId = 105
    ),
        ledgerItem(
            date = LocalDate.of(2023, 3, 5),
            ledgerType = 1,
            amount = 750,
            transactionType = "payment",
            ForeignId = 103
        ),
        ledgerItem(
            date = LocalDate.of(2023, 4, 10),
            ledgerType = 0,
            amount = 200,
            transactionType = "receipt",
            ForeignId = 104
        ),
        ledgerItem(
            date = LocalDate.of(2023, 5, 18),
            ledgerType = 1,
            amount = 1250,
            transactionType = "Credit",
            ForeignId = 105
        ),
        ledgerItem(
            date = LocalDate.of(2023, 3, 5),
            ledgerType = 1,
            amount = 750,
            transactionType = "payment",
            ForeignId = 103
        ),
        ledgerItem(
            date = LocalDate.of(2023, 4, 10),
            ledgerType = 0,
            amount = 200,
            transactionType = "receipt",
            ForeignId = 104
        ),
        ledgerItem(
            date = LocalDate.of(2023, 5, 18),
            ledgerType = 1,
            amount = 1250,
            transactionType = "Credit",
            ForeignId = 105
        )
    )

    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1
    val Row_height_for_Billinfo = height.value * 0.1 / 2.4
    val sidePadding = 16.dp

    var accountName by remember {
        mutableStateOf("")
    }

    var accountNameExpander by remember {
        mutableStateOf(false)
    }

    val list_of_ledgerItem = sampleData

    val load_ledger = remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = topPadding.dp, bottom = topPadding.dp)
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Black,
                    Greye,
                    Color.Gray,
                    Color.LightGray
                )
            )
        )
    ) {

        TextField(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(vertical = 8.dp),value = accountName,
            onValueChange = { accountName = it
                            accountNameExpander = true},
            label = { Text(text = "Account") },
            trailingIcon = { IconButton(onClick = {if (accountName in list_of_all_account_name){
                load_ledger.value = true
            } else{
                Log.d("Input Error", "Please enter valid account name")
            }}) { Icon(imageVector = Icons.Outlined.Search, contentDescription = "search")}}
        )

        fieldsInfoRow()

        Column(modifier = Modifier
            .padding(start = sidePadding, end = sidePadding)
            .weight(1f)){
            if (load_ledger.value){
                LedgerColumn(list_of_ledgerItem)
            }
        }


        ledgerFinalDataRow(netBalance = 22000, balanceType = "Debit")

        Spacer(modifier = Modifier.height(8.dp))



        // POP up for auto complete search
        if (accountNameExpander){
            Popup(alignment = Alignment.TopCenter,
                offset = IntOffset(x = 0, y = Row_height_for_Billinfo.toInt() * 5),
                properties = PopupProperties(excludeFromSystemGesture = true),
                onDismissRequest = {accountNameExpander = false}) {
                val columheight = Row_height_for_Billinfo * 4
                val rowWidth = columheight * 2
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = columheight.dp)
                        .width(rowWidth.dp)
                        .background(color = Color.LightGray)
                ) {

                    if (accountName.isNotEmpty()) {
                        items(
                            list_of_all_account_name.filter {
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
                            list_of_all_account_name.sorted()
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


}

@Composable
fun ledgerFinalDataRow(netBalance : Int = 0, balanceType : String = ""){

    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val sidePadding = 16.dp
    val boxHeight = height.value * 0.05

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(boxHeight.dp)
        .padding(end = sidePadding, start = sidePadding)){
        Row (modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.3f))){

            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.5f)){

                Text(fontSize = 15.sp,text = "Current Balance : ", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(0.3f)){

                Text(fontSize = 15.sp,text = netBalance.toString(), color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(0.3f)){

                Text(fontSize = 15.sp,text = balanceType, color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }

        }
    }

}


@Composable
fun fieldsInfoRow(){

    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val sidePadding = 16.dp
    val boxHeight = height.value * 0.05

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(boxHeight.dp)
        .padding(end = sidePadding, start = sidePadding)){
        Row (modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.3f))){

            // index
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.15f)
                .border(0.2.dp, color = Color.White)){

                Text(fontSize = 15.sp,text = "Sn.", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }

            // date
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.3f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = "Date", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }

            // TransactionType
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.25f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = "Voucher", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }

            // LedgerType
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.18f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = "D/C", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }

            // amount
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.2f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = "Amount", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }

        }
    }
}

@Composable
fun LedgerColumn(list_of_ledgerItem : List<ledgerItem>){
    val sidePadding = 16.dp
    LazyColumn() {
        itemsIndexed(items = list_of_ledgerItem){ index, item ->
            LedgerItemCard(index = index + 1, item = item)
            }
        }

}


@Composable
fun LedgerItemCard(index: Int, item: ledgerItem){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val boxHeight = height.value * 0.05

    val date  = item.date

    val transactionType = item.transactionType

    val ledgerType = item.ledgerType

    val amount = item.amount

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Black.copy(0.2f))
        .height(boxHeight.dp)){

        Row {

            // index
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.15f)
                .border(0.2.dp, color = Color.White)){

                Text(fontSize = 14.sp,text = index.toString(), color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }

            // date
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.3f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = date.toString(), color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }

            // TransactionType
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.25f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = transactionType, color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }

            // LedgerType
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.18f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = if(ledgerType == 0){"Debit"}else{"Credit"}, color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }

            // amount
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.2f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = amount.toString(), color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }


        }


    }
}