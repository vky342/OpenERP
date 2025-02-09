package com.vky342.openerp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vky342.openerp.ui.Graphs.Graph
import com.vky342.openerp.ui.Graphs.RootNavigationGraph


data class BottomNavigationItem(
    val title: String,
    val selectedIcon : ImageVector,
    val unSelectedIcon : ImageVector,
    val Route : String

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(navController: NavHostController = rememberNavController()){

    val items = listOf(
        BottomNavigationItem(
        title = "Home",
            selectedIcon = Icons.Default.Home,
            unSelectedIcon = Icons.Outlined.Home,
            Route = Graph.HOME
    ),
        BottomNavigationItem(
            title = "Accounts",
            selectedIcon = Icons.Default.Person,
            unSelectedIcon = Icons.Outlined.Person,
            Route = Graph.ACCOUNT
        ),
        BottomNavigationItem(
            title = "Ledger",
            selectedIcon = Icons.Default.Edit,
            unSelectedIcon = Icons.Outlined.Edit,
            Route = Graph.LEDGER
        ),
        BottomNavigationItem(
            title = "Bills",
            selectedIcon = Icons.Default.ShoppingCart,
            unSelectedIcon = Icons.Outlined.ShoppingCart,
            Route = Graph.TRANSACTION
        ),

    )

    var selectedIcon by rememberSaveable { mutableStateOf(0) }

    Scaffold (topBar = { TopAppBar(
        title = { Text("OpenERP")},
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.White),
        actions = {
            IconButton(onClick = {}){
                Icon(Icons.Default.Notifications, contentDescription = "")
            }
            IconButton(onClick = {}){
                Icon(Icons.Default.DateRange, contentDescription = "")
            }
            IconButton(onClick = {}){
                Icon(Icons.Default.Menu, contentDescription = "")
            }
        }
    ) }, containerColor = Color.White,
        bottomBar = { NavigationBar (containerColor = Color.White, contentColor = Color.Black) {
            items.forEachIndexed(){index, item ->
                NavigationBarItem(
                    selected = selectedIcon == index,
                    onClick = {
                        selectedIcon = index
                        navController.navigate(item.Route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    label = {
                        Text(text = item.title)
                    },
                    icon = {
                        Icon(imageVector = if (index == selectedIcon){item.selectedIcon} else{item.unSelectedIcon}, contentDescription = "")
                    }
                )
            }
        } }
    ){
        Box (modifier = Modifier.fillMaxSize().padding(paddingValues = it)){
            RootNavigationGraph(navController = navController)
        }

    }
}
