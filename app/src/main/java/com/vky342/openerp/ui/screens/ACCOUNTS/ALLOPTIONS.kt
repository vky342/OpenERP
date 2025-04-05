package com.vky342.openerp.ui.screens.ACCOUNTS

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.ViewModels.Account.modify_Account_vm
import com.vky342.openerp.data.ViewModels.HomeViewModel
import com.vky342.openerp.ui.Graphs.AccountScreens
import com.vky342.openerp.ui.Graphs.Graph
import com.vky342.openerp.ui.theme.background_color
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun AllOptionsScreen(
    navController: NavHostController,viewModel: modify_Account_vm = hiltViewModel()
) {

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }

    val sidePadding = width.value * 0.05

    var selectedType = remember { mutableStateOf("customer") }

    var searchText = remember { mutableStateOf("") }

    var accountList = remember { mutableStateOf(viewModel.old_Account_list.value) }

    if (searchText.value != "") {
       accountList.value =  accountList.value.filter { it.name.contains(searchText.value, ignoreCase = true) || it.address.contains(searchText.value, ignoreCase = true) || it.contact.contains(searchText.value, ignoreCase = true)}
    }
    if (searchText.value == ""){
        accountList.value = viewModel.old_Account_list.value
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)

    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp)
                .background(color = background_color)
                .verticalScroll(rememberScrollState())
        ) {

            add_account_button(onClick = {navController.navigate(AccountScreens.ADD)})
            edit_account_button(onClick = {navController.navigate(AccountScreens.MODIFY)})

            account_search_bar(onClear = {searchText.value = ""},modifier = Modifier.padding(horizontal = sidePadding.dp), value = searchText.value, onVC = {searchText.value = it})

            account_list(accountList = accountList.value.filter { it.type == selectedType.value },selectedType = selectedType.value, customerClick = {selectedType.value = it}, regularClick = {selectedType.value = it}, supplierClick = {selectedType.value = it})
        }
    }
}

@Composable
fun new_card(
    navController: NavController,
    to_location: String,
    main_title: String,
    sub_title: String,
    side_icon: ImageVector
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(horizontal = 25.dp, vertical = 13.dp),
        shape = RectangleShape,
        onClick = {

            navController.navigate(to_location)

        },
        colors = CardDefaults.cardColors()
            .copy(contentColor = Color.Black, containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = Color.White)
        ) {

            Box(modifier = Modifier
                .wrapContentHeight()
                .weight(0.12f)) {
                Icon(
                    imageVector = side_icon,
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 3.dp)
                )
            }


            Box(modifier = Modifier
                .wrapContentHeight()
                .weight(1f)) {
                Column() {
                    Text(
                        text = main_title,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 3.dp)
                    )

                    Text(
                        text = sub_title,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(vertical = 1.dp, horizontal = 2.dp)
                    )

                }
            }

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(0.2.dp)
                .background(color = Color.Black)
                .align(
                    Alignment.CenterHorizontally
                )
        )
    }
}
