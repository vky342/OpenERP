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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.ViewModels.HomeViewModel
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.InventoryIconPin
import com.vky342.openerp.ui.theme.Purple80
import com.vky342.openerp.ui.theme.ReceiptIconPin
import com.vky342.openerp.ui.theme.SaleiconPin
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import kotlin.math.max


@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel(navController.getBackStackEntry("HomeScreen"))){

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
                .weight(0.18f)
                .padding(vertical = 5.dp)){
                Box(modifier = Modifier
                    .padding(horizontal = sidePadding.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .border(shape = CircleShape.copy(topStart = CornerSize(20f),
                        topEnd = CornerSize(size = 20f),
                        bottomStart = CornerSize(size = 20f),
                        bottomEnd = CornerSize(size = 20f)),
                        border = BorderStroke(color = Color.LightGray, width = 1.dp))
                    .shadow(elevation = 5.dp, shape = CircleShape.copy(
                        topStart = CornerSize(20f),
                        topEnd = CornerSize(size = 20f),
                        bottomStart = CornerSize(size = 20f),
                        bottomEnd = CornerSize(size = 20f)))
                    .background(color = Color.White)){

                    VariableAmountRow()

                }
            }

            Box(modifier = Modifier.background(color = Color.Yellow).fillMaxWidth().fillMaxHeight().weight(.8f)){

                var items : Map<String, Pair<Int, Int>> = mapOf(
                    "Apple" to Pair(10, 30),   // 10 Apples, each costing 3
                    "Banana" to Pair(5, 10),   // 5 Bananas, each costing 1
                    "Orange" to Pair(8, 20),   // 8 Oranges, each costing 2
                    "Milk" to Pair(2, 40),     // 2 Milk cartons, each costing 4
                    "Bread" to Pair(1, 20)     // 1 Bread loaf, costing 2
                )

                var items_to_show = remember { mutableListOf("Apple", "banana", "oranges", "pineapple", "Guava") }

                Box (modifier = Modifier.background(color = Color.White).fillMaxWidth().fillMaxHeight(0.25f).align(Alignment.TopCenter)) {
                    Box (modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(horizontal = sidePadding.dp)
                        .align(Alignment.Center)) {
                        Searchbar(onVC = {
                            items_to_show = it
                        })
                    }
                }
                Box (modifier = Modifier.background(color = Color.White).fillMaxWidth().fillMaxHeight(0.75f).align(Alignment.BottomCenter)) {

                    // overall body
                    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f).align(Alignment.Center)) {

                        // title
                        Box (modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(0.1f).padding(horizontal = sidePadding.dp).background(color = Color.White)) {
                            Box (modifier = Modifier.wrapContentSize().align(Alignment.CenterStart)) {
                                Text(text = "Inventory Status", fontSize = 18.sp)
                            }
                        }

                        // subtitle
                        Box (modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(0.17f).shadow(elevation = 5.dp, shape = RoundedCornerShape(20f)).background(color = Color.White).border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(topStart = 20f, topEnd = 20f, ))) {
                            Box (modifier = Modifier.fillMaxHeight().padding(7.dp).width(25.dp).align(Alignment.CenterStart)) {

                                Icon(Icons.Outlined.Info, contentDescription = "", modifier = Modifier.fillMaxSize())
                            }
                            Box (modifier = Modifier.wrapContentHeight().padding(10.dp).wrapContentWidth().align(Alignment.CenterEnd)) {
                                Text(text = "Recent Items", fontSize = 15.sp)
                            }
                        }

                        // table

                        Box (modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(0.9f).background(color = Color.White)) {

                            Column (modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                                Box (modifier = Modifier.fillMaxWidth().fillMaxHeight(0.15f).background(color = Color.White).border(width = Dp.Hairline, color = Color.LightGray).shadow(elevation = 5.dp)) {

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

                                    Box (modifier = Modifier.fillMaxWidth().height((topPadding * 0.4).dp).background(color = Color.White).border(width = Dp.Hairline, color = Color.LightGray).shadow(elevation = 5.dp)) {

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

                                Box (modifier = Modifier.fillMaxSize()){
                                    Text("Load more..", fontSize = 14.sp, modifier = Modifier.padding(5.dp).align(Alignment.CenterEnd), color = Color.Black)
                                }

                            }

                        }
                    }


                }

            }

            Box(modifier = Modifier.background(color = Color.White).fillMaxWidth().fillMaxHeight().weight(0.3f)){

                Box (modifier = Modifier.fillMaxWidth(0.8f).fillMaxHeight(0.3f).shadow(elevation = 5.dp).background(color = Color.White, shape = RoundedCornerShape(20f)).border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20f)).align(Alignment.Center)) {
                    Row (modifier = Modifier.fillMaxSize()) {
                        Box (modifier = Modifier.weight(0.2f).align(Alignment.CenterVertically).padding(5.dp)) {
                            Icon(Icons.Default.Menu, contentDescription = "", modifier = Modifier.size(30.dp))
                        }

                        Box(modifier = Modifier.weight(0.8f).align(Alignment.CenterVertically)){
                            Text(text = "Edit Items", fontSize = 20.sp)
                        }
                    }
                }

            }

        }


}
@Preview
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
            .weight(0.15f)){
            Box(modifier = Modifier
                .padding(horizontal = sidePadding.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .border(shape = CircleShape.copy(topStart = CornerSize(20f),
                    topEnd = CornerSize(size = 20f),
                    bottomStart = CornerSize(size = 20f),
                    bottomEnd = CornerSize(size = 20f)),
                    border = BorderStroke(color = Color.LightGray, width = 1.dp))
                .shadow(elevation = 5.dp, shape = CircleShape.copy(
                    topStart = CornerSize(20f),
                    topEnd = CornerSize(size = 20f),
                    bottomStart = CornerSize(size = 20f),
                    bottomEnd = CornerSize(size = 20f)))
                .background(color = Color.White)){

                VariableAmountRow()

            }
        }

        Box(modifier = Modifier.background(color = Color.Yellow).fillMaxWidth().fillMaxHeight().weight(.8f)){

            var items : Map<String, Pair<Int, Int>> = mapOf(
                "Apple" to Pair(10, 30),   // 10 Apples, each costing 3
                "Banana" to Pair(5, 10),   // 5 Bananas, each costing 1
                "Orange" to Pair(8, 20),   // 8 Oranges, each costing 2
                "Milk" to Pair(2, 40),     // 2 Milk cartons, each costing 4
                "Bread" to Pair(1, 20)     // 1 Bread loaf, costing 2
            )

            var items_to_show = remember { mutableListOf("Apple", "banana", "oranges", "pineapple", "Guava") }

            Box (modifier = Modifier.background(color = Color.White).fillMaxWidth().fillMaxHeight(0.25f).align(Alignment.TopCenter)) {
                Box (modifier = Modifier
                    .align(Alignment.Center)
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(horizontal = sidePadding.dp)
                    .align(Alignment.Center)) {
                        Searchbar(onVC = {
                            items_to_show = it
                        })
                }
            }
            Box (modifier = Modifier.background(color = Color.White).fillMaxWidth().fillMaxHeight(0.75f).align(Alignment.BottomCenter)) {

                // overall body
                Column(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f).align(Alignment.Center)) {

                    // title
                    Box (modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(0.1f).padding(horizontal = sidePadding.dp).background(color = Color.White)) {
                        Box (modifier = Modifier.wrapContentSize().align(Alignment.CenterStart)) {
                            Text(text = "Inventory Status", fontSize = 18.sp)
                        }
                    }

                    // subtitle
                    Box (modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(0.17f).shadow(elevation = 5.dp, shape = RoundedCornerShape(20f)).background(color = Color.White).border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(topStart = 20f, topEnd = 20f, ))) {
                        Box (modifier = Modifier.fillMaxHeight().padding(7.dp).width(25.dp).align(Alignment.CenterStart)) {

                            Icon(Icons.Outlined.Info, contentDescription = "", modifier = Modifier.fillMaxSize())
                        }
                        Box (modifier = Modifier.wrapContentHeight().padding(10.dp).wrapContentWidth().align(Alignment.CenterEnd)) {
                            Text(text = "Recent Items", fontSize = 15.sp)
                        }
                    }

                    // table

                    Box (modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(0.9f).background(color = Color.White)) {
                        
                        Column (modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                            Box (modifier = Modifier.fillMaxWidth().fillMaxHeight(0.15f).background(color = Color.White).border(width = Dp.Hairline, color = Color.LightGray).shadow(elevation = 5.dp)) {

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

                                Box (modifier = Modifier.fillMaxWidth().height((topPadding * 0.4).dp).background(color = Color.White).border(width = Dp.Hairline, color = Color.LightGray).shadow(elevation = 5.dp)) {

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

                            Box (modifier = Modifier.fillMaxSize()){
                                Text("Load more..", fontSize = 14.sp, modifier = Modifier.padding(5.dp).align(Alignment.CenterEnd), color = Color.Black)
                            }

                        }

                    }
                }


            }

        }

        Box(modifier = Modifier.background(color = Color.White).fillMaxWidth().fillMaxHeight().weight(0.3f)){

            Box (modifier = Modifier.fillMaxWidth(0.8f).fillMaxHeight(0.3f).shadow(elevation = 5.dp).background(color = Color.White, shape = RoundedCornerShape(20f)).border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20f)).align(Alignment.Center)) {
                Row (modifier = Modifier.fillMaxSize()) {
                    Box (modifier = Modifier.weight(0.2f).align(Alignment.CenterVertically).padding(5.dp)) {
                        Icon(Icons.Default.Menu, contentDescription = "", modifier = Modifier.size(30.dp))
                    }

                    Box(modifier = Modifier.weight(0.8f).align(Alignment.CenterVertically)){
                        Text(text = "Edit Items", fontSize = 20.sp)
                    }
                }
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
fun VariableAmountRow() {
    // Mutable state for the amounts
    var todaySales by remember { mutableStateOf("10,00,000") }
    var todayReceipts by remember { mutableStateOf("40,00,000") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
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
                .background(Color.LightGray)
                .shadow(elevation = 3.dp)
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
fun Searchbar(onVC : (MutableList<String>)-> Unit ) {
    val current_value = remember{mutableStateOf("")}

    TextField(
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .border(width = 1.dp, color = Color.DarkGray, shape = CircleShape.copy(
                CornerSize(20f)))
            .shadow(elevation = 5.dp, shape = CircleShape.copy(CornerSize(20f))),
        value = current_value.value,
        placeholder = { Text("Search Items...")},
        onValueChange = {
            current_value.value = it

        },
        trailingIcon = { Icon(Icons.Default.Search, contentDescription = "")},
        colors = TextFieldDefaults.colors().copy(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White))

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
            color = Color.Black
        )

        // Amount with dynamic font size
        Text(
            text = "+$amount",
            fontWeight = FontWeight(350),
            style = TextStyle(shadow =
                Shadow(
                color = Color.Black, // Shadow color
                offset = Offset(0f, 5f), // Shadow offset (x, y)
                blurRadius = 4f // Shadow blur radius
            ),
                fontWeight = FontWeight.Bold),
            color = Color.Green,
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
