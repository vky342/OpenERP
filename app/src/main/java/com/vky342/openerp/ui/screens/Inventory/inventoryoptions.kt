package com.vky342.openerp.ui.screens.Inventory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vky342.openerp.ui.Graphs.InventoryScreens
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.GreyeHome
import com.vky342.openerp.ui.theme.ReceiptIconPin
import com.vky342.openerp.ui.theme.SaleiconPin



@Composable
fun inventoryOptionsScreen(navController: NavController) {

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val topPadding = height.value * 0.1
    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = GreyeHome)
        .padding(top = topPadding.dp, bottom = topPadding.dp)) {

        OptionsCard_Inventory_list({ navController.navigate(InventoryScreens.InvontoryList) })
        OptionsCard_Edit_Item( { navController.navigate(InventoryScreens.ItemEdit) } )

    }
}


@Composable
fun OptionsCard_Inventory_list( onClick : () -> Unit ){

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val cardHeight = height.value * 0.2
    val cardWidth = width.value * 1
    val sidePadding = 16.dp

    Card (modifier = Modifier
        .height(cardHeight.dp)
        .width(cardWidth.dp)
        .padding(horizontal = sidePadding)
        .padding(top = 32.dp, bottom = 16.dp)
        .border(width = 1.dp, color = SaleiconPin, shape = CardDefaults.shape),
        colors = CardDefaults.cardColors().copy(containerColor = Greye),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        onClick = onClick
    ){

        Box (modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)){
            Icon(imageVector = Icons.TwoTone.Menu, contentDescription = "sale", modifier = Modifier.padding(5.dp), tint = SaleiconPin)
            Text(text = "Quantity of all items", modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp), color = Color.LightGray, fontSize = 10.sp)

            Box(modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)){
                Text(text = " Inventory ", color = SaleiconPin, fontSize = 25.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight(400))
            }
        }

    }
}

@Composable
fun OptionsCard_Edit_Item( onClick: () -> Unit){

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val cardHeight = height.value * 0.2
    val cardWidth = width.value * 1
    val sidePadding = 16.dp

    Card (modifier = Modifier
        .height(cardHeight.dp)
        .width(cardWidth.dp)
        .padding(horizontal = sidePadding)
        .padding(top = 32.dp, bottom = 16.dp)
        .border(width = 1.dp, color = ReceiptIconPin, shape = CardDefaults.shape),
        colors = CardDefaults.cardColors().copy(containerColor = Greye),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        onClick = onClick
    ){

        Box (modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)){
            Icon(imageVector = Icons.TwoTone.Menu, contentDescription = "sale", modifier = Modifier.padding(5.dp), tint = ReceiptIconPin)
            Text(text = "Modify / Delete Item", modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp), color = Color.LightGray, fontSize = 10.sp)

            Box(modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)){
                Text(text = " Edit Item ", color = ReceiptIconPin, fontSize = 25.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight(400))
            }
        }

    }
}

