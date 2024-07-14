package com.vky342.openerp.ui.screens.ACCOUNTS

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vky342.openerp.ui.Graphs.AccountScreens

@Composable
fun AllOptionsScreen(navController: NavHostController){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val topPadding = height.value * 0.1
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = topPadding.dp)) {
            Text(text = "Add")
            Text(text = "MODIFY")
            Text(text = "DLETE")
            Text(text = "LIst")
        Button(onClick = { navController.navigate(route = AccountScreens.ADD) }) {

        }
    }

}