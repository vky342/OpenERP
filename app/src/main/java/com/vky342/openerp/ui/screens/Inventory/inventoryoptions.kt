package com.vky342.openerp.ui.screens.Inventory

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.vky342.openerp.ui.theme.ReceiptIconPin
import com.vky342.openerp.ui.theme.SaleiconPin
import com.vky342.openerp.ui.theme.account_add_border_color
import com.vky342.openerp.ui.theme.account_add_content_color
import com.vky342.openerp.ui.theme.account_add_options_container_color
import com.vky342.openerp.ui.theme.account_add_shadow_color
import com.vky342.openerp.ui.theme.account_add_subtitle_color
import com.vky342.openerp.ui.theme.account_add_title_color
import com.vky342.openerp.ui.theme.account_edit_border_color
import com.vky342.openerp.ui.theme.account_edit_content_color
import com.vky342.openerp.ui.theme.account_edit_options_container_color
import com.vky342.openerp.ui.theme.account_edit_shadow_color
import com.vky342.openerp.ui.theme.account_edit_subtitle_color
import com.vky342.openerp.ui.theme.account_edit_title_color
import com.vky342.openerp.ui.theme.background_color


@Composable
fun inventoryOptionsScreen(navController: NavController) {

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }


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
            inventory_list_button(onClick = {navController.navigate(InventoryScreens.InvontoryList)})
            edit_Item_button(onClick = {navController.navigate(InventoryScreens.ItemEdit)})
            add_new_item_button(onClick = {navController.navigate(InventoryScreens.NewItem)})
        }
    }

}


@Preview
@Composable
fun inventory_list_button(modifier: Modifier = Modifier, onClick : () -> Unit = {}){

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
                    Icons.AutoMirrored.Filled.List,
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
                        text = "Inventory Status",
                        fontSize = 20.sp,
                        color = account_add_title_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.TopStart)

                    )
                    Text(
                        text = "status of all the items in inventory",
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
fun edit_Item_button(modifier: Modifier = Modifier, onClick: () -> Unit = {}){

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
                    ambientColor = account_edit_shadow_color,
                    spotColor = account_edit_shadow_color
                )
                .background(
                    color = account_edit_options_container_color,
                    shape = RoundedCornerShape(20f)
                )
                .border(1.dp, account_edit_border_color, RoundedCornerShape(20f))
                .align(Alignment.Center)
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp)
                        .padding(3.dp),
                    tint = account_edit_content_color
                )
                Box (modifier = Modifier
                    .height(40.dp)
                    .align(Alignment.CenterVertically)) {
                    Text(
                        text = "Edit Item",
                        fontSize = 20.sp,
                        color = account_edit_title_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.TopStart)

                    )
                    Text(
                        text = "change Item properties",
                        fontSize = 12.sp,
                        color = account_edit_subtitle_color,
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
fun add_new_item_button(modifier: Modifier = Modifier, onClick : () -> Unit = {}){

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
                        text = "New Item",
                        fontSize = 20.sp,
                        color = account_add_title_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.TopStart)

                    )
                    Text(
                        text = "Add a new Item ",
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
