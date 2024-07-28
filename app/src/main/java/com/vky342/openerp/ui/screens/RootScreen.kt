package com.vky342.openerp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.compose.rememberNavController
import com.vky342.openerp.ui.Graphs.Graph
import com.vky342.openerp.ui.Graphs.RootNavigationGraph
import com.vky342.openerp.ui.theme.Greye

@Preview
@Composable
fun RootScreen(navController: NavHostController = rememberNavController()){
    Scaffold (topBar = { topAppBar() }, containerColor = Greye,
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

    var onScreen by remember {
        mutableStateOf(1)
    }

    var HomeIcon = Icons.Outlined.Home
    var HomeIconColor = Color.Black

    var AccountIcon = Icons.Outlined.Person
    var AccountColor = Color.Black

    var Transicon = Icons.Outlined.ShoppingCart
    var TransColor = Color.Black

    var Ledger = Icons.Outlined.Edit
    var ledgerColor = Color.Black

    fun colorFilling(){
        if (onScreen == 1){
            HomeIcon = Icons.Filled.Home
            HomeIconColor = Greye
        }
        else if (onScreen == 2){
            AccountIcon = Icons.Filled.Person
            AccountColor = Greye
        }
        else if (onScreen == 3){
            Transicon = Icons.Filled.ShoppingCart
            TransColor = Greye
        }
        else {
            Ledger = Icons.Filled.Edit
            ledgerColor = Greye
        }
    }

    colorFilling()

    BottomAppBar(modifier = Modifier.height(topPadding.dp), containerColor = Color.LightGray){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

            Row (modifier = Modifier.wrapContentSize()){

                Column(modifier = Modifier.fillMaxHeight()) {

                    Icon(imageVector = HomeIcon, tint = HomeIconColor, contentDescription = "home", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(IconHeight.dp)
                        .clickable(onClick = {
                            onScreen = 1
                            navController.navigate(Graph.HOME) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        })
                    )
                    Text(text = "Home", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(weight = 700), modifier = Modifier.align(Alignment.CenterHorizontally))

                }
                Spacer(modifier = Modifier.width(50.dp))

                Column (modifier = Modifier.fillMaxHeight()){
                    Icon(imageVector = AccountIcon, tint = AccountColor, contentDescription = "Account", modifier = Modifier
                        .size(IconHeight.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable(onClick = {
                            onScreen = 2
                            navController.navigate(Graph.ACCOUNT) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }))
                    Text(text = "Account", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(700), modifier = Modifier.align(Alignment.CenterHorizontally))
                }


                Spacer(modifier = Modifier.width(50.dp))

                Column (modifier = Modifier.fillMaxHeight()){
                    Icon(imageVector = Transicon, tint = TransColor, contentDescription = "ADD Sale", modifier = Modifier
                        .size(IconHeight.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable(onClick = {
                            onScreen = 3
                            navController.navigate(Graph.TRANSACTION) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }))

                    Text(text = "Bills", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(700), modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                Spacer(modifier = Modifier.width(50.dp))

                Column (modifier = Modifier.fillMaxHeight()){
                    Icon(imageVector = Ledger, tint = ledgerColor, contentDescription = "ADD Sale", modifier = Modifier
                        .size(IconHeight.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable(onClick = {
                            onScreen = 4
                            navController.navigate(Graph.LEDGER) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }))

                    Text(text = "Ledger", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight(700), modifier = Modifier.align(Alignment.CenterHorizontally))
                }

            }


        }


    }
}

@Preview
@Composable
fun topAppBar(){
    val height = LocalConfiguration.current.run { screenHeightDp.dp}
    val topPadding = height.value * 0.1
    val statusBar = topPadding * 0.3
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.LightGray)
        .height(topPadding.dp)){
        Text(fontSize = 34.sp, color = Greye, fontWeight = FontWeight(500),text = "OpenERP", modifier = Modifier
            .align(Alignment.Center)
            .padding(top = statusBar.dp))
    }

}
