package com.vky342.openerp.ui.Graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vky342.openerp.ui.screens.Analytics.Analytics_home
import com.vky342.openerp.ui.screens.Analytics.FinancialStats
import com.vky342.openerp.ui.screens.Analytics.LedgerOverview
import com.vky342.openerp.ui.screens.HOMES.HomeScreen

fun NavGraphBuilder.AnalyticsNavigationGraph(navController: NavHostController){
    navigation(startDestination = AnalyticsScreens.analyticsHome, route = Graph.ANALYTICS){
        composable(route = AnalyticsScreens.analyticsHome){
            Analytics_home(navController)
        }
        composable(route = AnalyticsScreens.financialOverview){
            FinancialStats()
        }
        composable(route = AnalyticsScreens.salesOverview){

        }
        composable(route = AnalyticsScreens.ledgersOverview){
            LedgerOverview()
        }
    }
}

object AnalyticsScreens {
    val analyticsHome = "analyticsHome"
    val financialOverview = "financialOverview"
    val salesOverview = "saleOverview"
    val ledgersOverview = "ledgersOverview"
}