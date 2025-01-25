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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
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
    Column(modifier = Modifier
        .fillMaxSize()) {

        new_card(navController = navController, to_location = AccountScreens.ADD, main_title = "Add account", sub_title = "customer / seller / personal accounts", side_icon = Icons.Default.Person)

        new_card(navController = navController, to_location = AccountScreens.MODIFY, main_title = "Modify account", sub_title = "Edit profile", side_icon = Icons.Default.Edit)

        new_card(navController = navController, to_location = AccountScreens.LIST, main_title = "Account lists", sub_title = "list of all accounts sorted", side_icon = Icons.Default.Menu)

        new_card(navController = navController, to_location = AccountScreens.DELETE, main_title = "Delete account", sub_title = "remove an account permanently ", side_icon = Icons.Default.Delete)

    }

}



@Preview
@Composable
fun new_Account_options() {

    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1
    val cardheight = topPadding / 1.3
    Column(modifier = Modifier
        .fillMaxSize()) {

        Text(color = Color.LightGray,fontSize = 26.sp,text = "Accounts", modifier = Modifier.align(Alignment.Start).padding(top = 10.dp).padding(horizontal = 25.dp))

        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(horizontal = 25.dp, vertical = 20.dp),
            shape = RectangleShape,
            onClick = {

                //navController.navigate(AccountScreens.ADD)

            }, colors = CardDefaults.cardColors().copy(contentColor = Color.Black, containerColor = Color.White)) {
            Row (modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(color = Color.White)) {

                Box (modifier = Modifier.wrapContentHeight().weight(0.12f)) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "", modifier = Modifier.align(Alignment.Center))
                }


                Box (modifier = Modifier.wrapContentHeight().weight(1f)) {
                    Column (){
                        Text(text = "Add account", fontSize = 20.sp, modifier = Modifier.align(Alignment.Start).padding(vertical = 2.dp))

                        Text(text = "Add account", fontSize = 12.sp, modifier = Modifier.align(Alignment.Start).padding(vertical = 2.5.dp, horizontal = 2.dp))
                    }
                }

            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(25.dp),
            onClick = {

                //navController.navigate(AccountScreens.MODIFY)

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

                //navController.navigate(AccountScreens.LIST)

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

               // navController.navigate(AccountScreens.DELETE)

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


@Composable
fun new_card(navController: NavHostController, to_location : String, main_title : String, sub_title : String , side_icon : ImageVector) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize()
        .padding(horizontal = 25.dp, vertical = 13.dp),
        shape = RectangleShape,
        onClick = {

            navController.navigate(to_location)

        }, colors = CardDefaults.cardColors().copy(contentColor = Color.Black, containerColor = Color.White)) {
        Row (modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(color = Color.White)) {

            Box (modifier = Modifier.wrapContentHeight().weight(0.12f)) {
                Icon(imageVector = side_icon, contentDescription = "", modifier = Modifier.align(Alignment.Center).padding(top = 3.dp))
            }


            Box (modifier = Modifier.wrapContentHeight().weight(1f)) {
                Column (){
                    Text(text = main_title, fontSize = 20.sp, modifier = Modifier.align(Alignment.Start).padding(top = 3.dp))

                    Text(text = sub_title, fontSize = 12.sp, modifier = Modifier.align(Alignment.Start).padding(vertical = 1.dp, horizontal = 2.dp))

                }
            }

        }
        Spacer(modifier = Modifier.fillMaxWidth(0.6f).height(0.2.dp).background(color = Color.Black).align(
            Alignment.CenterHorizontally))
    }
}