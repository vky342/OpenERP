package com.vky342.openerp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vky342.openerp.ui.Graphs.Graph
import com.vky342.openerp.ui.Graphs.RootNavigationGraph
import com.vky342.openerp.ui.theme.Greye


@Composable
fun RootScreen(navController: NavHostController = rememberNavController()){
    Scaffold (topBar = { TopAppBar() }, containerColor = Color.White,
        bottomBar = { BottomBar(navController = navController) }
    ){
        RootNavigationGraph(navController = navController, paddingValues = it)
    }
}


@Composable
fun BottomBar(navController: NavHostController){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1
    val IconHeight = topPadding/3

    var HomeIcon = Icons.Outlined.Home
    var HomeIconColor = Color.Black

    var AccountIcon = Icons.Outlined.Person
    var AccountColor = Color.Black

    var Transicon = Icons.Outlined.ShoppingCart
    var TransColor = Color.Black

    var Ledger = Icons.Outlined.Edit
    var ledgerColor = Color.Black

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    fun colorFilling(){
        if (currentDestination?.parent?.route == Graph.HOME){
            HomeIcon = Icons.Filled.Home
            HomeIconColor = Greye
        }
        else if (currentDestination?.parent?.route == Graph.ACCOUNT){
            AccountIcon = Icons.Filled.Person
            AccountColor = Greye
        }
        else if (currentDestination?.parent?.route == Graph.TRANSACTION){
            Transicon = Icons.Filled.ShoppingCart
            TransColor = Greye
        }
        else if (currentDestination?.parent?.route == Graph.LEDGER){
            Ledger = Icons.Filled.Edit
            ledgerColor = Greye
        }
        else {
            HomeIcon = Icons.Filled.Home
            HomeIconColor = Greye
        }
    }

    colorFilling()

    BottomAppBar(modifier = Modifier.height(topPadding.dp), containerColor = Color.White){
        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp), contentAlignment = Alignment.Center){

            Row (modifier = Modifier.wrapContentSize()){

                Column(modifier = Modifier.fillMaxHeight().weight(1f)) {

                    Box(modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)){

                        Column(modifier = Modifier.fillMaxHeight().align(Alignment.Center)) {
                            Icon(imageVector = HomeIcon, tint = HomeIconColor, contentDescription = "home", modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .size(IconHeight.dp)
                                .clickable(onClick = {
                                    navController.navigate(Graph.HOME) {
                                        popUpTo(navController.graph.findStartDestination().id)
                                        launchSingleTop = true
                                    }
                                })
                            )
                            Text(text = "Home", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(weight = 700), modifier = Modifier.align(Alignment.CenterHorizontally))
                        }
                    }

                }

                Column (modifier = Modifier.fillMaxHeight().weight(1f)){

                    Box(modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)){

                        Column(modifier = Modifier.fillMaxHeight().align(Alignment.Center)) {

                            Icon(imageVector = AccountIcon, tint = AccountColor, contentDescription = "Account", modifier = Modifier
                                .size(IconHeight.dp)
                                .align(Alignment.CenterHorizontally)
                                .clickable(onClick = {

                                    navController.navigate(Graph.ACCOUNT) {
                                        popUpTo(navController.graph.findStartDestination().id)
                                        launchSingleTop = true
                                    }
                                }))
                            Text(text = "Account", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(700), modifier = Modifier.align(Alignment.CenterHorizontally))

                        }
                    }

                }

                Column (modifier = Modifier.fillMaxHeight().weight(1f)){

                    Box(modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)){

                        Column(modifier = Modifier.fillMaxHeight().align(Alignment.Center)) {
                            Icon(imageVector = Ledger, tint = ledgerColor, contentDescription = "ADD Sale", modifier = Modifier
                                .size(IconHeight.dp)
                                .align(Alignment.CenterHorizontally)
                                .clickable(onClick = {
                                    navController.navigate(Graph.LEDGER) {
                                        popUpTo(navController.graph.findStartDestination().id)
                                        launchSingleTop = true
                                    }
                                }))

                            Text(text = "Ledger", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(700), modifier = Modifier.align(Alignment.CenterHorizontally))
                        }

                    }

                }

                Column (modifier = Modifier.fillMaxHeight().weight(1f)){


                    Box(modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)){

                        Column(modifier = Modifier.fillMaxHeight().align(Alignment.Center)) {
                            Icon(imageVector = Transicon, tint = TransColor, contentDescription = "ADD Sale", modifier = Modifier
                                .size(IconHeight.dp)
                                .align(Alignment.CenterHorizontally)
                                .clickable(onClick = {

                                    navController.navigate(Graph.TRANSACTION) {
                                        popUpTo(navController.graph.findStartDestination().id)
                                        launchSingleTop = true
                                    }
                                }))

                            Text(text = "Bills", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(700), modifier = Modifier.align(Alignment.CenterHorizontally))


                        }
                    }
                }



            }


        }


    }
}

@Preview
@Composable
fun TopAppBar(){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp}
    val sidePadding = width.value * 0.05
    val topPadding = height.value * 0.1
    val statusBar = topPadding * 0.3
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .height(topPadding.dp)){
        Text(fontSize = 34.sp, color = Greye, fontWeight = FontWeight(500),text = "OpenERP", modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(horizontal = sidePadding.dp)
            .padding(top = statusBar.dp))

        Box(modifier = Modifier
            .wrapContentWidth()
            .height(topPadding.dp)
            .background(color = Color.White)
            .align(Alignment.CenterEnd)
            .padding(horizontal = sidePadding.dp)
            ){

            Row (
                modifier = Modifier.wrapContentWidth().align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(top = statusBar.dp))



                Icon(imageVector = Icons.Default.DateRange, contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(top = statusBar.dp))



                Icon(imageVector = Icons.Default.Menu, contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(top = statusBar.dp))
            }

        }


    }

}

@Preview
@Composable
fun bottombar_test() {

    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1
    val IconHeight = topPadding/3

    var HomeIcon = Icons.Outlined.Home
    var HomeIconColor = Color.Black

    var AccountIcon = Icons.Outlined.Person
    var AccountColor = Color.Black

    var Transicon = Icons.Outlined.ShoppingCart
    var TransColor = Color.Black

    var Ledger = Icons.Outlined.Edit
    var ledgerColor = Color.Black
//
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
    BottomAppBar(modifier = Modifier.height(topPadding.dp), containerColor = Color.White){
        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp), contentAlignment = Alignment.Center){

            Row (modifier = Modifier.wrapContentSize()){

                Column(modifier = Modifier.fillMaxHeight().weight(1f)) {

                    Box(modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)){

                        Column(modifier = Modifier.fillMaxHeight().align(Alignment.Center)) {
                            Icon(imageVector = HomeIcon, tint = HomeIconColor, contentDescription = "home", modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .size(IconHeight.dp)
                                )

                            Text(text = "Home", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(weight = 700), modifier = Modifier.align(Alignment.CenterHorizontally))
                        }
                    }

                }

                Column (modifier = Modifier.fillMaxHeight().weight(1f)){

                    Box(modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)){

                        Column(modifier = Modifier.fillMaxHeight().align(Alignment.Center)) {

                            Icon(imageVector = AccountIcon, tint = AccountColor, contentDescription = "Account", modifier = Modifier
                                .size(IconHeight.dp)
                                .align(Alignment.CenterHorizontally)
                                )
                            Text(text = "Account", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(700), modifier = Modifier.align(Alignment.CenterHorizontally))

                        }
                    }

                }

                Column (modifier = Modifier.fillMaxHeight().weight(1f)){

                    Box(modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)){

                        Column(modifier = Modifier.fillMaxHeight().align(Alignment.Center)) {
                            Icon(imageVector = Ledger, tint = ledgerColor, contentDescription = "ADD Sale", modifier = Modifier
                                .size(IconHeight.dp)
                                .align(Alignment.CenterHorizontally)
                            )

                            Text(text = "Ledger", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(700), modifier = Modifier.align(Alignment.CenterHorizontally))
                        }

                    }

                }

                Column (modifier = Modifier.fillMaxHeight().weight(1f)){


                    Box(modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)){

                        Column(modifier = Modifier.fillMaxHeight().align(Alignment.Center)) {
                            Icon(imageVector = Transicon, tint = TransColor, contentDescription = "ADD Sale", modifier = Modifier
                                .size(IconHeight.dp)
                                .align(Alignment.CenterHorizontally)
                                )

                            Text(text = "Bills", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(700), modifier = Modifier.align(Alignment.CenterHorizontally))


                        }
                    }
                }



            }


        }


    }
}
