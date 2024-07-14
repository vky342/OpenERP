package com.vky342.openerp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vky342.openerp.ui.Graphs.Graph
import com.vky342.openerp.ui.Graphs.RootNavigationGraph

@Preview
@Composable
fun RootScreen(navController: NavHostController = rememberNavController()){
    Scaffold (topBar = { topAppBar() },
        bottomBar = { BottomBar(navController = navController) }
    ){
        RootNavigationGraph(navController = navController, paddingValues = it)
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    BottomAppBar(){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

            Row (modifier = Modifier.wrapContentSize()){

                Icon(imageVector = Icons.Default.Home, contentDescription = "home", modifier = Modifier
                    .size(60.dp)
                    .clickable(onClick = {
                        navController.navigate(Graph.HOME) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }))
                Spacer(modifier = Modifier.width(50.dp))
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Account", modifier = Modifier
                    .size(60.dp)
                    .clickable(onClick = {
                        navController.navigate(Graph.ACCOUNT) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }))
            }


        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar(){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val topPadding = height.value * 0.1
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(topPadding.dp)){
        Text(fontSize = 32.sp,text = "OpenERP", modifier = Modifier.align(Alignment.Center))
    }

}

//@Preview
//@Composable
//fun prevec(){
//    RootScreen()
//}