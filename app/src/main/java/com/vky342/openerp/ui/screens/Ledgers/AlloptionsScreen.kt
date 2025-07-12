package com.vky342.openerp.ui.screens.Ledgers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vky342.openerp.ui.Graphs.AnalyticsScreens
import com.vky342.openerp.ui.Graphs.LedgerScreens
import com.vky342.openerp.ui.screens.ACCOUNTS.new_card
import com.vky342.openerp.ui.screens.Analytics.ButtonCard
import com.vky342.openerp.ui.theme.background_color


@Composable
fun Alloptionsforledger(
    navController: NavHostController
){

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

            ButtonCard(icon = Icons.Default.Person,mainText = "Account Ledger", subText = "ledgers of customer/seller/personal accounts",onClick = {
                navController.navigate(LedgerScreens.AccountLedger)
            })

            ButtonCard(icon = Icons.Default.Menu,mainText = "Day book", subText = "Ledger of the day",onClick = {
                navController.navigate(LedgerScreens.DayBook)
            })

//            ButtonCard(icon = Icons.Default.Menu,mainText = "Year book", subText = "Ledger of the year",onClick = {
//                navController.navigate(LedgerScreens.YearBook)
//            })  TO BE implemented later.

        }
    }

}
