package com.vky342.openerp.ui.screens.ACCOUNTS

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.ViewModels.Account.delete_Account_vm
import com.vky342.openerp.ui.theme.Greye


@Composable
fun DeleteScreen( viewModel: delete_Account_vm = hiltViewModel()){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1

    var name by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .background(color = Greye)
        .fillMaxSize()
        .padding(top = topPadding.dp)
    ) {
        Text(color = Color.LightGray,fontSize = 26.sp,text = "Delete Account", modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 10.dp))
        TextField(modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally),
            label = { Text(text = "Name")},value = name , onValueChange = {name = it})
        Button(colors = ButtonColors(containerColor = Color.LightGray, disabledContainerColor = Color.LightGray, contentColor = Greye, disabledContentColor = Greye),modifier = Modifier
            .padding(25.dp)
            .align(Alignment.CenterHorizontally)
            .wrapContentSize(), onClick = {
                viewModel.search(name)
                viewModel.deleteAccount()
            Log.d("DELETE", " ACCOUNT deleted - $name")

        }) {
            Text(text = "Delete", fontSize = 24.sp)
        }
    }
}