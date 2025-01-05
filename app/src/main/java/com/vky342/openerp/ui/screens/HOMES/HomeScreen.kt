package com.vky342.openerp.ui.screens.HOMES

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import kotlin.math.max


data class list_item(
    val name : String,
    val Pcs : Int,
    val Price : Int
)

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel(navController.getBackStackEntry("HomeScreen"))){

        val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }

        val sidePadding = width.value * 0.05

        var initial_item_list = listOf(list_item("",0,0))

        var Today_Sale = remember{ mutableStateOf(1000000) }
        var Today_Receipt = remember{ mutableStateOf(1000000) }

        var Items_to_show = remember { mutableStateOf(initial_item_list) }

        Column (modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)) {

            Box(modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.2f)){
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


                Box (modifier = Modifier.background(color = Color.White).fillMaxWidth().fillMaxHeight(0.25f).align(Alignment.TopCenter)) {
                    Box (modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(horizontal = sidePadding.dp)
                        .align(Alignment.Center)) {
                        Searchbar(onVC = {
                            // on value change of search Bar
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
                                Box (modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color.White).border(width = Dp.Hairline, color = Color.LightGray).shadow(elevation = 5.dp)) {

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

                                    Box (modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color.White).border(width = Dp.Hairline, color = Color.LightGray).shadow(elevation = 5.dp)) {

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

                                Box (modifier = Modifier.fillMaxWidth().weight(1f)){
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
