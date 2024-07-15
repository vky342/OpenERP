package com.vky342.openerp.ui.screens.ACCOUNTS

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DeleteScreen(){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val topPadding = height.value * 0.1

    var name by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize()
        .padding(top = topPadding.dp)
    ) {
        Text(fontSize = 26.sp,text = "Delete Account", modifier = Modifier.align(Alignment.CenterHorizontally))
        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),
            label = { Text(text = "Name")},value = name , onValueChange = {name = it})
        Button(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally)
            .wrapContentSize(), onClick = {
            Log.d("DELETE", " ACCOUNT deleted - $name")
        }) {
            Text(text = "Delete", fontSize = 24.sp)
        }
    }
}