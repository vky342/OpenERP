package com.vky342.openerp.ui.screens.Inventory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.ViewModels.InventoryList_VM
import com.vky342.openerp.ui.screens.transactions.list_of_all_account_name
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.GreyeHome
import com.vky342.openerp.ui.theme.ReceiptIconPin
import com.vky342.openerp.ui.theme.SaleiconPin


@Composable
fun ItemEdit(viewModel : InventoryList_VM = hiltViewModel()){

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val topPadding = height.value * 0.1

    viewModel.Add_Item_to_inventory()

    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = GreyeHome)
        .padding(top = topPadding.dp, bottom = topPadding.dp)) {

        SearchCard()
        Item_properties_Card()

    }
}


@Composable
fun SearchCard(){

    val sampleData = listOf(
        "Notebook",
        "Pen",
        "Laptop",
        "Coffee Mug",
        "Headphones",
        "Backpack",
        "Water Bottle",
        "Smartphone",
        "Keyboard",
        "Mouse"
    )

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val cardHeight = height.value * 0.16
    val cardWidth = width.value * 1
    val sidePadding = 16.dp
    val column_height_for_itemNameExpander = cardHeight * 6
    val row_width_for_itemNameExpander = cardWidth * 0.9

    val itemName = remember {
        mutableStateOf("")
    }

    val itemNameExpander = remember {
        mutableStateOf(false)
    }

    val list_of_all_item : List<String> = sampleData

    Box (modifier = Modifier
        .height(cardHeight.dp)
        .width(cardWidth.dp)
        .padding(horizontal = sidePadding)
        .padding(top = 32.dp, bottom = 16.dp)
        .border(width = 0.5.dp, color = ReceiptIconPin, shape = CardDefaults.shape)){
        TextField(value = itemName.value,
            onValueChange = { itemName.value = it
                itemNameExpander.value = true},
            textStyle = TextStyle(fontSize = 16.sp),
            label = { Text(text = "Item name", fontSize = 14.sp) },
            trailingIcon = { Icon(modifier = Modifier.clickable {

                    // Load Item

            },imageVector = Icons.Outlined.Search, tint = ReceiptIconPin, contentDescription = "search") },
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            colors = TextFieldDefaults.colors().copy(focusedContainerColor = Greye,unfocusedContainerColor = GreyeHome, focusedLabelColor = ReceiptIconPin, unfocusedLabelColor = ReceiptIconPin, focusedTextColor = Color.White, unfocusedTextColor = Color.White)
        )
    }

    if (itemNameExpander.value){
//        Popup(alignment = Alignment.TopCenter,
//            offset = IntOffset(x = 0, y = (cardHeight * 2.3).toInt()),
//            properties = PopupProperties(excludeFromSystemGesture = true),
//            onDismissRequest = {itemNameExpander.value = false}) {
//            val columheight = column_height_for_itemNameExpander
//            val rowWidth = row_width_for_itemNameExpander
//            LazyColumn(
//                modifier = Modifier
//                    .heightIn(max = columheight.dp)
//                    .width(rowWidth.dp)
//                    .background(color = Greye)
//            ) {
//
//                if (itemName.value.isNotEmpty()) {
//                    items(
//                        list_of_all_item.filter {
//                            it.lowercase()
//                                .contains(itemName.value.lowercase()) || it.lowercase()
//                                .contains("others")
//                        }
//                            .sorted()
//                    ) {
//                        PartsItems(name = it) { name ->
//                            itemName.value = name
//                            itemNameExpander.value = false
//                        }
//                    }
//                } else {
//                    items(
//                        list_of_all_item.sorted()
//                    ) {
//                        PartsItems(name = it) { name ->
//                            itemName.value = name
//                            itemNameExpander.value = false
//                        }
//                    }
//                }
//
//            }
//        }
    }

}

@Composable
fun Item_properties_Card(){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val cardHeight = height.value * 0.2
    val cardWidth = width.value * 1
    val sidePadding = 16.dp

    val itemName = remember {
        mutableStateOf("Laptop")
    }

    val itemQuantity = remember {
        mutableStateOf(450)
    }

    val sellingPrice = remember {
        mutableStateOf(50000)
    }

    val purchasePrice = remember {
        mutableStateOf(46000)
    }

    Card (modifier = Modifier
        .wrapContentHeight()
        .width(cardWidth.dp)
        .padding(horizontal = sidePadding)
        .padding(top = 32.dp, bottom = 16.dp)
        .border(width = 1.dp, color = SaleiconPin, shape = CardDefaults.shape),
        colors = CardDefaults.cardColors().copy(containerColor = Greye),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ){

        Box (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(2.dp)){

            Column (modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {

                TextField(value = itemName.value,
                    onValueChange = { itemName.value = it },
                    textStyle = TextStyle(fontSize = 16.sp),
                    label = { Text(text = "Item name", fontSize = 14.sp) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    colors = TextFieldDefaults.colors().copy(focusedContainerColor = Greye,unfocusedContainerColor = GreyeHome, focusedLabelColor = SaleiconPin, unfocusedLabelColor = SaleiconPin, focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                )

                TextField(value = itemQuantity.value.toString(),
                    onValueChange = { itemQuantity.value = it.toInt() },
                    textStyle = TextStyle(fontSize = 16.sp),
                    label = { Text(text = "Stock", fontSize = 14.sp) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    colors = TextFieldDefaults.colors().copy(focusedContainerColor = Greye,unfocusedContainerColor = GreyeHome, focusedLabelColor = SaleiconPin, unfocusedLabelColor = SaleiconPin, focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                )

                TextField(value = purchasePrice.value.toString(),
                    onValueChange = { purchasePrice.value = it.toInt() },
                    textStyle = TextStyle(fontSize = 16.sp),
                    label = { Text(text = "Purchase price", fontSize = 14.sp) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    colors = TextFieldDefaults.colors().copy(focusedContainerColor = Greye,unfocusedContainerColor = GreyeHome, focusedLabelColor = SaleiconPin, unfocusedLabelColor = SaleiconPin, focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                )

                TextField(value = sellingPrice.value.toString(),
                    onValueChange = { sellingPrice.value = it.toInt() },
                    textStyle = TextStyle(fontSize = 16.sp),
                    label = { Text(text = "Selling price", fontSize = 14.sp) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    colors = TextFieldDefaults.colors().copy(focusedContainerColor = Greye,unfocusedContainerColor = GreyeHome, focusedLabelColor = SaleiconPin, unfocusedLabelColor = SaleiconPin, focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                )
            }

        }
    }
}