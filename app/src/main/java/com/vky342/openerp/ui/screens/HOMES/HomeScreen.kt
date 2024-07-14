package com.vky342.openerp.ui.screens.HOMES

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val topPadding = height.value * 0.1
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(top = topPadding.dp)) {
            Text(text = "HOME")
            Text(text = "HOME")
            Text(text = "HOME")
            Text(text = "HOME")
            Text(text = "HOME")
        }

}

@Preview
@Composable
fun nacknk(){
    HomeScreen()
}

