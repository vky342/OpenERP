package com.vky342.openerp.ui.screens.ACCOUNTS

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vky342.openerp.ui.Graphs.AccountScreens
import com.vky342.openerp.ui.theme.Greye


@Composable
fun AllOptionsScreen(
    navController: NavHostController
){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1
    val cardheight = topPadding / 1.3
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Greye)
        .padding(top = topPadding.dp)) {

        Text(color = Color.LightGray,fontSize = 26.sp,text = "ACCOUNT", modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 10.dp))

        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(25.dp),
            onClick = {

            navController.navigate(AccountScreens.ADD)

            }, colors = CardDefaults.cardColors().copy(contentColor = Greye, containerColor = Color.LightGray)) {
            Row (modifier = Modifier
                .height(cardheight.dp)
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "ADD", modifier = Modifier.size(cardheight.dp))
                Spacer(modifier = Modifier
                    .height(cardheight.dp)
                    .width(20.dp))
                Text(text = "Add Account", fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(25.dp),
            onClick = {

                navController.navigate(AccountScreens.MODIFY)

            }, colors = CardDefaults.cardColors().copy(contentColor = Greye, containerColor = Color.LightGray)) {
            Row (modifier = Modifier
                .height(cardheight.dp)
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "EDIT", modifier = Modifier.size(cardheight.dp))
                Spacer(modifier = Modifier
                    .height(cardheight.dp)
                    .width(20.dp))
                Text(text = "Modify Account", fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(25.dp),
            onClick = {

                navController.navigate(AccountScreens.LIST)

            }, colors = CardDefaults.cardColors().copy(contentColor = Greye, containerColor = Color.LightGray)) {
            Row (modifier = Modifier
                .height(cardheight.dp)
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "LIST", modifier = Modifier.size(cardheight.dp))
                Spacer(modifier = Modifier
                    .height(cardheight.dp)
                    .width(20.dp))
                Text(text = "List Accounts", fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(25.dp),
            onClick = {

                navController.navigate(AccountScreens.DELETE)

            }, colors = CardDefaults.cardColors().copy(contentColor = Greye, containerColor = Color.LightGray)) {
            Row (modifier = Modifier
                .height(cardheight.dp)
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "DELETE", modifier = Modifier.size(cardheight.dp))
                Spacer(modifier = Modifier
                    .height(cardheight.dp)
                    .width(20.dp))
                Text(text = "Delete Account", fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterVertically))
            }
        }


    }

}
