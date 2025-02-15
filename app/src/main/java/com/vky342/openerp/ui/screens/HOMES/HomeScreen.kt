package com.vky342.openerp.ui.screens.HOMES

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vky342.openerp.data.ViewModels.HomeViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import com.vky342.openerp.ui.theme.amount_stat_border_color
import com.vky342.openerp.ui.theme.amount_text_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.card_shadow_color
import com.vky342.openerp.ui.theme.edit_item_border_color
import com.vky342.openerp.ui.theme.edit_item_card_shadow_color
import com.vky342.openerp.ui.theme.edit_item_container_colour
import com.vky342.openerp.ui.theme.edit_item_content_color
import com.vky342.openerp.ui.theme.item_table_container_colour
import com.vky342.openerp.ui.theme.item_table_content_color
import com.vky342.openerp.ui.theme.middle_spacer_color
import com.vky342.openerp.ui.theme.sale_button_background_color
import com.vky342.openerp.ui.theme.sale_button_box_color
import com.vky342.openerp.ui.theme.sale_icon_color
import com.vky342.openerp.ui.theme.search_item_border_color
import com.vky342.openerp.ui.theme.search_item_card_shadow_color
import com.vky342.openerp.ui.theme.search_item_container_colour
import com.vky342.openerp.ui.theme.search_item_content_color
import com.vky342.openerp.ui.theme.search_item_focused_container_colour
import com.vky342.openerp.ui.theme.shadow_color
import com.vky342.openerp.ui.theme.var_amount_row_colour


data class list_item(
    val name : String,
    val Pcs : Int,
    val Price : Int
)

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel(navController.getBackStackEntry("HomeScreen"))) {

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }

    val sidePadding = width.value * 0.05

    var initial_item_list = listOf(list_item("", 0, 0))

    var Today_Sale = remember { mutableStateOf(1000000) }
    var Today_Receipt = remember { mutableStateOf(1000000) }

    var Items_to_show = remember { mutableStateOf(initial_item_list) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)

    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = background_color)
                .verticalScroll(rememberScrollState())
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
                        Searchbar("Search items...",onVC = {
                            // on value change of search Bar
                        })
                    }
                }

                // Recent Items Table (75% → Adjusted to 380.dp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    table_for_recent_items(
                        modifier = Modifier
                            .padding(horizontal = (sidePadding + 7).dp),
                        items = items
                    )
                }
            }

        }
        // Sale Button (Fixed Height: 60.dp)
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(color = sale_button_box_color)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {


            Box( modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.75f)
                .shadow(elevation = 4.dp, shape = CircleShape.copy(CornerSize(20f)))
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




fun Calculate_color_of_pieces(int: Int) : Color{
    if (int < 6){
        return Color.Red
    }
    return Color.Green
}


@Composable
fun Searchbar(label : String,onVC : (MutableList<String>)-> Unit ) {
    val current_value = remember{mutableStateOf("")}

    TextField(
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .border(width = 1.dp, color = search_item_border_color, shape = CircleShape.copy(
                CornerSize(20f)))
            .shadow(elevation = 5.dp, shape = CircleShape.copy(CornerSize(20f)), ambientColor = search_item_card_shadow_color, spotColor = search_item_card_shadow_color),
        value = current_value.value,
        placeholder = { Text(label, color = search_item_content_color)},
        onValueChange = {
            current_value.value = it

        },
        trailingIcon = { Icon(Icons.Default.Search, contentDescription = "", tint = search_item_content_color)},
        colors = TextFieldDefaults.colors().copy(focusedContainerColor = search_item_focused_container_colour, unfocusedContainerColor = search_item_container_colour))

}

@Composable
fun AmountSection(title: String, amount: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            maxLines = 1 // Prevents multi-line wrapping
        )

        // Auto-adjusting font size for amount
        Box(
            modifier = Modifier
                .widthIn(min = 50.dp, max = 150.dp) // Ensures text has a fixed width range
        ) {
            AutoResizeText(
                text = "₹$amount",
                style = TextStyle(
                    shadow = Shadow(
                        color = shadow_color, // Shadow color
                        offset = Offset(0f, 4f), // Shadow offset (x, y)
                        blurRadius = 4f // Shadow blur radius
                    ),
                    fontWeight = FontWeight.Bold
                ),
                color = amount_text_color,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AutoResizeText(
    text: String,
    style: TextStyle,
    color: Color,
    textAlign: TextAlign,
    modifier: Modifier = Modifier
) {
    var textSize by remember { mutableStateOf(24.sp) } // Default font size

    BoxWithConstraints(modifier = modifier) {
        val maxWidth = constraints.maxWidth.toFloat()

        Text(
            text = text,
            style = style.copy(fontSize = textSize),
            color = color,
            textAlign = textAlign,
            modifier = Modifier.layout { measurable, constraints ->
                var newFontSize = textSize.value // Convert to Float

                // Measure text width
                val placeable = measurable.measure(constraints)
                if (placeable.width > maxWidth) {
                    newFontSize *= maxWidth / placeable.width // Reduce font size dynamically
                }

                textSize = newFontSize.coerceAtLeast(12f).sp // Fix: Convert Float to TextUnit
                layout(placeable.width, placeable.height) { placeable.placeRelative(0, 0) }
            }
        )
    }
}

@Composable
fun VariableAmountRow(modifier: Modifier) {
    // Mutable state for the amounts
    var todaySales by remember { mutableStateOf("10,00,000") }
    var todayReceipts by remember { mutableStateOf("40,00,000") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(), // Fix height issue
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left section: Today's Sale
        AmountSection(
            title = "Today's Sale",
            amount = todaySales,
            modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
        )

        // Thin vertical line separator
        Box(
            modifier = modifier
                .width(3.dp)
                .height(40.dp) // Adjust height for better UI
                .background(middle_spacer_color)
                .shadow(elevation = 2.dp)
        )

        // Right section: Today's Receipts
        AmountSection(
            title = "Today's Receipts",
            amount = todayReceipts,
            modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
        )
    }
}


@Composable
fun table_for_recent_items(modifier: Modifier = Modifier, items : Map<String, Pair<Int, Int>>){

    // overall body
    Column(modifier = modifier.fillMaxHeight(0.95f).fillMaxWidth(1f)) {

        // subtitle
        Box (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(0.2f)
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(topEnd = 20f, topStart = 20f))
            .background(color = item_table_container_colour, shape = RoundedCornerShape(topEnd = 20f, topStart = 20f))) {

            Box (modifier = Modifier.wrapContentHeight().padding(10.dp).wrapContentWidth().align(Alignment.Center)) {
                Text(text = "Inventory", fontSize = 18.sp, color = item_table_content_color)
            }

        }

        // table

        Box (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(0.9f)) {

            Column (modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .border(BorderStroke(width = 1.dp, color = Color.White),shape = RoundedCornerShape(bottomStart = 20f, bottomEnd = 20f))
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(bottomStart = 20f, bottomEnd = 20f))
                .background(color = Color.White, shape = RoundedCornerShape(bottomStart = 20f, bottomEnd = 20f))) {
                Box (modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color.White)) {

                    Row (modifier = Modifier.fillMaxHeight().fillMaxWidth()){
                        //Srn
                        Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.1f).background(color = Color.White)) {
                            Text(text = "Sr.no", modifier = Modifier.align(Alignment.Center))
                        }

                        //Name
                        Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.25f).background(color = Color.White)) {
                            Text(text = "Name", modifier = Modifier.align(Alignment.Center))
                        }

                        //Pcs
                        Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.15f).background(color = Color.White)) {
                            Text(text = "Pcs", modifier = Modifier.align(Alignment.Center))
                        }

                        //Price
                        Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.15f).background(color = Color.White)) {
                            Text(text = "Price", modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }

                repeat(5){

                    Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(color = Color.LightGray))

                    Box (modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color.White)) {

                        Row (modifier = Modifier.fillMaxHeight().fillMaxWidth()){
                            //Srn
                            Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.1f).background(color = Color.White)) {
                                Text(text = (it + 1).toString(), modifier = Modifier.align(Alignment.Center))
                            }

                            //Name
                            Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.25f).background(color = Color.White)) {
                                Text(text = items.keys.toList()[it], modifier = Modifier.align(Alignment.Center))
                            }

                            //Pcs
                            Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.15f).background(color = Color.White)) {
                                Text(text = items.values.toList()[it].first.toString(), modifier = Modifier.align(Alignment.Center), color = Calculate_color_of_pieces(items.values.toList()[it].first))
                            }

                            //Price
                            Box (modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(0.15f).background(color = Color.White)) {
                                Text(text = items.values.toList()[it].second.toString(), modifier = Modifier.align(Alignment.Center))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(color = Color.LightGray))
            }
        }
    }

}