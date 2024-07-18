package com.vky342.openerp.ui.Graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vky342.openerp.ui.screens.transactions.AddSaleScreen
import com.vky342.openerp.ui.screens.transactions.AllTransactionOptionsScreen

fun NavGraphBuilder.TransactionNavGraph(navController: NavHostController){
    navigation(route = Graph.TRANSACTION,
        startDestination = TransactionScreen.AllOption){
        composable(route = TransactionScreen.AllOption){
            AllTransactionOptionsScreen(navController)
        }
        composable(route = TransactionScreen.AddSale){
            AddSaleScreen(navController)
        }
    }
}


object TransactionScreen{
    val AllOption = "allOption"
    val AddSale = "addSale"
    val AddPurchase = "addPurchase"
    val modifySale = "modifySale"
    val modifyPurchase = "modifyPurchase"
    val deleteSale = "deleteSale"
    val deletePurchase = "deletePurchase"
}