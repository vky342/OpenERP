package com.vky342.openerp.ui.screens.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vky342.openerp.ui.Graphs.AccountScreens
import com.vky342.openerp.ui.Graphs.TransactionScreen
import com.vky342.openerp.ui.screens.ACCOUNTS.new_card
import com.vky342.openerp.ui.theme.account_add_border_color
import com.vky342.openerp.ui.theme.account_add_content_color
import com.vky342.openerp.ui.theme.account_add_options_container_color
import com.vky342.openerp.ui.theme.account_add_shadow_color
import com.vky342.openerp.ui.theme.account_add_subtitle_color
import com.vky342.openerp.ui.theme.account_add_title_color
import com.vky342.openerp.ui.theme.add_purchase_button_container_color
import com.vky342.openerp.ui.theme.add_sale_button_background_color
import com.vky342.openerp.ui.theme.background_color


@Composable
fun AllTransactionOptionsScreen(
    navController: NavHostController
){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
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


        add_sale_button(onClick = {navController.navigate(TransactionScreen.AddSale)})

        add_purchase_button(onClick = {navController.navigate(TransactionScreen.AddPurchase)})

        modify_bill_button(onClick = {navController.navigate(TransactionScreen.modifyBill)})
        }
    }

}


@Preview
@Composable
fun modify_bill_button(modifier: Modifier = Modifier, onClick : () -> Unit = {}){

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(65.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(20f),
                    ambientColor = account_add_shadow_color,
                    spotColor = account_add_shadow_color
                )
                .background(
                    color = account_add_options_container_color,
                    shape = RoundedCornerShape(20f)
                )
                .border(1.dp, account_add_border_color, RoundedCornerShape(20f))
                .align(Alignment.Center)
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    Icons.Default.Create,
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp)
                        .padding(3.dp),
                    tint = account_add_content_color
                )
                Box (modifier = Modifier
                    .height(40.dp)
                    .align(Alignment.CenterVertically)) {
                    Text(
                        text = "Modify bills",
                        fontSize = 20.sp,
                        color = account_add_title_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.TopStart)

                    )
                    Text(
                        text = "modify previous bills",
                        fontSize = 12.sp,
                        color = account_add_subtitle_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.BottomStart)
                    )
                }

            }
        }
    }
}



@Preview
@Composable
fun add_purchase_button(modifier: Modifier = Modifier, onClick : () -> Unit = {}){

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(65.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(20f),
                    ambientColor = account_add_shadow_color,
                    spotColor = account_add_shadow_color
                )
                .background(
                    color = add_purchase_button_container_color,
                    shape = RoundedCornerShape(20f)
                )
                .border(1.dp, account_add_border_color, RoundedCornerShape(20f))
                .align(Alignment.Center)
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp)
                        .padding(3.dp),
                    tint = account_add_content_color
                )
                Box (modifier = Modifier
                    .height(40.dp)
                    .align(Alignment.CenterVertically)) {
                    Text(
                        text = "Add purchase",
                        fontSize = 20.sp,
                        color = account_add_title_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.TopStart)

                    )
                    Text(
                        text = "add purchase on cash or credit",
                        fontSize = 12.sp,
                        color = account_add_subtitle_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.BottomStart)
                    )
                }

            }
        }
    }
}




@Preview
@Composable
fun add_sale_button(modifier: Modifier = Modifier, onClick : () -> Unit = {}){

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(65.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(20f),
                    ambientColor = account_add_shadow_color,
                    spotColor = account_add_shadow_color
                )
                .background(
                    color = add_sale_button_background_color,
                    shape = RoundedCornerShape(20f)
                )
                .border(1.dp, account_add_border_color, RoundedCornerShape(20f))
                .align(Alignment.Center)
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    Icons.Default.AddCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp)
                        .padding(3.dp),
                    tint = account_add_content_color
                )
                Box (modifier = Modifier
                    .height(40.dp)
                    .align(Alignment.CenterVertically)) {
                    Text(
                        text = "Add sales",
                        fontSize = 20.sp,
                        color = account_add_title_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.TopStart)

                    )
                    Text(
                        text = "add sales on cash or credit",
                        fontSize = 12.sp,
                        color = account_add_subtitle_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.BottomStart)
                    )
                }

            }
        }
    }


}

