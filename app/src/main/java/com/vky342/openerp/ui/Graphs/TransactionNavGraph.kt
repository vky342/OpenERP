package com.vky342.openerp.ui.Graphs


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vky342.openerp.ui.screens.transactions.AddPurchaseScreen
import com.vky342.openerp.ui.screens.transactions.AddSaleScreen
import com.vky342.openerp.ui.screens.transactions.AllTransactionOptionsScreen
import com.vky342.openerp.ui.screens.transactions.addPayment
import com.vky342.openerp.ui.screens.transactions.modifyBillScreen

fun NavGraphBuilder.TransactionNavGraph(navController: NavHostController){
    navigation(route = Graph.TRANSACTION,
        startDestination = TransactionScreen.AllOption){

        composable(route = TransactionScreen.AllOption){
            AllTransactionOptionsScreen(navController)
        }
        composable(route = TransactionScreen.AddSale){
            AddSaleScreen()
        }
        composable(route = TransactionScreen.modifyBill){
            modifyBillScreen()
        }
        composable(route = TransactionScreen.AddPurchase){
            AddPurchaseScreen()
        }
        composable ( route = TransactionScreen.addPayment){
            addPayment()
        }
    }
}


object TransactionScreen{
    val AllOption = "allOption"
    val AddSale = "addSale"
    val AddPurchase = "addPurchase"
    val modifyBill = "modifySale"
    val addPayment = "Payment"
}