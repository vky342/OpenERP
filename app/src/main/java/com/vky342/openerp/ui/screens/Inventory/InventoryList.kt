package com.vky342.openerp.ui.screens.Inventory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.ViewModels.InventoryList_VM
import com.vky342.openerp.ui.screens.ACCOUNTS.account_list_table_single_row
import com.vky342.openerp.ui.screens.HOMES.Searchbar
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color


@Composable
fun InventoryList(viewModel : InventoryList_VM = hiltViewModel()){

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    var searchText = remember { mutableStateOf("") }

    var itemList = remember { mutableStateOf(viewModel.item_list.value) }

    if (searchText.value != "") {
       itemList.value =  itemList.value.filter { it.itemName.contains(searchText.value, ignoreCase = true) }
    }
    if (searchText.value == ""){
        itemList.value = viewModel.item_list.value
    }

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
            // Title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(50.dp)
            ) {
                Text(text = "Inventory Status", fontWeight = FontWeight.Bold,color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            //searchBar
            inventory_search_bar(onClear = {searchText.value = ""},modifier = Modifier.padding(horizontal = sidePadding.dp), value = searchText.value, onVC = {searchText.value = it})

            // List
            Box(modifier = Modifier.fillMaxWidth().heightIn(max = 1200.dp)) {
                item_list_table(modifier = Modifier.align(Alignment.Center), itemList = itemList.value)
            }

        }
    }
}


@Composable
fun inventory_search_bar(modifier: Modifier = Modifier,onVC : (String) -> Unit = {},value : String = "", onClear : () -> Unit = {}){
    // Search Bar (20% → Adjusted to fixed 100.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {
            Searchbar(onClear = onClear,modifier = Modifier, current_value = value, label = "Search Inventory",onVC = {
                onVC(it)
            })
        }
    }
}


@Preview
@Composable
fun item_list_table(modifier: Modifier = Modifier, itemList : List<Item> = listOf()){

    Column(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(bottomStart = 20f, bottomEnd = 20f)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                //Srn
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.1f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Sr", fontWeight = FontWeight.Bold,modifier = Modifier.align(Alignment.Center))
                }

                //Name
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.25f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Item", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterStart))
                }

                // Quantity
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.25f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Quantity",fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Center))
                }


            }
        }

        LazyColumn (modifier = Modifier.fillMaxWidth()){ itemsIndexed(itemList){
                index, item ->  item_list_table_single_row(item = item, sr = index)
        }

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.LightGray)
        )
    }

}


@Preview
@Composable
fun item_list_table_single_row(modifier: Modifier = Modifier, item : Item = Item("",0.0,0.0,0), sr : Int = 0){

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color.LightGray)
    )

    Row (modifier = modifier.fillMaxWidth().height(45.dp)) {

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            //Srn
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(0.1f)
                    .background(color = Color.White)
            ) {
                Text(
                    text = (sr + 1).toString(),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Name
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(0.25f)
                    .background(color = Color.White)
            ) {
                Text(
                    text = item.itemName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }

            // contact
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(0.25f)
                    .background(color = Color.White)
            ) {
                Text(
                    text = item.itemQuantity.toString(),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
