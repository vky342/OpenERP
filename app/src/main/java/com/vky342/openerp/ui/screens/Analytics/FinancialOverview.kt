package com.vky342.openerp.ui.screens.Analytics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.ViewModels.Statistics.CashStatsVM
import com.vky342.openerp.ui.screens.HOMES.AutoResizeText
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.item_table_container_colour
import com.vky342.openerp.ui.theme.shadow_color
import com.vky342.openerp.ui.theme.title_color
import com.vky342.openerp.utility.getCurrentMonthYear
import java.util.Calendar


@Preview
@Composable
fun FinancialStats(viewModel: CashStatsVM = hiltViewModel()){

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    val cashInflow = viewModel.totalCashInflow.observeAsState(0.0)
    val cashOutflow = viewModel.totalCashOutflow.observeAsState(0.0)
    val totalPayable = viewModel.totalPendingPayable.observeAsState(0.0)
    val totalReceivable = viewModel.totalPendingReceivable.observeAsState(0.0)
    val monthlyCashInflow = viewModel.monthlyCashInflow.observeAsState()
    val selectedMonthYearInflow = viewModel.selectedMonthYearInflow.observeAsState(getCurrentMonthYear())
    val monthlyCashOutflow = viewModel.monthlyCashOutflow.observeAsState()
    val selectedMonthYearOutflow = viewModel.selectedMonthYearOutflow.observeAsState(getCurrentMonthYear())
    val pendingPayableLedger = viewModel.pendingPayableLedger.observeAsState(listOf<Ledger>())

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
                Text(text = "Financial Overview", fontWeight = FontWeight.Bold,color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            DataStrip("C A S H   I N F L O W", cashInflow.value)
            Spacer(modifier = Modifier.fillMaxWidth().height(5.dp))
            DataStrip("C A S H   O U T F L O W", cashOutflow.value)
            Spacer(modifier = Modifier.fillMaxWidth().height(5.dp))
            DataStrip("P A Y A B L E", totalPayable.value)
            Spacer(modifier = Modifier.fillMaxWidth().height(5.dp))
            DataStrip("R E C E I V A B L E", totalReceivable.value)

//            // pending payables
//            Row(horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .wrapContentHeight()
//                    .fillMaxWidth()
//                    .padding(horizontal = (sidePadding/2).dp, vertical = 2.dp)
//
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
//                ) {
//                    Text(
//                        "P A Y A B L E  L E D G E R S",
//                        fontSize = 16.sp, color = title_color,
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally)
//                    )
//                    PendingPayableLedger(ledgerList = pendingPayableLedger.value)
//                }
//
//            }

            // monthly stats
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(210.dp)
                    .fillMaxWidth()
                    .padding(horizontal = (sidePadding/2).dp)

            ) {
                MonthlyStatCard(selectedMonthYear = selectedMonthYearInflow.value, amount = monthlyCashInflow.value.toString(), monthlyTitle = "Monthly Cash Inflow"){
                    viewModel.selectedMonthYearInflow.value = it
                    viewModel.getMonthlyCashInflow()
                }
            }
            // monthly stats
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(210.dp)
                    .fillMaxWidth()
                    .padding(horizontal = (sidePadding/2).dp, vertical = 2.dp)

            ) {
                MonthlyStatCard(selectedMonthYear = selectedMonthYearOutflow.value, amount = monthlyCashOutflow.value.toString(), monthlyTitle = "Monthly Cash Outflow"){
                    viewModel.selectedMonthYearOutflow.value = it
                    viewModel.getMonthlyCashOutflow()
                }
            }

        }
    }
}

@Preview
@Composable
fun StatBox(modifier: Modifier = Modifier, title: String = "Total Cash Inflow", amount: String = "$12,500") {
    Box(
        modifier = modifier
            .padding(7.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(20f), spotColor = Color.Black)
            .background(color = item_table_container_colour, shape = RoundedCornerShape(20f))
            .height(170.dp)
            .width(160.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top,modifier = Modifier.fillMaxSize(0.93f).align(Alignment.Center)
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 5.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            AutoResizeText(
                text = amount,
                style = TextStyle.Default.copy(fontSize = 25.sp, fontWeight = FontWeight.Bold),
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(start = 5.dp).fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun MonthlyStatCard(
    modifier: Modifier = Modifier,
    selectedMonthYear: String = "06/2025",
    amount: String = "12,400",
    monthlyTitle : String = "Card Title",
    onMonthYearSelected: (String) -> Unit = {},
    ){
    Box(modifier = modifier
        .height(200.dp)
        .width(300.dp)
        .background(color = item_table_container_colour, shape = RoundedCornerShape(35f))) {

        Column(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .align(Alignment.Center)
        ) {
            MonthYearPicker(modifier = Modifier.align(Alignment.CenterHorizontally),selectedMonthYear = selectedMonthYear, onMonthYearSelected = onMonthYearSelected)
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top,modifier = Modifier.fillMaxSize(0.93f).align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = monthlyTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 5.dp)
                )
                Spacer(modifier = Modifier.height(14.dp))
                AutoResizeText(
                    text = "$" + amount,
                    style = TextStyle.Default.copy(fontSize = 25.sp, fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = modifier.padding(start = 5.dp)
                )
            }
        }

    }
}

@Preview
@Composable
fun MonthYearPicker(modifier: Modifier = Modifier,
                    selectedMonthYear: String = "06/2025",
                    onMonthYearSelected: (String) -> Unit = {}
) {
    val months = listOf(
        "01" to "January", "02" to "February", "03" to "March", "04" to "April",
        "05" to "May", "06" to "June", "07" to "July", "08" to "August",
        "09" to "September", "10" to "October", "11" to "November", "12" to "December"
    )

    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val years = (currentYear - 20..currentYear + 20).map { it.toString() }

    val selectedMonth = remember { mutableStateOf(selectedMonthYear.take(2)) }
    val selectedYear = remember { mutableStateOf(selectedMonthYear.takeLast(4)) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp)
    ) {
        // Month Dropdown
        var expandedMonth = remember { mutableStateOf(false) }
        Box {
            OutlinedButton (shape = RoundedCornerShape(35f),
                onClick = { expandedMonth.value = true },
                border =BorderStroke(width = 1.dp, color = Color.White)) {
                Text(months.first { it.first == selectedMonth.value }.second,color = Color.White)
            }
            DropdownMenu(
                expanded = expandedMonth.value,
                onDismissRequest = { expandedMonth.value = false }
            ) {
                months.forEach { (num, name) ->
                    DropdownMenuItem(
                        text = { Text(name) },
                        onClick = {
                            selectedMonth.value = num
                            expandedMonth.value = false
                            onMonthYearSelected("${selectedMonth.value}/${selectedYear.value}")
                        }
                    )
                }
            }
        }

        // Year Dropdown
        val expandedYear = remember { mutableStateOf(false) }
        Box {
            OutlinedButton(shape = RoundedCornerShape(35f),onClick = { expandedYear.value = true },
                border =BorderStroke(width = 1.dp, color = Color.White)) {
                Text(selectedYear.value,color = Color.White)
            }
            DropdownMenu(
                expanded = expandedYear.value,
                onDismissRequest = { expandedYear.value = false }
            ) {
                years.forEach { year ->
                    DropdownMenuItem(
                        text = { Text(year) },
                        onClick = {
                            selectedYear.value = year
                            expandedYear.value = false
                            onMonthYearSelected("${selectedMonth.value}/${selectedYear.value}")
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PendingLedger(modifier: Modifier = Modifier, ledgerList : List<Ledger> = listOf<Ledger>()){
    Box(
        modifier = modifier
            .padding(7.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(20f),
                spotColor = Color.Black
            )
            .background(
                color = item_table_container_colour,
                shape = RoundedCornerShape(20f)
            )
            .heightIn(min = 170.dp)
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row (modifier = modifier.fillMaxWidth()) { Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(topStart = 20f, topEnd = 20f))
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    //Srn
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.1f)

                    ) {
                        Text(
                            text = "Sr",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    // Name
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.25f)

                    ) {
                        Text(
                            text = "Name",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }

                    // balance
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .weight(0.25f)

                    ) {
                        Text(
                            text = "Balance",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
            }
            }

            if (ledgerList.isEmpty()) {
                Text("Zero pending", modifier = Modifier.padding(top = 8.dp), color = Color.Gray)
            } else {
                ledgerList.forEachIndexed { index, ledger ->
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(color = Color.LightGray)
                    )

                    Row (modifier = modifier.fillMaxWidth()) { Box(
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
                            //Srn
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .weight(0.1f)
                                    .background(color = Color.White)
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }

                            // Name
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .weight(0.25f)
                                    .background(color = Color.White)
                            ) {
                                Text(
                                    text = ledger.accountName,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.align(Alignment.CenterStart)
                                )
                            }

                            // balance
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth()
                                    .weight(0.25f)
                                    .background(color = Color.White)
                            ) {
                                Text(
                                    text = "₹%.2f".format(ledger.ledgerNetBalance),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }

                        }
                    } }
                }
            }
        }
    }
}

@Preview
@Composable
fun DataStrip(title: String = "Data", value: Double = 123.45){

    Column(modifier = Modifier.wrapContentSize()) {
        Text(
            title,
            fontSize = 16.sp, color = title_color,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(color = Color.White)){
            Row (horizontalArrangement = Arrangement.Center,modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
                .align(Alignment.Center)){
                Text("₹%.2f".format(value), color = title_color,fontSize = 24.sp,
                    style = TextStyle(
                        shadow = Shadow(
                            color = shadow_color,
                            offset = Offset(0f, 4f),
                            blurRadius = 4f
                        ),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically))
            }
        }
    }
}
