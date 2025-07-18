package com.vky342.openerp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vky342.openerp.ui.Graphs.Graph
import com.vky342.openerp.ui.Graphs.RootNavigationGraph
import com.vky342.openerp.ui.screens.ACCOUNTS.ModifyAccountScreen
import com.vky342.openerp.ui.theme.Typography
import com.vky342.openerp.ui.theme.action_icon_color
import com.vky342.openerp.ui.theme.bottom_app_bar
import com.vky342.openerp.ui.theme.bottom_bar_content_color
import com.vky342.openerp.ui.theme.bottom_bar_selected_icon_color
import com.vky342.openerp.ui.theme.bottom_bar_selected_indicator_color
import com.vky342.openerp.ui.theme.bottom_bar_selected_txt_color
import com.vky342.openerp.ui.theme.bottom_bar_unselected_icon_color
import com.vky342.openerp.ui.theme.bottom_bar_unselected_txt_color
import com.vky342.openerp.ui.theme.root_container_color
import com.vky342.openerp.ui.theme.title_color
import com.vky342.openerp.ui.theme.top_app_bar


data class BottomNavigationItem(
    val title: String,
    val selectedIcon : ImageVector,
    val unSelectedIcon : ImageVector,
    val Route : String

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(navController: NavHostController = rememberNavController()){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

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
        )
    )

    var selectedIcon by rememberSaveable { mutableStateOf(0) }

    Scaffold (topBar = { TopAppBar(
        title = { Text("OpenERP", style = Typography.titleLarge) },
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = top_app_bar, titleContentColor = title_color, actionIconContentColor = action_icon_color),
    ) }, containerColor = root_container_color,

        bottomBar = { NavigationBar (containerColor = bottom_app_bar, contentColor = bottom_bar_content_color) {
            items.forEachIndexed(){index, item ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors().copy(selectedIconColor = bottom_bar_selected_icon_color, selectedTextColor = bottom_bar_selected_txt_color, selectedIndicatorColor = bottom_bar_selected_indicator_color, unselectedIconColor = bottom_bar_unselected_icon_color, unselectedTextColor = bottom_bar_unselected_txt_color),
                    selected = currentDestination?.hierarchy?.any { it.route == item.Route } == true,
                    onClick = {
                        selectedIcon = index
                        navController.navigate(item.Route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = {
                        Text(text = item.title, style = Typography.labelSmall)
                    },
                    icon = {
                        Icon(imageVector = if (index == selectedIcon){item.selectedIcon} else{item.unSelectedIcon}, contentDescription = "", modifier = Modifier.size(25.dp))
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
