package com.vky342.openerp.ui.screens.Analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.ViewModels.Statistics.LedgerOverviewVM
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.title_color

@Composable
fun LedgerOverview(viewModel : LedgerOverviewVM = hiltViewModel()){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    val pendingPayableLedger = viewModel.pendingPayableLedger.observeAsState(listOf<Ledger>())
    val pendingReceivableLedger = viewModel.pendingReceivableLedger.observeAsState(listOf<Ledger>())

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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(50.dp)
            ) {
                Text(text = "Ledger Overview", fontWeight = FontWeight.Bold,color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            // pending payables
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = (sidePadding/2).dp, vertical = 2.dp)

            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                ) {
                    Text(
                        "P A Y A B L E  L E D G E R",
                        fontSize = 16.sp, color = title_color,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                   PendingLedger(ledgerList = pendingPayableLedger.value)
                }

            }
            // pending receivable
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = (sidePadding/2).dp, vertical = 2.dp)

            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                ) {
                    Text(
                        "R E C E I V A B L E  L E D G E R",
                        fontSize = 16.sp, color = title_color,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    PendingLedger(ledgerList = pendingReceivableLedger.value)
                }

            }
        }
    }
}