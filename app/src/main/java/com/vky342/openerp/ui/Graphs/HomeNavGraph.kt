package com.vky342.openerp.ui.Graphs

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vky342.openerp.data.ViewModels.HomeViewModel
import com.vky342.openerp.ui.screens.HOMES.HomeScreen


fun NavGraphBuilder.HomeNavigationGraph(navController: NavHostController){
    navigation(startDestination = "HomeScreen", route = Graph.HOME){
        composable(route = "HomeScreen"){
            HomeScreen(navController)
        }
    }
}


