package com.vky342.openerp.ui.screens.HOMES

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
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
                    .background(
                        color = edit_item_container_colour,
                        shape = RoundedCornerShape(20f)
                    )
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
                    .background(
                        color = edit_item_container_colour,
                        shape = RoundedCornerShape(20f)
                    )
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

            val recentItems = listOf(
                RecentItem("Laptop", 2, 50000),
                RecentItem("Smartphone", 5, 30000),
                RecentItem("Headphones", 10, 5000),
                RecentItem("Tablet", 3, 20000),
                RecentItem("Smartwatch", 4, 10000),
                RecentItem("Keyboard", 6, 4000),
                RecentItem("Mouse", 8, 2500),
                RecentItem("Monitor", 2, 15000),
                RecentItem("Speaker", 3, 7000),
                RecentItem("Charger", 12, 1200),
                RecentItem("USB Drive", 15, 800),
                RecentItem("Camera", 2, 45000),
                RecentItem("Printer", 1, 25000),
                RecentItem("External HDD", 4, 9000),
                RecentItem("Gaming Console", 3, 40000)
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
                    Searchbar(modifier = Modifier, current_value = "", label = "Search items...",onVC = {
                        // on value change of search Bar
                    })
                }
            }

            // Recent Items Table (75% → Adjusted to 380.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp),
                contentAlignment = Alignment.Center
            ) {
                table_for_recent_items(
                    modifier = Modifier
                        .padding(horizontal = (sidePadding + 7).dp),
                    items = recentItems
                )
            }
        }

    }


}

data class RecentItem(
    val name : String,
    val pcs : Int,
    val price : Int
)

@Preview
@Composable
fun recent_item_table_row(modifier: Modifier = Modifier, item : RecentItem = RecentItem(name = "apple", pcs = 50, price = 80), sr : Int = 0){

    Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(color = Color.LightGray))

    Box (modifier = Modifier.fillMaxWidth().height(50.dp).background(color = Color.White)) {

        Row (modifier = Modifier.fillMaxHeight().fillMaxWidth()){
            //Srn
            Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.1f).background(color = Color.White)) {
                Text(text = (sr + 1).toString(), modifier = Modifier.align(Alignment.Center))
            }

            //Name
            Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.25f).background(color = Color.White)) {
                Text(text = item.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.align(Alignment.Center))
            }

            //Pcs
            Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.15f).background(color = Color.White)) {
                Text(text = item.pcs.toString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.align(Alignment.Center), color = Calculate_color_of_pieces(item.pcs))
            }

            //Price
            Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.15f).background(color = Color.White)) {
                Text(text = item.price.toString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.align(Alignment.Center))
            }
        }
    }


}
