package com.vky342.openerp.ui.screens.Ledgers

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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vky342.openerp.ui.theme.Greye


@Preview
@Composable
fun Alloptionsforledger(){

    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val topPadding = height.value * 0.1
    val cardheight = topPadding / 1.3
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Greye)
        .padding(top = topPadding.dp)) {

        Text(color = Color.LightGray,fontSize = 26.sp,text = "LEDGER", modifier = Modifier.align(
            Alignment.CenterHorizontally).padding(top = 10.dp))

        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(25.dp),
            onClick = {

                //navController.navigate(AccountScreens.ADD)

            }, colors = CardDefaults.cardColors().copy(contentColor = Greye, containerColor = Color.LightGray)) {
            Row (modifier = Modifier
                .height(cardheight.dp)
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "account ledger", modifier = Modifier.size(cardheight.dp))
                Spacer(modifier = Modifier
                    .height(cardheight.dp)
                    .width(20.dp))
                Text(text = "Account ledger", fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(25.dp),
            onClick = {

               // navController.navigate(AccountScreens.MODIFY)

            }, colors = CardDefaults.cardColors().copy(contentColor = Greye, containerColor = Color.LightGray)) {
            Row (modifier = Modifier
                .height(cardheight.dp)
                .fillMaxWidth()) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "screen", modifier = Modifier.size(cardheight.dp))
                Spacer(modifier = Modifier
                    .height(cardheight.dp)
                    .width(20.dp))
                Text(text = "Day Book", fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterVertically))
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
                Icon(imageVector = Icons.Default.Star, contentDescription = "LIST", modifier = Modifier.size(cardheight.dp))
                Spacer(modifier = Modifier
                    .height(cardheight.dp)
                    .width(20.dp))
                Text(text = "Year Book", fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterVertically))
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
                Icon(imageVector = Icons.Default.Delete, contentDescription = "List", modifier = Modifier.size(cardheight.dp))
                Spacer(modifier = Modifier
                    .height(cardheight.dp)
                    .width(20.dp))
                Text(text = "Debtor's list", fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterVertically))
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
                Icon(imageVector = Icons.Default.Delete, contentDescription = "List", modifier = Modifier.size(cardheight.dp))
                Spacer(modifier = Modifier
                    .height(cardheight.dp)
                    .width(20.dp))
                Text(text = "Creditor's list", fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterVertically))
            }
        }


    }

}