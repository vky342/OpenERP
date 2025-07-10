package com.vky342.openerp.ui.screens.ACCOUNTS

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.ui.screens.HOMES.Calculate_color_of_pieces
import com.vky342.openerp.ui.screens.HOMES.Searchbar
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
import com.vky342.openerp.ui.theme.account_list_title_border_color
import com.vky342.openerp.ui.theme.account_list_title_color
import com.vky342.openerp.ui.theme.account_list_title_container_color
import com.vky342.openerp.ui.theme.account_list_type_selector_container_color
import com.vky342.openerp.ui.theme.account_list_type_selector_selected_txt_color
import com.vky342.openerp.ui.theme.account_list_type_selector_shadow_color
import com.vky342.openerp.ui.theme.account_list_type_selector_unselected_txt_color
import com.vky342.openerp.ui.theme.account_screen_background_color
import com.vky342.openerp.ui.theme.account_type_selector_selected_button_color
import com.vky342.openerp.ui.theme.account_type_selector_unselected_button_color
import com.vky342.openerp.ui.theme.bottom_app_bar
import com.vky342.openerp.ui.theme.bottom_bar_selected_icon_color
import com.vky342.openerp.ui.theme.bottom_bar_selected_indicator_color
import com.vky342.openerp.ui.theme.bottom_bar_selected_txt_color
import com.vky342.openerp.ui.theme.bottom_bar_unselected_icon_color
import com.vky342.openerp.ui.theme.bottom_bar_unselected_txt_color
import com.vky342.openerp.ui.theme.edit_item_border_color
import com.vky342.openerp.ui.theme.edit_item_card_shadow_color
import com.vky342.openerp.ui.theme.edit_item_container_colour
import com.vky342.openerp.ui.theme.edit_item_content_color

data class AccountItem(
    val name : String,
    val balance : Int,
    val balanceType : String
)

@Preview
@Composable
fun account_options_prev(){


    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }

    val sidePadding = width.value * 0.05

    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = account_screen_background_color)
    ) {
        add_account_button()
        edit_account_button()
        account_search_bar(modifier = Modifier.padding(horizontal = sidePadding.dp))
    }
}


@Composable
fun account_search_bar(modifier: Modifier = Modifier,onVC : (String) -> Unit = {},value : String = "", onClear : () -> Unit = {}){
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
            Searchbar(onClear = onClear,modifier = Modifier, current_value = value, label = "Search accounts...",onVC = {
                onVC(it)
            })
        }
    }
}

@Preview
@Composable
fun add_account_button(modifier: Modifier = Modifier, onClick : () -> Unit = {}){

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
                    Icons.Default.AccountBox,
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
                        text = "Add account",
                        fontSize = 20.sp,
                        color = account_add_title_color,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.TopStart)

                    )
                    Text(
                        text = "add a new account to ledger",
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
fun edit_account_button(modifier: Modifier = Modifier, onClick: () -> Unit = {}){

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
                            text = "Edit account",
                            fontSize = 20.sp,
                            color = account_edit_title_color,
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .align(Alignment.TopStart)

                        )
                        Text(
                            text = "change account properties",
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


@Composable
fun account_list(accountList: List<Account>, modifier: Modifier = Modifier, selectedType : String = "customer", customerClick : (String) -> Unit, supplierClick : (String) -> Unit, regularClick : (String) -> Unit){

    Column (modifier = modifier
        .padding(vertical = 10.dp)
        .fillMaxWidth()
        .height(650.dp)) {

        Box (modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(0.9f)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(20f))
            .background(
                color = account_list_title_container_color,
                shape = RoundedCornerShape(20f)
            )
            .border(
                width = 1.dp,
                color = account_list_title_border_color,
                shape = RoundedCornerShape(20f)
            )
            .align(Alignment.CenterHorizontally)
        ) {
            Column (modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Accounts",
                    fontSize = 24.sp,
                    color = account_list_title_color, modifier = Modifier.padding(vertical = 10.dp))

                account_registration_type_selector(enabled = true,
                    selected_type = selectedType,
                    customer_click = {customerClick("customer")},
                    supplier_click = {supplierClick("supplier")},
                    regular_click = {regularClick("regular")})

                Spacer(modifier.fillMaxWidth().height(20.dp))

                account_list_table(accountList = accountList)
            }

        }


    }
}


@Preview
@Composable
fun account_list_table(modifier: Modifier = Modifier, accountList : List<Account> = listOf()){

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
                    Text(text = "Sr", modifier = Modifier.align(Alignment.Center))
                }

                //Name
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.25f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Name", modifier = Modifier.align(Alignment.CenterStart))
                }

                //contact
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .weight(0.25f)
                        .background(color = Color.White)
                ) {
                    Text(text = "Contact", modifier = Modifier.align(Alignment.Center))
                }


            }
        }

        LazyColumn (modifier = Modifier.fillMaxWidth()){ itemsIndexed(accountList){
            index, item ->  account_list_table_single_row(account = item, sr = index)
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
fun account_list_table_single_row(modifier: Modifier = Modifier, account : Account = Account(0,"","","",""), sr : Int = 0){

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color.LightGray)
    )

    Row (modifier = modifier.fillMaxWidth()) { Box(
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
                    text = account.name,
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
                    text = account.contact,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    } }


}

@Preview
@Composable
fun account_list_type_selector(modifier: Modifier = Modifier){

    val selected_type = remember { mutableStateOf(0) }

    var customer_type_txt_color = account_list_type_selector_unselected_txt_color
    var customer_type_button_color = account_type_selector_unselected_button_color
    var customer_type_elevation = 0.dp

    var supplier_type_txt_color = account_list_type_selector_unselected_txt_color
    var supplier_type_button_color = account_type_selector_unselected_button_color
    var supplier_type_elevation = 0.dp

    var regular_type_txt_color = account_list_type_selector_unselected_txt_color
    var regular_type_button_color = account_type_selector_unselected_button_color
    var regular_type_elevation = 0.dp

    if (selected_type.value == 0){
        customer_type_txt_color = account_list_type_selector_selected_txt_color
        customer_type_button_color = account_type_selector_selected_button_color
        customer_type_elevation = 5.dp
    }

    if (selected_type.value == 1){
        supplier_type_txt_color = account_list_type_selector_selected_txt_color
        supplier_type_button_color = account_type_selector_selected_button_color
        supplier_type_elevation = 5.dp
    }

    if (selected_type.value == 2){
        regular_type_txt_color = account_list_type_selector_selected_txt_color
        regular_type_button_color = account_type_selector_selected_button_color
        regular_type_elevation = 5.dp
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row (modifier = modifier
            .fillMaxWidth(0.9f)
            .height(60.dp)
            .align(Alignment.Center)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(topStart = 20f, topEnd = 20f),
                ambientColor = account_list_type_selector_shadow_color,
                spotColor = account_list_type_selector_shadow_color
            )
            .background(color = account_list_type_selector_container_color)

            ) {

            // customer
            Box (modifier = Modifier
                .clickable { selected_type.value = 0 }
                .padding(top = 4.dp, start = 4.dp, end = 4.dp, bottom = 0.dp)
                .weight(1f)
                .fillMaxHeight()
                .shadow(
                    elevation = customer_type_elevation,
                    shape = RoundedCornerShape(topEnd = 20f, topStart = 20f),
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                )
                .background(
                    color = customer_type_button_color,
                    shape = RoundedCornerShape(topEnd = 20f, topStart = 20f)
                )
                .align(Alignment.CenterVertically)) {

                Text(text = "Customer",
                    fontSize = 15.sp,
                    color = customer_type_txt_color,
                    modifier = Modifier.align(Alignment.Center))

            }


            // supplier
            Box (modifier = Modifier
                .clickable { selected_type.value = 1 }
                .padding(top = 4.dp, start = 4.dp, end = 4.dp, bottom = 0.dp)
                .weight(1f)
                .fillMaxHeight()
                .shadow(
                    elevation = supplier_type_elevation,
                    shape = RoundedCornerShape(topEnd = 20f, topStart = 20f),
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                )
                .background(
                    color = supplier_type_button_color,
                    shape = RoundedCornerShape(topEnd = 20f, topStart = 20f)
                )
                .align(Alignment.CenterVertically)) {

                Text(text = "Supplier",
                    fontSize = 15.sp,
                    color = supplier_type_txt_color,
                    modifier = Modifier.align(Alignment.Center))

            }

            // regular
            Box (modifier = Modifier
                .clickable { selected_type.value = 2 }
                .padding(top = 4.dp, start = 4.dp, end = 4.dp, bottom = 0.dp)
                .weight(1f)
                .fillMaxHeight()
                .shadow(
                    elevation = regular_type_elevation,
                    shape = RoundedCornerShape(topEnd = 20f, topStart = 20f),
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                )
                .background(
                    color = regular_type_button_color,
                    shape = RoundedCornerShape(topEnd = 20f, topStart = 20f)
                )
                .align(Alignment.CenterVertically)) {

                Text(text = "Regular",
                    fontSize = 15.sp,
                    color = regular_type_txt_color,
                    modifier = Modifier.align(Alignment.Center))

            }

        }
    }

}
