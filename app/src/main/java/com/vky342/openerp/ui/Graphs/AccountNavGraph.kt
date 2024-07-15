package com.vky342.openerp.ui.Graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vky342.openerp.ui.screens.ACCOUNTS.AddA_Account_Screen
import com.vky342.openerp.ui.screens.ACCOUNTS.AllOptionsScreen
import com.vky342.openerp.ui.screens.ACCOUNTS.DeleteScreen
import com.vky342.openerp.ui.screens.ACCOUNTS.ListAllAccountsScreen
import com.vky342.openerp.ui.screens.ACCOUNTS.ModifyAccountScreen

fun NavGraphBuilder.AccountNavigationGraph(navController: NavHostController){
    navigation(route = Graph.ACCOUNT,
        startDestination = AccountScreens.ALLOPTIONS){
        composable(route = AccountScreens.ALLOPTIONS){
            AllOptionsScreen(navController)
        }
        composable(route = AccountScreens.ADD){
            AddA_Account_Screen()
        }
        composable(route = AccountScreens.DELETE){
            DeleteScreen()
        }
        composable(route = AccountScreens.MODIFY){
            ModifyAccountScreen()
        }
        composable(route = AccountScreens.LIST){
            ListAllAccountsScreen()
        }
    }
}


object AccountScreens {
    const val ALLOPTIONS = "Alloptions_graph"
    const val ADD = "Add_screen"
    const val MODIFY = "Modify_screen"
    const val DELETE = "Delete_screen"
    const val LIST = "List_screen"
}