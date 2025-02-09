package com.vky342.openerp.ui.Graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vky342.openerp.ui.screens.Ledgers.AccountLedgerScreen
import com.vky342.openerp.ui.screens.Ledgers.Alloptionsforledger
import com.vky342.openerp.ui.screens.Ledgers.DayBookScreen

fun NavGraphBuilder.LedgerNavigationGraph(navController: NavHostController){
    navigation(route = Graph.LEDGER, startDestination = LedgerScreens.Alloptions){

        composable(route = LedgerScreens.Alloptions){
            Alloptionsforledger(navController = navController)
        }
        composable(route = LedgerScreens.AccountLedger){
            AccountLedgerScreen()
        }
        composable(route = LedgerScreens.DayBook){
            DayBookScreen()
        }
        composable(route = LedgerScreens.YearBook){

        }
    }
}

object LedgerScreens{
    val Alloptions = "alloptions"
    val AccountLedger = "accountLedger"
    val DayBook = "daybook"
    val YearBook = "yearbook"
}