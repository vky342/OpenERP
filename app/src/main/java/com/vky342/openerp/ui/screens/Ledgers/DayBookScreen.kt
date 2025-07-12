package com.vky342.openerp.ui.screens.Ledgers

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.vky342.openerp.data.Entities.GeneralDataClasses.DayBookVoucher
import com.vky342.openerp.data.Repositories.dayBookItem
import com.vky342.openerp.data.ViewModels.Ledger.DayBookVM
import com.vky342.openerp.ui.screens.transactions.DatePickerComposable
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.title_color
import com.vky342.openerp.utility.getTodayDate

@Preview
@Composable
fun DayBookScreen(viewModel: DayBookVM = hiltViewModel()){
    val context : Context = LocalContext.current
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    val selectedDate = viewModel.selectedDate.observeAsState(getTodayDate())
    val dayBookList = viewModel.dayBookList.observeAsState(listOf<dayBookItem>())

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
                Text(text = "Select date", fontWeight = FontWeight.Bold,color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            DatePickerComposable(
                enabled = true,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxHeight()
                    .fillMaxWidth(0.9f),
                label = "Date",
                value = selectedDate.value,
                onDateSelected = {
                    viewModel.selectedDate.value = it
                    viewModel.getDayBookList()
                })

            Box(modifier = Modifier.fillMaxWidth().height(40.dp)){
                Text(
                    "D A Y  B O O K",
                    fontSize = 20.sp, color = title_color,
                    modifier = Modifier
                        .padding(horizontal = sidePadding.dp)
                        .align(Alignment.Center)
                )
            }

            // List
            Box(modifier = Modifier.fillMaxWidth().heightIn(max = 750.dp).padding(vertical = 10.dp)) {
                DayBook_list_table(modifier = Modifier.padding(horizontal = (sidePadding/2).dp),LegderItemList = dayBookList.value)
            }
        }
    }
}


@Preview
@Composable
fun DayBook_list_table(modifier: Modifier = Modifier, LegderItemList : List<dayBookItem> = listOf()){

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(bottomStart = 20f, bottomEnd = 20f)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {

                // Bill by name
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.25f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Account", fontWeight = FontWeight.Bold,modifier = Modifier.align(Alignment.Center))
                }

                // Bill Type
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.25f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Bill", fontWeight = FontWeight.Bold,modifier = Modifier.align(Alignment.Center))
                }

                // Amount
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.25f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Amount",fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
        LazyColumn(reverseLayout = true,modifier = Modifier.fillMaxWidth()){
            itemsIndexed(LegderItemList){ index, item ->
                DayBookSingleRow(
                    item = item
                )

            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.LightGray)
        )
    }
}


@Preview
@Composable
fun DayBookSingleRow (modifier: Modifier = Modifier, item : dayBookItem = dayBookItem("kunal","Purchase",1000.00, CashOrCredit = "Cash")) {

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color.LightGray)
    )

    Row(modifier = modifier.fillMaxWidth().height(90.dp)) {

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {

            Column (modifier = Modifier.fillMaxHeight().fillMaxWidth()){
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    // Bill By name.
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.25f)
                            .background(color = Color.White)
                    ) {
                        Text(
                            text = item.accountName,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    // Bill Type
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.25f)
                            .background(color = Color.White)
                    ) {
                        Text(
                            text = item.BillType,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    // Bill Amount
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.25f)
                            .background(color = Color.White)
                    ) {
                        Text(
                            text = "₹" + item.BillAmount.toString(),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxWidth()
                ) {
                    // Cash/Credit
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.7f)
                            .background(color = Color.White)
                    ) {
                        Text(
                            text =  "~ " + item.CashOrCredit,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.align(Alignment.CenterStart)
                                .padding(start = 12.dp)
                        )
                    }
                }
            }

        }
    }
}

