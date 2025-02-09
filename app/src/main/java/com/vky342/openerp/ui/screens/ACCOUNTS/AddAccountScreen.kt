package com.vky342.openerp.ui.screens.ACCOUNTS


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.ViewModels.Account.Add_Account_VM
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.GreyeHome
import com.vky342.openerp.ui.theme.ReceiptIconPin


@Composable
fun Add_Account_Screen( viewModel: Add_Account_VM = hiltViewModel()){


    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1

    val name = remember{ mutableStateOf("")}

    var address by remember {
        mutableStateOf("")
    }
    var contact by remember {
        mutableStateOf("")
    }


    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {



    }
}

@Preview
@Composable
fun aasc_prototype() {
    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)){



    }
}

@Preview
@Composable
fun account_type_selector(){

}