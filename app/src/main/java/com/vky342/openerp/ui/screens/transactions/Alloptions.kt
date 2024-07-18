package com.vky342.openerp.ui.screens.transactions

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
import androidx.compose.material.icons.filled.Refresh
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.vky342.openerp.ui.Graphs.AccountScreens
import com.vky342.openerp.ui.Graphs.TransactionScreen

//@Preview
@Composable
fun AllTransactionOptionsScreen(navController: NavHostController){
    val height = LocalConfiguration.current.run { screenHeightDp.dp}
    val topPadding = height.value * 0.1
    val cardheight = topPadding/1.3

    Column(modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize()
        .padding(top = topPadding.dp, bottom = topPadding.dp)
    ) {

        Text(fontSize = 26.sp,text = "TRANSACTIONS", modifier = Modifier.align(Alignment.CenterHorizontally))

        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(vertical = 15.dp, horizontal = 25.dp),
            onClick = {
                navController.navigate(TransactionScreen.AddSale)

            }, colors = CardDefaults.cardColors()) {
            Row(
                modifier = Modifier
                    .height(cardheight.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "ADD",
                    modifier = Modifier.size(cardheight.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(cardheight.dp)
                        .width(20.dp)
                )
                Text(
                    text = "Add Sales",
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(vertical = 15.dp, horizontal = 25.dp),
            onClick = {

                navController.navigate(TransactionScreen.AddPurchase)

            }, colors = CardDefaults.cardColors()) {
            Row(
                modifier = Modifier
                    .height(cardheight.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "ADD",
                    modifier = Modifier.size(cardheight.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(cardheight.dp)
                        .width(20.dp)
                )
                Text(
                    text = "Add Purchase",
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(vertical = 15.dp, horizontal = 25.dp),
            onClick = {

                navController.navigate(TransactionScreen.modifySale)

            }, colors = CardDefaults.cardColors()) {
            Row(
                modifier = Modifier
                    .height(cardheight.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "MOD",
                    modifier = Modifier.size(cardheight.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(cardheight.dp)
                        .width(20.dp)
                )
                Text(
                    text = "Modify Sale",
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(vertical = 15.dp, horizontal = 25.dp),
            onClick = {

                navController.navigate(TransactionScreen.modifyPurchase)

            }, colors = CardDefaults.cardColors()) {
            Row(
                modifier = Modifier
                    .height(cardheight.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "MOD",
                    modifier = Modifier.size(cardheight.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(cardheight.dp)
                        .width(20.dp)
                )
                Text(
                    text = "Modify Purchase",
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(vertical = 15.dp, horizontal = 25.dp),
            onClick = {

                navController.navigate(TransactionScreen.deleteSale)

            }, colors = CardDefaults.cardColors()) {
            Row(
                modifier = Modifier
                    .height(cardheight.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "REM",
                    modifier = Modifier.size(cardheight.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(cardheight.dp)
                        .width(20.dp)
                )
                Text(
                    text = "Delete Sale",
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(vertical = 15.dp, horizontal = 25.dp),
            onClick = {

                navController.navigate(TransactionScreen.deletePurchase)

            }, colors = CardDefaults.cardColors()) {
            Row(
                modifier = Modifier
                    .height(cardheight.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "REM",
                    modifier = Modifier.size(cardheight.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(cardheight.dp)
                        .width(20.dp)
                )
                Text(
                    text = "Delete Purchase",
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}