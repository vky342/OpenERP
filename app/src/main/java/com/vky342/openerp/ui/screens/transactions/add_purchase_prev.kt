package com.vky342.openerp.ui.screens.transactions

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.screens.HOMES.AutoResizeText
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.account_list_type_selector_container_color
import com.vky342.openerp.ui.theme.account_list_type_selector_selected_txt_color
import com.vky342.openerp.ui.theme.account_list_type_selector_shadow_color
import com.vky342.openerp.ui.theme.account_list_type_selector_unselected_txt_color
import com.vky342.openerp.ui.theme.account_type_selector_selected_button_color
import com.vky342.openerp.ui.theme.account_type_selector_unselected_button_color
import com.vky342.openerp.ui.theme.add_purchase_screen_item_card_background_color
import com.vky342.openerp.ui.theme.add_purchase_screen_item_card_delete_color
import com.vky342.openerp.ui.theme.amount_text_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.sale_button_background_color
import com.vky342.openerp.ui.theme.sale_icon_color
import com.vky342.openerp.ui.theme.search_account_container_color_for_edit_account
import com.vky342.openerp.ui.theme.shadow_color
import com.vky342.openerp.ui.theme.title_color
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

//@Preview
@Composable
fun add_purchase_screen_prev(){
    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
            .background(color = background_color)
    ) {
        // Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
                .height(50.dp)
        ) {
            Text(text = "New purchase", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(
                Alignment.CenterStart).padding(horizontal = sidePadding.dp))
        }
        // Name
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
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
                DatePickerComposable (label = "Date"){  }
            }
        }
        Column (modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(top = 25.dp)){
            item_Card(modifier = Modifier.padding(horizontal = sidePadding.dp, vertical = 10.dp))
            item_Card(modifier = Modifier.padding(horizontal = sidePadding.dp, vertical = 10.dp))
            item_Card(modifier = Modifier.padding(horizontal = sidePadding.dp, vertical = 10.dp))
        }
    }
}

// payment mode selector

//@Preview
@Composable
fun payment_mode_type_selector(modifier: Modifier = Modifier){

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
            .fillMaxWidth(0.5f)
            .height(60.dp)
    ) {
        Row (modifier = modifier
            .fillMaxWidth(0.9f)
            .height(60.dp)
            .align(Alignment.Center)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(topStart = 20f, topEnd = 20f, bottomEnd = 20f, bottomStart = 20f),
                ambientColor = account_list_type_selector_shadow_color,
                spotColor = account_list_type_selector_shadow_color
            )
            .background(color = account_list_type_selector_container_color, shape = RoundedCornerShape(topStart = 20f, topEnd = 20f, bottomEnd = 20f, bottomStart = 20f))

        ) {

            // cash
            Box (modifier = Modifier
                .clickable { selected_type.value = 0 }
                .weight(1f)
                .fillMaxHeight()
                .shadow(
                    elevation = customer_type_elevation,
                    shape = RoundedCornerShape(topStart = 20f, topEnd = 20f, bottomEnd = 20f, bottomStart = 20f),
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                )
                .background(
                    color = customer_type_button_color,
                    shape = RoundedCornerShape(topStart = 20f, topEnd = 20f, bottomEnd = 20f, bottomStart = 20f)
                )
                .align(Alignment.CenterVertically)) {

                Text(text = "Cash",
                    fontSize = 15.sp,
                    color = customer_type_txt_color,
                    modifier = Modifier.align(Alignment.Center))

            }


            // Credit
            Box (modifier = Modifier
                .clickable { selected_type.value = 1 }
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
                    shape = RoundedCornerShape(topStart = 20f, topEnd = 20f, bottomEnd = 20f, bottomStart = 20f)
                )
                .align(Alignment.CenterVertically)) {

                Text(text = "Credit",
                    fontSize = 15.sp,
                    color = supplier_type_txt_color,
                    modifier = Modifier.align(Alignment.Center))

            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComposable(
    label: String = "Select Date",
    onDateSelected: (String) -> Unit
) {
    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val localDate = Instant.ofEpochMilli(it)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        selectedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        onDateSelected(selectedDate)
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDatePicker = true },
        trailingIcon = {
            IconButton(onClick = { showDatePicker = true }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Select Date")
            }
        }
    )
}

//@Preview
@Composable
fun item_Card(modifier: Modifier = Modifier){


        Box (modifier = modifier.height(250.dp).fillMaxWidth().shadow(elevation = 4.dp, shape = RoundedCornerShape(20f)).background(color = add_purchase_screen_item_card_background_color, shape = RoundedCornerShape(20f)).border(1.dp, color = title_color, shape = RoundedCornerShape(20f))) {
            Column (modifier = Modifier.fillMaxSize()){
                Box (modifier = Modifier.fillMaxWidth().weight(1f)){
                    Row (modifier = Modifier.fillMaxSize()){

                        // Image - Item
                        Box (modifier = Modifier.fillMaxHeight().weight(0.75f).padding(all = 12.dp).shadow(elevation = 4.dp,shape = RoundedCornerShape(20f)).background(color = Color.White, shape = RoundedCornerShape(20f))){
                            Image(Icons.Default.Refresh, contentDescription = "",modifier = Modifier.align(Alignment.Center))
                        }


                        Box (modifier = Modifier.fillMaxHeight().weight(1f)){

                            // item details
                            Column (modifier = Modifier.fillMaxSize().padding(start = 10.dp, bottom = 5.dp).align(Alignment.Center), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

                                AutoResizeText("24x36x45 R/B",
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = shadow_color, // Shadow color
                                            offset = Offset(0f, 4f), // Shadow offset (x, y)
                                            blurRadius = 4f // Shadow blur radius
                                        ),
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = amount_text_color,
                                    textAlign = TextAlign.Center, modifier = Modifier.align(
                                        Alignment.Start).padding(vertical = 2.5.dp)
                                )

                                AutoResizeText("24.80 $",
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = shadow_color, // Shadow color
                                            offset = Offset(0f, 4f), // Shadow offset (x, y)
                                            blurRadius = 4f // Shadow blur radius
                                        ),
                                        fontWeight = FontWeight.Light
                                    ),
                                    color = amount_text_color,
                                    textAlign = TextAlign.Center, modifier = Modifier.align(
                                        Alignment.Start).padding(vertical = 2.5.dp)
                                )

                                AutoResizeText("disc - 20%",
                                    style = TextStyle(
                                        shadow = Shadow(
                                            color = shadow_color, // Shadow color
                                            offset = Offset(0f, 4f), // Shadow offset (x, y)
                                            blurRadius = 4f // Shadow blur radius
                                        ),
                                        fontWeight = FontWeight.Light
                                    ),
                                    color = amount_text_color,
                                    textAlign = TextAlign.Center, modifier = Modifier.align(
                                        Alignment.Start).padding(vertical = 2.5.dp)
                                )




                                AutoResizeText("486.40 $",
                                    style = TextStyle(
                                    shadow = Shadow(
                                        color = shadow_color, // Shadow color
                                        offset = Offset(0f, 4f), // Shadow offset (x, y)
                                        blurRadius = 4f // Shadow blur radius
                                    ),
                                    fontWeight = FontWeight.Bold
                                ),
                                    color = amount_text_color,
                                    textAlign = TextAlign.Center, modifier = Modifier.align(
                                        Alignment.Start)
                                )
                            }

                        }
                    }
                }
                Box (modifier = Modifier.fillMaxWidth().weight(0.4f)){
                    Row (modifier = Modifier.fillMaxSize()){

                        // Delete button
                        Box( modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .shadow(elevation = 4.dp,shape = RoundedCornerShape(20f))
                            .background(color = add_purchase_screen_item_card_delete_color, shape = RoundedCornerShape(
                                CornerSize(20f)
                            ))

                            .align(Alignment.CenterVertically)){

                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(50.dp).align(Alignment.Center)
                            )

                        }

                        // Quantity button
                        Box( modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .shadow(elevation = 4.dp,shape = RoundedCornerShape(20f))
                            .background(color = sale_button_background_color, shape = RoundedCornerShape(
                                CornerSize(20f)
                            ))
                            .align(Alignment.CenterVertically)) {

                            Row (modifier = Modifier.fillMaxSize()){

                                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "", tint = Color.White, modifier = Modifier.weight(1f).align(Alignment.CenterVertically))

                                Box ( modifier = Modifier.weight(2.8f).align(Alignment.CenterVertically)) {

                                    Text("20", modifier = Modifier.align(Alignment.Center), fontSize = 26.sp)

                                }

                                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "", tint = Color.White, modifier = Modifier.weight(1f).align(Alignment.CenterVertically))

                            }
                        }
                    }

                }
            }
        }

}

//@Preview
@Composable
fun floating_add_button(modifier: Modifier = Modifier){
    Box (modifier = modifier.size(60.dp).shadow(elevation = 8.dp, shape = RoundedCornerShape(20f)).background(color = New_account_title_color, shape = RoundedCornerShape(20f))){
        Icon(Icons.Default.Add, contentDescription = "", tint = background_color,modifier = Modifier.size(45.dp).align(Alignment.Center))
    }
}

@Preview
@Composable
fun item_fill_popUp(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(color = background_color,shape = RoundedCornerShape(20f))
    ) {
        //to be continued
    }
}