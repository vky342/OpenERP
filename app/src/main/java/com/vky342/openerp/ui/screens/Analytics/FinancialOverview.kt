package com.vky342.openerp.ui.screens.Analytics

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.ViewModels.Statistics.CashStatsVM
import com.vky342.openerp.data.ViewModels.Statistics.SaleStatsVM
import com.vky342.openerp.ui.screens.HOMES.AutoResizeText
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.item_table_container_colour


@Preview
@Composable
fun FinancialStats(viewModel: CashStatsVM = hiltViewModel()){

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    val cashInflow = viewModel.totalCashInflow.observeAsState()
    val cashOutflow = viewModel.totalCashOutflow.observeAsState()
    val totalPayable = viewModel.totalPendingPayable.observeAsState()
    val totalReceivable = viewModel.totalPendingReceivable.observeAsState()

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
            // Stats
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding( horizontal = (sidePadding/2).dp)

            ) {
                StatBox(title = "Total Cash Inflow", amount = "₹" + cashInflow.value.toString())
                StatBox(title = "Total Cash Outflow", amount = "₹" + cashOutflow.value.toString())
            }
            // Stats
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(horizontal = (sidePadding/2).dp)

            ) {
                StatBox(title = "Total Payable", amount = "₹" + totalPayable.value.toString())
                StatBox(title = "Total Receivable", amount = "₹" + totalReceivable.value.toString())
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