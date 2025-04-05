package com.vky342.openerp.ui.screens.Inventory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.GeneralDataClasses.InventoryListItem
import com.vky342.openerp.data.ViewModels.InventoryList_VM
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.GreyeHome
import com.vky342.openerp.ui.theme.SaleiconPin


@Composable
fun InventoryList(viewModel : InventoryList_VM = hiltViewModel()){

    val sampleData = listOf(
        InventoryListItem("Apple", 50),
        InventoryListItem("Banana", 30),
        InventoryListItem("Orange", 20),
        InventoryListItem("Milk", 10),
        InventoryListItem("Bread", 15),
        InventoryListItem("Eggs", 12),
        InventoryListItem("Cheese", 25),
        InventoryListItem("Butter", 18),
        InventoryListItem("Tomato", 40),
        InventoryListItem("Potato", 45),
        InventoryListItem("Onion", 35),
        InventoryListItem("Carrot", 20),
        InventoryListItem("Chicken", 10),
        InventoryListItem("Beef", 8),
        InventoryListItem("Pork", 12),
        InventoryListItem("Fish", 14),
        InventoryListItem("Rice", 60),
        InventoryListItem("Pasta", 25),
        InventoryListItem("Flour", 30),
        InventoryListItem("Sugar", 22),
        InventoryListItem("Salt", 40),
        InventoryListItem("Pepper", 35),
        InventoryListItem("Cereal", 18),
        InventoryListItem("Yogurt", 12)
    )

    val height = LocalConfiguration.current.run { screenHeightDp.dp}
    val topPadding = height.value * 0.1

    val list_of_inventory_item = sampleData

    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = GreyeHome)
        .padding(top = topPadding.dp, bottom = topPadding.dp)) {

        field_info()

        LazyColumn (modifier = Modifier.fillMaxSize()){
            itemsIndexed(list_of_inventory_item){index, item ->
                item_card(index = index + 1, inventoryListItem = item)
            }
        }
    }
}


@Composable
fun item_card(index : Int ,inventoryListItem: InventoryListItem) {
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val cardHeight = height.value * 0.08
    val cardWidth = width.value * 1
    val sidePadding = 16.dp

    Card(
        modifier = Modifier
            .height(cardHeight.dp)
            .width(cardWidth.dp)
            .padding(horizontal = sidePadding)
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Greye),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
        ) {
            Text(text = index.toString(), fontSize = 15.sp, color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterStart))

            Text(text = inventoryListItem.itemName, color = SaleiconPin, fontSize = 18.sp,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 4.dp)
                    .align(Alignment.Center))

            Text(text = inventoryListItem.itemQuantity.toString(), color = Color.White, fontSize = 15.sp,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterEnd))
        }
    }
}

@Composable
fun field_info(){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val cardHeight = height.value * 0.08
    val cardWidth = width.value * 1
    val sidePadding = 16.dp

    Card(
        modifier = Modifier
            .height(cardHeight.dp)
            .width(cardWidth.dp)
            .padding(horizontal = sidePadding)
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors().copy(containerColor = Greye),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
        ) {
            Text(text = "Sn.", fontSize = 15.sp, color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterStart))

            Text(text = "Items", color = Color.White, fontSize = 18.sp,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 4.dp)
                    .align(Alignment.Center))

            Text(text = "Quantity", color = Color.White, fontSize = 15.sp,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterEnd))
        }
    }
}
