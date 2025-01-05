package com.vky342.openerp.ui.Graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavigationGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Graph.HOME, route = Graph.ROOT){
        HomeNavigationGraph(navController = navController)
        AccountNavigationGraph(navController = navController)
        TransactionNavGraph(navController = navController)
        LedgerNavigationGraph(navController = navController)
        InvontoryNavigationGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "Root_graph"
    const val HOME = "Home_graph"
    const val ACCOUNT = "Account_graph"
    const val TRANSACTION = "Transaction_graph"
    const val LEDGER = "Ledger_graph"
    const val INVENTORY = "Inventory_graph"
}

