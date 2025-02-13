package com.vky342.openerp.ui.screens.HOMES

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vky342.openerp.ui.theme.amount_stat_border_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.card_shadow_color
import com.vky342.openerp.ui.theme.edit_item_border_color
import com.vky342.openerp.ui.theme.edit_item_card_shadow_color
import com.vky342.openerp.ui.theme.edit_item_container_colour
import com.vky342.openerp.ui.theme.edit_item_content_color
import com.vky342.openerp.ui.theme.root_container_color
import com.vky342.openerp.ui.theme.sale_button_background_color
import com.vky342.openerp.ui.theme.sale_button_box_color
import com.vky342.openerp.ui.theme.sale_icon_color
import com.vky342.openerp.ui.theme.var_amount_row_colour


@Preview
@Composable
fun homePrev(){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }

    val sidePadding = width.value * 0.05
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)
            .verticalScroll(rememberScrollState()) // Makes column scrollable
            .padding(bottom = 10.dp) // Extra padding to prevent cutoff at the bottom
    ) {
        // Variable Amount Row (20% Height → Adjusted to fixed 120.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(vertical = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = (sidePadding + 7).dp)
                    .shadow(
                        elevation = 4.dp,
                        spotColor = card_shadow_color,
                        ambientColor = card_shadow_color,
                        shape = CircleShape.copy(CornerSize(20f))
                    )
                    .background(
                        color = var_amount_row_colour,
                        shape = CircleShape.copy(
                            topStart = CornerSize(20f),
                            topEnd = CornerSize(20f),
                            bottomStart = CornerSize(20f),
                            bottomEnd = CornerSize(20f)
                        )
                    )
                    .border(
                        shape = CircleShape.copy(
                            topStart = CornerSize(20f),
                            topEnd = CornerSize(20f),
                            bottomStart = CornerSize(20f),
                            bottomEnd = CornerSize(20f)
                        ),
                        border = BorderStroke(1.dp, amount_stat_border_color)
                    )
            ) {
                VariableAmountRow(modifier = Modifier.align(Alignment.Center))
            }
        }

        // Edit Items Button (13% Height → Adjusted to fixed 80.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(56.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(20f),
                        ambientColor = edit_item_card_shadow_color,
                        spotColor = edit_item_card_shadow_color
                    )
                    .background(color = edit_item_container_colour, shape = RoundedCornerShape(20f))
                    .border(1.dp, edit_item_border_color, RoundedCornerShape(20f))
                    .align(Alignment.Center)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(5.dp),
                        tint = edit_item_content_color
                    )
                    Text(
                        text = "Edit Items",
                        fontSize = 20.sp,
                        color = edit_item_content_color,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }

        // Account Ledger Button (13% Height → Adjusted to fixed 80.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(56.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(20f),
                        ambientColor = edit_item_card_shadow_color,
                        spotColor = edit_item_card_shadow_color
                    )
                    .background(color = edit_item_container_colour, shape = RoundedCornerShape(20f))
                    .border(1.dp, edit_item_border_color, RoundedCornerShape(20f))
                    .align(Alignment.Center)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Create,
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(5.dp),
                        tint = edit_item_content_color
                    )
                    Text(
                        text = "Account Ledger",
                        fontSize = 20.sp,
                        color = edit_item_content_color,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }

        // Search Bar and Recent Items Table (80% → Adjusted to 500.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            val items: Map<String, Pair<Int, Int>> = mapOf(
                "Apple" to Pair(10, 30),
                "Banana" to Pair(5, 10),
                "Orange" to Pair(8, 20),
                "Milk" to Pair(2, 40),
                "Bread" to Pair(1, 20)
            )

            // Search Bar (20% → Adjusted to fixed 100.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(horizontal = sidePadding.dp)
                ) {
                    Searchbar(onVC = {
                        // on value change of search Bar
                    })
                }
            }

            // Recent Items Table (75% → Adjusted to 380.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp),
                contentAlignment = Alignment.Center
            ) {
                table_for_recent_items(
                    modifier = Modifier
                        .padding(horizontal = (sidePadding + 7).dp),
                    items = items
                )
            }
        }

        // Sale Button (Fixed Height: 60.dp)
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(color = sale_button_box_color),
            contentAlignment = Alignment.Center
        ) {


            Box( modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.9f)
                .background(color = sale_button_background_color, shape = RoundedCornerShape(
                    CornerSize(20f)))){
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "",
                    tint = sale_icon_color,
                    modifier = Modifier.size(50.dp).align(Alignment.Center)
                )
            }

        }
    }


}