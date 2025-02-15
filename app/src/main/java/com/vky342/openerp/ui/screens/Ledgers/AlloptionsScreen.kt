package com.vky342.openerp.ui.screens.Ledgers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vky342.openerp.ui.Graphs.LedgerScreens
import com.vky342.openerp.ui.screens.ACCOUNTS.new_card


@Composable
fun Alloptionsforledger(
    navController: NavHostController
){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    Column(modifier = Modifier
        .fillMaxSize()) {

        new_card(navController = navController, to_location = LedgerScreens.AccountLedger, main_title = "Account ledger", sub_title = " ledgers of customer/seller/personal accounts", side_icon = Icons.Default.Person)

        new_card(navController = navController, to_location = LedgerScreens.DayBook, main_title = "Day book", sub_title = "Ledger of the day", side_icon = Icons.Default.Menu)

        new_card(navController = navController, to_location = LedgerScreens.YearBook, main_title = "Year book", sub_title = "Ledger of the year", side_icon = Icons.Default.Menu)

    }

}
