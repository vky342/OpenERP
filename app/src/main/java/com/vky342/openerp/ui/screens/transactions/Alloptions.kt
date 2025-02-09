package com.vky342.openerp.ui.screens.transactions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vky342.openerp.ui.Graphs.TransactionScreen
import com.vky342.openerp.ui.screens.ACCOUNTS.new_card



@Composable
fun AllTransactionOptionsScreen(
    navController: NavHostController
){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    Column(modifier = Modifier
        .fillMaxSize()) {

        new_card(navController = navController, to_location = TransactionScreen.AddSale, main_title = "Add sales", sub_title = "Add cash sales or credit sales", side_icon = Icons.Default.AddCircle)

        new_card(navController = navController, to_location = TransactionScreen.AddPurchase, main_title = "Add purchase", sub_title = "Add purchase of both credit and cash type", side_icon = Icons.Default.ShoppingCart)

        new_card(navController = navController, to_location = TransactionScreen.modifyBill, main_title = "Modify bills", sub_title = "modify previous bills", side_icon = Icons.Default.Create)

    }

}
//
//@Composable
//fun AllTransactionOptionsScreen(navController: NavHostController){
//    val height = LocalConfiguration.current.run { screenHeightDp.dp}
//    val topPadding = height.value * 0.1
//    val cardheight = topPadding/1.3
//
//    Column(modifier = Modifier
//        .background(color = Greye)
//        .fillMaxSize()
//        .padding(top = topPadding.dp, bottom = topPadding.dp)
//    ) {
//
//        Text(color = Color.LightGray,fontSize = 26.sp,text = "TRANSACTIONS", modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 10.dp))
//
//        Card(modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentSize()
//            .padding(vertical = 15.dp, horizontal = 25.dp),
//            onClick = {
//                navController.navigate(TransactionScreen.AddSale)
//
//            }, colors = CardDefaults.cardColors().copy(contentColor = Greye, containerColor = Color.LightGray)) {
//            Row(
//                modifier = Modifier
//                    .height(cardheight.dp)
//                    .fillMaxWidth()
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "ADD",
//                    modifier = Modifier.size(cardheight.dp)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(cardheight.dp)
//                        .width(20.dp)
//                )
//                Text(
//                    text = "Add Sales",
//                    fontSize = 25.sp,
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//            }
//        }
//        Card(modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentSize()
//            .padding(vertical = 15.dp, horizontal = 25.dp),
//            onClick = {
//
//                navController.navigate(TransactionScreen.AddPurchase)
//
//            }, colors = CardDefaults.cardColors().copy(contentColor = Greye, containerColor = Color.LightGray)) {
//            Row(
//                modifier = Modifier
//                    .height(cardheight.dp)
//                    .fillMaxWidth()
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "ADD",
//                    modifier = Modifier.size(cardheight.dp)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(cardheight.dp)
//                        .width(20.dp)
//                )
//                Text(
//                    text = "Add Purchase",
//                    fontSize = 25.sp,
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//            }
//        }
//        Card(modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentSize()
//            .padding(vertical = 15.dp, horizontal = 25.dp),
//            onClick = {
//
//                navController.navigate(TransactionScreen.modifyBill)
//
//            }, colors = CardDefaults.cardColors().copy(contentColor = Greye, containerColor = Color.LightGray)) {
//            Row(
//                modifier = Modifier
//                    .height(cardheight.dp)
//                    .fillMaxWidth()
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Refresh,
//                    contentDescription = "MOD",
//                    modifier = Modifier.size(cardheight.dp)
//                )
//                Spacer(
//                    modifier = Modifier
//                        .height(cardheight.dp)
//                        .width(20.dp)
//                )
//                Text(
//                    text = "Modify Bills",
//                    fontSize = 25.sp,
//                    modifier = Modifier.align(Alignment.CenterVertically)
//                )
//            }
//        }
//
//    }
//}