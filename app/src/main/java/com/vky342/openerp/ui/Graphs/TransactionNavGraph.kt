package com.vky342.openerp.ui.Graphs


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vky342.openerp.ui.screens.transactions.AddPurchaseScreen
import com.vky342.openerp.ui.screens.transactions.AddReceipt
import com.vky342.openerp.ui.screens.transactions.AddSaleScreen
import com.vky342.openerp.ui.screens.transactions.AllTransactionOptionsScreen
import com.vky342.openerp.ui.screens.transactions.addPayment
import com.vky342.openerp.ui.screens.transactions.modifySaleScreen

fun NavGraphBuilder.TransactionNavGraph(navController: NavHostController){
    navigation(route = Graph.TRANSACTION,
        startDestination = TransactionScreen.AllOption){

        composable(route = TransactionScreen.AllOption){
            AllTransactionOptionsScreen(navController)
        }
        composable(route = TransactionScreen.AddSale){
            AddSaleScreen()
        }
        composable(route = TransactionScreen.modifySale){
            modifySaleScreen()
        }
        composable(route = TransactionScreen.AddPurchase){
            AddPurchaseScreen()
        }
        composable ( route = TransactionScreen.addPayment){
            addPayment()
        }
        composable ( route = TransactionScreen.addReceipt){
            AddReceipt()
        }

    }
}


object TransactionScreen{
    val AllOption = "allOption"
    val AddSale = "addSale"
    val AddPurchase = "addPurchase"
    val modifySale = "modifySale"
    val addPayment = "Payment"
    val addReceipt = "Receipt"
}