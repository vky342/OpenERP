package com.vky342.openerp.ui.screens.HOMES

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.ViewModels.HomeViewModel
import com.vky342.openerp.ui.Graphs.Graph
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.InventoryIconPin
import com.vky342.openerp.ui.theme.Purple80
import com.vky342.openerp.ui.theme.ReceiptIconPin
import com.vky342.openerp.ui.theme.SaleiconPin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.*
import kotlin.math.max


@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel(navController.getBackStackEntry("HomeScreen"))){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val topPadding = height.value * 0.1

    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(top = topPadding.dp, bottom = topPadding.dp)) {

        Row {
            SaleStatusCard()
            ReceiptStatusCard()
        }

        Box(modifier = Modifier
            .wrapContentSize()
            .align(Alignment.CenterHorizontally)){
            inventoryStatusCard(toInventory = { navController.navigate(Graph.INVENTORY) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
                    }
                }
            )
        }


    }

}

@Composable
fun SaleStatusCard(){

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val cardHeight = height.value * 0.2
    val cardWidth = width.value * 0.5
    val sidePadding = 16.dp
    val amount : Int = 89000

    Card (modifier = Modifier
        .height(cardHeight.dp)
        .width(cardWidth.dp)
        .padding(horizontal = sidePadding)
        .padding(top = 32.dp, bottom = 16.dp)
        .border(width = 1.dp, color = SaleiconPin, shape = CardDefaults.shape),
        colors = CardDefaults.cardColors().copy(containerColor = Greye),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ){

        Box (modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)){
            Icon(imageVector = Icons.Default.Info, contentDescription = "sale", modifier = Modifier.padding(5.dp), tint = SaleiconPin)
            Text(text = "Today's Sales", modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp), color = Color.LightGray, fontSize = 10.sp)

            Box(modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)){
                Text(text = "₹ $amount", color = SaleiconPin, fontSize = 25.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight(400))
            }
        }

    }

}
@Composable
fun ReceiptStatusCard(){

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val cardHeight = height.value * 0.2
    val cardWidth = width.value * 0.5
    val sidePadding = 16.dp
    val amount : Int = 89000

    Card (modifier = Modifier
        .height(cardHeight.dp)
        .width(cardWidth.dp)
        .padding(horizontal = sidePadding)
        .padding(top = 32.dp, bottom = 16.dp)
        .border(width = 1.dp, color = ReceiptIconPin, shape = CardDefaults.shape)
        , colors = CardDefaults.cardColors().copy(containerColor = Greye),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ){

        Box (modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)){
            Icon(imageVector = Icons.Default.Info, contentDescription = "sale", modifier = Modifier.padding(5.dp), tint = ReceiptIconPin)

            Text(text = "Today's Receipts", modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp), color = Color.LightGray, fontSize = 10.sp)

            Box(modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)){
                Text(text = "₹ $amount", color = ReceiptIconPin, fontSize = 25.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight(400))
            }
        }

    }

}


@Composable
fun inventoryStatusCard( toInventory : () -> Unit){

    val sampleItems = listOf(
        Item(
            itemName = "Laptop",
            itemSellingPrice = 1500.00,
            itemPurchasePrice = 1200.00,
            itemQuantity = 10
        ),
        Item(
            itemName = "Smartphone",
            itemSellingPrice = 800.00,
            itemPurchasePrice = 600.00,
            itemQuantity = 25
        ),
        Item(
            itemName = "Headphones",
            itemSellingPrice = 200.00,
            itemPurchasePrice = 150.00,
            itemQuantity = 50
        )
    )

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val cardHeight = height.value * 0.35
    val sidePadding = 16.dp

    val list_of_recent_items : List<Item> = sampleItems

    Card(modifier = Modifier
        .height(cardHeight.dp)
        .fillMaxWidth()
        .padding(horizontal = sidePadding, vertical = 16.dp)
        .border(width = 1.dp, color = InventoryIconPin, shape = CardDefaults.shape),
        colors = CardDefaults.cardColors().copy(containerColor = Greye),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box (modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)){

            Icon(imageVector = Icons.Default.Info, contentDescription = "sale", modifier = Modifier.padding(5.dp), tint = InventoryIconPin)

            Text(text = "Inventory Status", modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp), color = Color.LightGray, fontSize = 10.sp)

            Box(modifier = Modifier
                .height((cardHeight * 0.5).dp)
                .fillMaxWidth()
                .padding(horizontal = sidePadding)
                .align(Alignment.Center)){

                Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Recent Items", fontSize = 10.sp,color = Color.LightGray,modifier = Modifier.align(Alignment.Start))
                    LazyColumn {
                        items(list_of_recent_items){item ->
                            InventoryStatusRow(item = item)
                        }
                    }

                }

            }

            Text(text = "To Inventory", color = Purple80, fontSize = 15.sp, modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(10.dp)
                .clickable { toInventory() }
            )
        }
    }

}

@Composable
fun InventoryStatusRow(item: Item){
    val height = LocalConfiguration.current.run { screenHeightDp.dp }
    val cardHeight = height.value * 0.3
    Box(modifier = Modifier
        .padding(vertical = 5.dp)
        .height((cardHeight * 0.1).dp)
        .fillMaxWidth()){

        Row (modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()){
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
                .align(Alignment.CenterVertically)){
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, tint = Color.Black,contentDescription = "", modifier = Modifier
                    .align(
                        Alignment.CenterStart
                    )
                    .scale(1.3f))
            }

            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .align(Alignment.CenterVertically)){
                Text(text = item.itemName, color = Color.LightGray, fontSize = 16.sp,modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 18.dp))
            }
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .align(Alignment.CenterVertically)){
                Text(text = item.itemQuantity.toString() + " pcs.", fontSize = 18.sp, fontWeight = FontWeight(400), color = InventoryIconPin,modifier = Modifier.align(Alignment.CenterEnd))
            }
        }



    }
}

@Preview
@Composable
fun Updated_home() {
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val topPadding = height.value * 0.1
    val sidePadding = width.value * 0.05

    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(top = topPadding.dp, bottom = topPadding.dp)) {

        Box(modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(0.8f)){
            Box(modifier = Modifier
                .padding(horizontal = sidePadding.dp, vertical = (topPadding * 0.5).dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .border(shape = CircleShape.copy(topStart = CornerSize(20f),
                    topEnd = CornerSize(size = 20f),
                    bottomStart = CornerSize(size = 20f),
                    bottomEnd = CornerSize(size = 20f)),
                    border = BorderStroke(color = Color.DarkGray, width = 1.dp))
                .shadow(elevation = 4.dp, shape = CircleShape.copy(
                    topStart = CornerSize(20f),
                    topEnd = CornerSize(size = 20f),
                    bottomStart = CornerSize(size = 20f),
                    bottomEnd = CornerSize(size = 20f)))
                .background(color = Color.White)){

                VariableAmountRow()

            }
        }

        Box(modifier = Modifier.background(color = Color.Yellow).fillMaxWidth().fillMaxHeight().weight(1f)){

        }

        Box(modifier = Modifier.background(color = Color.Blue).fillMaxWidth().fillMaxHeight().weight(1f)){

        }

    }
}

@Composable
fun VariableAmountRow() {
    // Mutable state for the amounts
    var todaySales by remember { mutableStateOf("2,12,345") }
    var todayReceipts by remember { mutableStateOf("4,67,890") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left section: Today's Sale
        AmountSection(
            title = "Today's Sale",
            amount = todaySales,
            modifier = Modifier.weight(1f)
        )

        // Thin vertical line separator
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(50.dp) // Half the height of the row
                .background(Color.Gray)
        )

        // Right section: Today's Receipts
        AmountSection(
            title = "Today's Receipts",
            amount = todayReceipts,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun AmountSection(title: String, amount: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        // Amount with dynamic font size
        Text(
            text = "+$amount",
            fontWeight = FontWeight(350),
            style = TextStyle(shadow =
                Shadow(
                color = Color.Black, // Shadow color
                offset = Offset(0f, 3f), // Shadow offset (x, y)
                blurRadius = 4f // Shadow blur radius
            ),
                fontWeight = FontWeight.Bold),
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = calculateDynamicFontSize(amount)
        )
    }
}

// Function to calculate font size dynamically
@Composable
fun calculateDynamicFontSize(amount: String): TextUnit {
    val baseSize = 24f // Base font size in sp
    val minSize = 14f // Minimum font size in sp
    val maxLength = 10 // Maximum length before scaling down

    // Calculate scaling factor
    val factor = maxLength.toFloat() / max(amount.length, 1).toFloat()
    val scaledSize = baseSize * factor

    // Ensure font size is within valid range
    return scaledSize.coerceAtLeast(minSize).sp
}