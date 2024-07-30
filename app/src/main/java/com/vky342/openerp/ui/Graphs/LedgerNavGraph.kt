package com.vky342.openerp.ui.Graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vky342.openerp.ui.screens.Ledgers.AccountLedgerScreen
import com.vky342.openerp.ui.screens.Ledgers.Alloptionsforledger

fun NavGraphBuilder.LedgerNavigationGraph(navController: NavHostController){
    navigation(route = Graph.LEDGER, startDestination = LedgerScreens.Alloptions){

        composable(route = LedgerScreens.Alloptions){
            Alloptionsforledger(navController = navController)
        }
        composable(route = LedgerScreens.AccountLedger){
            AccountLedgerScreen()
        }
    }
}

object LedgerScreens{
    val Alloptions = "alloptionsr"
    val AccountLedger = "accountLedger"
}