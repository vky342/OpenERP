package com.vky342.openerp.ui.screens.transactions

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.screens.HOMES.Searchbar
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.account_list_type_selector_container_color
import com.vky342.openerp.ui.theme.account_list_type_selector_selected_txt_color
import com.vky342.openerp.ui.theme.account_list_type_selector_shadow_color
import com.vky342.openerp.ui.theme.account_list_type_selector_unselected_txt_color
import com.vky342.openerp.ui.theme.account_type_selector_selected_button_color
import com.vky342.openerp.ui.theme.account_type_selector_unselected_button_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.sale_button_box_color
import com.vky342.openerp.ui.theme.title_color
import com.vky342.openerp.ui.theme.var_amount_row_colour

@Preview
@Composable
fun modify_sale_screen_prev(){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    val is_bill_selected = remember { mutableStateOf(false) }

    val bill_type = remember { mutableStateOf("Sale") }

    val item_fill_popUp_status = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
            .background(color = background_color)
    ) {

        if (is_bill_selected.value && bill_type.value == "Sale"){
            // Title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(45.dp)
            ) {
                Text(text = "old sale", color = New_account_title_color,fontSize = 24.sp, modifier = Modifier.align(
                    Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }
            // Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 5.dp)
                    .height(70.dp)
            ) {
                form_fields(icon = Icons.Outlined.Person,label = "Party",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }

            // Payment mode selector
            Row (horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(horizontal = sidePadding.dp)
                    .fillMaxWidth()
                    .padding(vertical = 7.dp)
                    .height(50.dp)
            ) {
                payment_mode_type_selector(modifier = Modifier.align(Alignment.CenterVertically).fillMaxHeight())

                Box (modifier = Modifier.fillMaxWidth(1f).align(Alignment.Top).height(43.dp)){
                    DatePickerComposable (label = "Date")
                }
            }
            Column (modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(top = 25.dp)){

                Text("S a l e   S u m m a r y",
                    fontSize = 20.sp,color = title_color,
                    modifier = Modifier
                        .padding(horizontal = sidePadding.dp)
                        .align(Alignment.CenterHorizontally))

                Variable_Amount_Row_2(modifier = Modifier.background(color = var_amount_row_colour))

                checkout_Strip()

                Add_button_Strip(onClick = {item_fill_popUp_status.value = true})

                Box (modifier = Modifier.fillMaxWidth().height(20.dp)){
                    //padding
                }

                item_Card(modifier = Modifier.padding(horizontal = (sidePadding/2).dp, vertical = 10.dp))
                item_Card(modifier = Modifier.padding(horizontal = (sidePadding/2).dp, vertical = 10.dp))
                item_Card(modifier = Modifier.padding(horizontal = (sidePadding/2).dp, vertical = 10.dp))
            }
        }

        if (is_bill_selected.value && bill_type.value == "Purchase"){
            // Title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(45.dp)
            ) {
                Text(text = "old purchase", color = New_account_title_color,fontSize = 24.sp, modifier = Modifier.align(
                    Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }
            // Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 5.dp)
                    .height(70.dp)
            ) {
                form_fields(icon = Icons.Outlined.Person,label = "Party",modifier = Modifier.padding(horizontal = sidePadding.dp).align(Alignment.CenterStart))
            }

            // Payment mode selector
            Row (horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(horizontal = sidePadding.dp)
                    .fillMaxWidth()
                    .padding(vertical = 7.dp)
                    .height(50.dp)
            ) {
                payment_mode_type_selector(modifier = Modifier.align(Alignment.CenterVertically).fillMaxHeight())

                Box (modifier = Modifier.fillMaxWidth(1f).align(Alignment.Top).height(43.dp)){
                    DatePickerComposable (label = "Date")
                }
            }
            Column (modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(top = 25.dp)){

                Text("P u r c h a s e   S u m m a r y",
                    fontSize = 20.sp,color = title_color,
                    modifier = Modifier
                        .padding(horizontal = sidePadding.dp)
                        .align(Alignment.CenterHorizontally))

                Variable_Amount_Row_2(modifier = Modifier.background(color = var_amount_row_colour))

                checkout_Strip()

                Add_button_Strip(onClick = {item_fill_popUp_status.value = true})

                Box (modifier = Modifier.fillMaxWidth().height(20.dp)){
                    //padding
                }

                item_Card(modifier = Modifier.padding(horizontal = (sidePadding/2).dp, vertical = 10.dp))
                item_Card(modifier = Modifier.padding(horizontal = (sidePadding/2).dp, vertical = 10.dp))
                item_Card(modifier = Modifier.padding(horizontal = (sidePadding/2).dp, vertical = 10.dp))
            }
        }

        else{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(50.dp)
            ) {
                Text(text = "Select Bill", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier
                    .align(
                        Alignment.CenterStart
                    )
                    .padding(horizontal = sidePadding.dp))
            }

            invoice_type_selector(onVc = {bill_type.value = it},modifier = Modifier.padding(vertical = 2.dp))

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
                    Searchbar(modifier = Modifier, current_value = "", label = "Invoice ID...",onVC = {
                        // on value change of search Bar
                    })
                }
            }

            Box(
                modifier = Modifier
                    .padding(vertical = 150.dp)
                    .height(60.dp)
                    .fillMaxWidth()
                    .background(color = sale_button_box_color)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {

                Row (modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center){
                    Icon(
                        Icons.Default.Refresh, contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .size(25.dp)
                            .align(Alignment.CenterVertically),
                        tint = Color.White
                    )
                    Text("Load recent bill...",
                        color = Color.White,
                        fontSize = 22.sp,
                        modifier = Modifier.align(
                            Alignment.CenterVertically)
                            .clickable {
                                // load recent Bill
                                is_bill_selected.value = true }
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun invoice_type_selector(modifier: Modifier = Modifier, onVc : (String) -> Unit = {}){
    val selected_type = remember { mutableStateOf(0) }

    var customer_type_txt_color = account_list_type_selector_unselected_txt_color
    var customer_type_button_color = account_type_selector_unselected_button_color
    var customer_type_elevation = 0.dp

    var supplier_type_txt_color = account_list_type_selector_unselected_txt_color
    var supplier_type_button_color = account_type_selector_unselected_button_color
    var supplier_type_elevation = 0.dp

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
                shape = RoundedCornerShape(
                    topStart = 20f,
                    topEnd = 20f,
                    bottomEnd = 20f,
                    bottomStart = 20f
                ),
                ambientColor = account_list_type_selector_shadow_color,
                spotColor = account_list_type_selector_shadow_color
            )
            .background(
                color = account_list_type_selector_container_color,
                shape = RoundedCornerShape(
                    topStart = 20f,
                    topEnd = 20f,
                    bottomEnd = 20f,
                    bottomStart = 20f
                )
            )

        ) {

            // cash
            Box (modifier = Modifier
                .clickable {
                    selected_type.value = 0
                    onVc("Sale")
                }
                .weight(1f)
                .fillMaxHeight()
                .shadow(
                    elevation = customer_type_elevation,
                    shape = RoundedCornerShape(
                        topStart = 20f,
                        topEnd = 20f,
                        bottomEnd = 20f,
                        bottomStart = 20f
                    ),
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                )
                .background(
                    color = customer_type_button_color,
                    shape = RoundedCornerShape(
                        topStart = 20f,
                        topEnd = 20f,
                        bottomEnd = 20f,
                        bottomStart = 20f
                    )
                )
                .align(Alignment.CenterVertically)) {

                Text(text = "Sale",
                    fontSize = 15.sp,
                    color = customer_type_txt_color,
                    modifier = Modifier.align(Alignment.Center)
                )

            }


            // Credit
            Box (modifier = Modifier
                .clickable {
                    selected_type.value = 1
                    onVc("Purchase")
                }
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
                    shape = RoundedCornerShape(
                        topStart = 20f,
                        topEnd = 20f,
                        bottomEnd = 20f,
                        bottomStart = 20f
                    )
                )
                .align(Alignment.CenterVertically)) {

                Text(text = "Purchase",
                    fontSize = 15.sp,
                    color = supplier_type_txt_color,
                    modifier = Modifier.align(Alignment.Center))

            }

        }
    }
}
