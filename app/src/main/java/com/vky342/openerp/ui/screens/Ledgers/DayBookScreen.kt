package com.vky342.openerp.ui.screens.Ledgers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Index
import com.vky342.openerp.data.dataInterface.DayBookVoucher
import com.vky342.openerp.data.dataInterface.ledgerItem
import com.vky342.openerp.ui.theme.Greye

@Preview
@Composable
fun DayBookScreen(){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1
    val sidePadding = 16.dp

    val sampleData = listOf(
        DayBookVoucher("John Doe", "Sale", 1500),
        DayBookVoucher("Jane Smith", "Purchase", 2000),
        DayBookVoucher("Alice Johnson", "Payment", 500),
        DayBookVoucher("Robert Brown", "Receipt", 3000),
        DayBookVoucher("Emily Davis", "Sale", 700),
        DayBookVoucher("Michael Wilson", "Purchase", 2500),
        DayBookVoucher("Sarah Miller", "Payment", 100),
        DayBookVoucher("David Moore", "Receipt", 4000),
        DayBookVoucher("Laura Taylor", "Sale", 1200),
        DayBookVoucher("James Anderson", "Purchase", 800),
        DayBookVoucher("Linda Thomas", "Payment", 15000),
        DayBookVoucher("Kevin Jackson", "Receipt", 2200),
        DayBookVoucher("Sophia White", "Sale", 5000),
        DayBookVoucher("Daniel Harris", "Purchase", 900),
        DayBookVoucher("Olivia Martin", "Payment", 1100),
        DayBookVoucher("Matthew Tho", "Receipt", 13000),
        DayBookVoucher("Chloe Garcia", "Sale", 600),
        DayBookVoucher("Anthony Marti", "Purchase", 550),
        DayBookVoucher("Grace Robinson", "Payment", 1200),
        DayBookVoucher("Andrew Clark", "Receipt", 750),
        DayBookVoucher("James Anderson", "Purchase", 800),
        DayBookVoucher("Linda Thomas", "Payment", 15000),
        DayBookVoucher("Kevin Jackson", "Receipt", 2200),
        DayBookVoucher("Sophia White", "Sale", 5000),
        DayBookVoucher("Daniel Harris", "Purchase", 900),
        DayBookVoucher("Olivia Martin", "Payment", 1100),
        DayBookVoucher("Matthew Tho", "Receipt", 13000),
        DayBookVoucher("Chloe Garcia", "Sale", 600),
        DayBookVoucher("Anthony Marti", "Purchase", 550),
        DayBookVoucher("Grace Robinson", "Payment", 1200),
        DayBookVoucher("Andrew Clark", "Receipt", 750)
    )

    val list_of_Voucher : List<DayBookVoucher> = listOf()

    Column(modifier = Modifier
        .padding(top = topPadding.dp, bottom = topPadding.dp)
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Black,
                    Greye,
                    Color.Gray,
                    Color.LightGray,
                    Color.Gray,
                    Greye,
                    Color.Black
                )
            )
        )) {

        infoBar()

        Column(modifier = Modifier
            .padding(start = sidePadding, end = sidePadding)
            .weight(1f)) {
            DayBookColumn(list_of_DayBookVoucher = sampleData)
        }
        
        bottom_Day_status_bar()

    }
}


@Composable
fun infoBar() {
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val infoBarHeight = height.value * 0.03
    val sidePadding = 16.dp
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, end = sidePadding, start = sidePadding)
        .height(infoBarHeight.dp)
        .border(0.2.dp, color = Color.White)
        ){
        Row (modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.3f))){

            // title
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.15f)){

                Text(fontSize = 15.sp,text = "Day Book : ", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }
            // date data
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.15f)){

                Text(fontSize = 15.sp,text = "25-11-24", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }

        }
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp)
        .height(infoBarHeight.dp)
        .padding(end = sidePadding, start = sidePadding)){
        Row (modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.3f))){

            // index
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.125f)
                .border(0.2.dp, color = Color.White)){

                Text(fontSize = 15.sp,text = "Sn.", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }

            // account name
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.3f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = "account", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }
            // voucher type
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.17f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = "voucher", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }
            // amount
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.2f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 15.sp,text = "amount", color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
fun bottom_Day_status_bar(){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val infoBarHeight = height.value * 0.03
    val sidePadding = 16.dp

    Column (modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)){
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = sidePadding)
            .border(0.2.dp, color = Color.White)
            .height(infoBarHeight.dp)
        ){
            Row (modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.3f))){

                // sale
                Box (modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.3f)){

                    Text(fontSize = 13.sp,text = "sale : ", color = Color.White, maxLines = 1, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                    )

                }
                // sale data
                Box (modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.4f)){

                    Text(fontSize = 13.sp,text = "500", color = Color.White, maxLines = 1, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                    )

                }
                // purchase
                Box (modifier = Modifier
                    .fillMaxHeight()
                    .weight(.3f)){

                    Text(fontSize = 13.sp,text = "payment : ", color = Color.White, maxLines = 1, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                    )

                }
                // purchase data
                Box (modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.4f)){

                    Text(fontSize = 13.sp,text = "500", color = Color.White, maxLines = 1, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                    )

                }

            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = sidePadding)
            .border(0.2.dp, color = Color.White)
            .height(infoBarHeight.dp)
        ){
            Row (modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.3f))){
                // payment
                Box (modifier = Modifier
                    .fillMaxHeight()
                    .weight(.3f)){

                    Text(fontSize = 13.sp,text = "purchase : ", color = Color.White, maxLines = 1, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                    )

                }
                // payment data
                Box (modifier = Modifier
                    .fillMaxHeight()
                    .weight(.4f)){

                    Text(fontSize = 13.sp,text = "50000", color = Color.White, maxLines = 1, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                    )

                }
                // receipt
                Box (modifier = Modifier
                    .fillMaxHeight()
                    .weight(.3f)){

                    Text(fontSize = 13.sp,text = "receipt : ", color = Color.White, maxLines = 1, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                    )

                }
                // receipt data
                Box (modifier = Modifier
                    .fillMaxHeight()
                    .weight(.4f)){

                    Text(fontSize = 13.sp,text = "500", color = Color.White, maxLines = 1, modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                    )

                }

            }
        }
    }

}


@Composable
fun day_Book_Item(index: Int, dayBookVoucher: DayBookVoucher){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val boxHeight = height.value * 0.04

    val account = dayBookVoucher.accountName

    val VoucherType = dayBookVoucher.voucher_type

    val amount = dayBookVoucher.voucher_amount

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Black.copy(0.2f))
        .height(boxHeight.dp)){

        Row {

            // index
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.125f)
                .border(0.2.dp, color = Color.White)){

                Text(fontSize = 12.sp,text = index.toString(), color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )

            }

            // account
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.3f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 12.sp,text = account, color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }

            // VoucherType
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.17f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 12.sp,text = VoucherType, color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }

            // amount
            Box (modifier = Modifier
                .fillMaxHeight()
                .weight(.2f)
                .border(0.2.dp, color = Color.White)){
                Text(fontSize = 12.sp,text = amount.toString(), color = Color.White, maxLines = 1, modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
                )
            }


        }


    }
}

@Composable
fun DayBookColumn(list_of_DayBookVoucher : List<DayBookVoucher>){
    LazyColumn() {
        itemsIndexed(items = list_of_DayBookVoucher){ index, item ->
            day_Book_Item(index, item)
        }
    }

}