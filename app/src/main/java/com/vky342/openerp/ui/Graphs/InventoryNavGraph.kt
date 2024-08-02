package com.vky342.openerp.ui.Graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vky342.openerp.ui.screens.Inventory.InventoryList
import com.vky342.openerp.ui.screens.Inventory.ItemEdit
import com.vky342.openerp.ui.screens.Inventory.inventoryOptionsScreen

fun NavGraphBuilder.InvontoryNavigationGraph(navController: NavHostController){
    navigation(startDestination = InventoryScreens.InventoryOptions, route = Graph.INVENTORY){
        composable(route = InventoryScreens.InventoryOptions){
            inventoryOptionsScreen(navController)
        }
        composable(route = InventoryScreens.InvontoryList){
            InventoryList()
        }
        composable(route = InventoryScreens.ItemEdit){
            ItemEdit()
        }
    }
}

object InventoryScreens {
    val InventoryOptions = "inventoryOptions"
    val InvontoryList = "inventoryList"
    val ItemEdit = "itemEdit"
}