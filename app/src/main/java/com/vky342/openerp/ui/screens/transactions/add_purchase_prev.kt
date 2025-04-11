package com.vky342.openerp.ui.screens.transactions

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.vky342.openerp.data.Entities.PurchaseEntry
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.screens.HOMES.AmountSection
import com.vky342.openerp.ui.screens.HOMES.AutoResizeText
import com.vky342.openerp.ui.screens.HOMES.VariableAmountRow
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.account_add_title_color
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
import com.vky342.openerp.ui.theme.form_unfocused_container_color
import com.vky342.openerp.ui.theme.middle_spacer_color
import com.vky342.openerp.ui.theme.sale_button_background_color
import com.vky342.openerp.ui.theme.sale_icon_color
import com.vky342.openerp.ui.theme.search_account_container_color_for_edit_account
import com.vky342.openerp.ui.theme.shadow_color
import com.vky342.openerp.ui.theme.title_color
import com.vky342.openerp.ui.theme.var_amount_row_colour
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.sin
import kotlin.text.toDouble

@Preview
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
            Text(text = "New purchase", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier
                .align(
                    Alignment.CenterStart
                )
                .padding(horizontal = sidePadding.dp))
        }
        // Name
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)
        ) {
            form_fields(icon = Icons.Outlined.Person,label = "Party",modifier = Modifier
                .padding(horizontal = sidePadding.dp)
                .align(Alignment.CenterStart))
        }

        // Payment mode selector
        Row (horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = sidePadding.dp)
                .fillMaxWidth()
                .padding(vertical = 7.dp)
                .height(50.dp)
        ) {
            payment_mode_type_selector(modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxHeight())

            Box (modifier = Modifier
                .fillMaxWidth(1f)
                .align(Alignment.Top)
                .height(43.dp)){
                DatePickerComposable (label = "Date")
            }
        }
        Column (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 25.dp)){
            Text("P u r c h a s e   S u m m a r y",
                fontSize = 20.sp,color = title_color,
                modifier = Modifier
                    .padding(horizontal = sidePadding.dp)
                    .align(Alignment.CenterHorizontally))

            Variable_Amount_Row_2(modifier = Modifier.background(color = var_amount_row_colour))

            checkout_Strip()

            Add_button_Strip()

            item_Card(modifier = Modifier.padding(horizontal = (sidePadding/2).dp, vertical = 10.dp))

        }
    }
}



// payment mode selector
@Preview
@Composable
fun payment_mode_type_selector(enabled : Boolean = true,onCash : (String) -> Unit = {}, onCredit : (String) -> Unit = {}, payment_mode : String = "Cash", modifier: Modifier = Modifier){

    val selected_type = remember { mutableStateOf(0) }

    var customer_type_txt_color = account_list_type_selector_unselected_txt_color
    var customer_type_button_color = account_type_selector_unselected_button_color
    var customer_type_elevation = 0.dp

    var supplier_type_txt_color = account_list_type_selector_unselected_txt_color
    var supplier_type_button_color = account_type_selector_unselected_button_color
    var supplier_type_elevation = 0.dp

    if (payment_mode == "Cash"){

        customer_type_txt_color = account_list_type_selector_selected_txt_color
        customer_type_button_color = account_type_selector_selected_button_color
        customer_type_elevation = 5.dp

    }

    if (payment_mode == "Credit"){

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
            .height(58.dp)
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
                .clickable(enabled = enabled) { onCash("Cash") }
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

                Text(text = "Cash",
                    fontSize = 15.sp,
                    color = customer_type_txt_color,
                    modifier = Modifier.align(Alignment.Center)
                )

            }


            // Credit
            Box (modifier = Modifier
                .clickable(enabled = enabled) { onCredit("Credit") }
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
fun DatePickerComposable(enabled: Boolean = true,modifier : Modifier = Modifier,
                         label: String = "Select Date",
                         onDateSelected: (String) -> Unit = {},
                         value : String = ""
) {
    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(enabled = enabled,onClick = {
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

    OutlinedTextField(enabled = enabled,
        value = value,
        onValueChange = {},
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .clickable { showDatePicker = true },
        trailingIcon = {
            IconButton(enabled = enabled,onClick = { showDatePicker = true }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Select Date")
            }
        }
    )
}


//@Preview
@Composable
fun item_Card(quantity : Int = 0,name : String = "", price : Double = 0.00, discount : Double = 0.00,modifier: Modifier = Modifier,onDelete : () -> Unit = {},onQuantityDecrease : () -> Unit = {}, onQuantityIncrease : () -> Unit = {},onAmountChange : (Double) -> Unit = {}) : Pair<Double, PurchaseEntry> {

    val quantity = remember { mutableStateOf(quantity) }

    var item_name = remember { mutableStateOf(name) }

    var totalAmount = remember { derivedStateOf { ( quantity.value.toDouble() *  ( price.toDouble() - ( discount * (price.toDouble() / 100) ) ) ) } }

        Box (modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(20f))
            .background(
                color = add_purchase_screen_item_card_background_color,
                shape = RoundedCornerShape(20f)
            )
            .border(1.dp, color = title_color, shape = RoundedCornerShape(20f))
        ) {
            Column (modifier = Modifier.fillMaxSize()){
                Box (modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)){
                    Row (modifier = Modifier.fillMaxSize()){

                        // Image - Item
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.75f)
                                .padding(all = 12.dp)
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(20f))
                                .background(color = Color.White, shape = RoundedCornerShape(20f))
                        ) {
                            Image(
                                Icons.Default.Refresh,
                                contentDescription = "Item Pic",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }


                        Box (modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)){

                            // item details
                            Column (modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, bottom = 7.dp, top = 14.dp)
                                .align(Alignment.Center), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

                                // Item name
                                Box(modifier = Modifier
                                    .padding(horizontal = 6.dp)
                                    .fillMaxWidth()
                                    .weight(1f)){
                                    Text(item_name.value,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 22.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = amount_text_color,
                                        style = TextStyle(shadow = Shadow(
                                            color = shadow_color, // Shadow color
                                            offset = Offset(0f, 4f), // Shadow offset (x, y)
                                            blurRadius = 4f // Shadow blur radius
                                        )))
                                }


                                // Item Price
                                Box(modifier = Modifier
                                    .padding(horizontal = 6.dp)
                                    .fillMaxWidth()
                                    .weight(1f)){
                                    Text("Price", fontWeight = FontWeight.Light, fontSize = 14.sp, color = Color.White, modifier = Modifier.align(Alignment.CenterEnd))
                                    Text("₹ " + price.toString(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 22.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = amount_text_color,
                                        style = TextStyle(shadow = Shadow(
                                            color = shadow_color, // Shadow color
                                            offset = Offset(0f, 4f), // Shadow offset (x, y)
                                            blurRadius = 4f)
                                        ), modifier = Modifier.align(Alignment.CenterStart)
                                    )
                                }

                                // Item Discount
                                Box(modifier = Modifier
                                    .padding(horizontal = 6.dp)
                                    .fillMaxWidth()
                                    .weight(1f)){
                                    Text("Discount", fontWeight = FontWeight.Light, fontSize = 14.sp, color = Color.White, modifier = Modifier.align(Alignment.CenterEnd))
                                    Text("% " + discount.toString(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 22.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = amount_text_color,
                                        style = TextStyle(shadow = Shadow(
                                            color = shadow_color, // Shadow color
                                            offset = Offset(0f, 4f), // Shadow offset (x, y)
                                            blurRadius = 4f)
                                        ), modifier = Modifier.align(Alignment.CenterStart)
                                    )
                                }

                                // Total amount
                                Box(modifier = Modifier
                                    .padding(horizontal = 6.dp)
                                    .fillMaxWidth()
                                    .weight(1f)){
                                    Text("Total", fontWeight = FontWeight.Light, fontSize = 14.sp, color = Color.White, modifier = Modifier.align(Alignment.CenterEnd))
                                    Text("₹ " + totalAmount.value,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 22.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.White,
                                        style = TextStyle(shadow = Shadow(
                                            color = shadow_color,
                                            offset = Offset(0f, 4f),
                                            blurRadius = 4f)
                                        ),modifier = Modifier.align(Alignment.CenterStart)
                                    )
                                }
                            }

                        }
                    }
                }
                Box (modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)){
                    Row (modifier = Modifier.fillMaxSize()){

                        // Delete button
                        Box( modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(20f))
                            .background(
                                color = add_purchase_screen_item_card_delete_color,
                                shape = RoundedCornerShape(
                                    CornerSize(20f)
                                )
                            )
                            .align(Alignment.CenterVertically)
                            .clickable {
                                onDelete()
                            }
                        ){

                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(50.dp)
                                    .align(Alignment.Center)
                            )

                        }

                        // Quantity button
                        Box( modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                            .weight(1f)
                            .fillMaxHeight(0.9f)
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(20f))
                            .background(
                                color = sale_button_background_color, shape = RoundedCornerShape(
                                    CornerSize(20f)
                                )
                            )
                            .align(Alignment.CenterVertically)) {

                            Row (modifier = Modifier.fillMaxSize()){

                                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "", tint = Color.White,
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(Alignment.CenterVertically)
                                        .clickable {
                                            // Decrease the quantity by 1
                                            if (quantity.value > 1) {
                                                quantity.value -= 1
                                                onQuantityDecrease()
                                                onAmountChange(totalAmount.value)
                                            }

                                        })

                                Box ( modifier = Modifier
                                    .weight(2.8f)
                                    .align(Alignment.CenterVertically)) {

                                    BasicTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),textStyle = TextStyle(fontSize = 25.sp,textAlign = TextAlign.Center),singleLine = true,
                                        value = quantity.value.toString(),
                                        onValueChange = {
                                            try {quantity.value = it.toInt()}
                                            catch ( e : Exception){
                                                quantity.value = 0
                                            }
                                                        },
                                        modifier = Modifier.align(Alignment.Center)
                                    )

                                }

                                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "", tint = Color.White,
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(Alignment.CenterVertically)
                                        .clickable {
                                            // increase the quantity by 1
                                            quantity.value += 1
                                            onQuantityIncrease()
                                            onAmountChange(totalAmount.value)
                                        }
                                )

                            }
                        }
                    }

                }
            }
        }

    return Pair((totalAmount.value), PurchaseEntry(0,quantity.value,price,discount,(totalAmount.value),item_name.value,0))

}

//@Preview
@Composable
fun floating_add_button(modifier: Modifier = Modifier,onClick : () -> Unit = {}){
    Box (modifier = modifier
        .size(60.dp)
        .shadow(elevation = 8.dp, shape = RoundedCornerShape(20f))
        .background(color = New_account_title_color, shape = RoundedCornerShape(20f))
        .clickable { onClick() }
    ){
        Icon(Icons.Default.Add, contentDescription = "", tint = background_color,modifier = Modifier
            .size(45.dp)
            .align(Alignment.Center))
    }
}

@Preview
@Composable
fun item_fill_popUp(name: String = "", price: String = "", discount: String = "", quantity: String = "",
                    onNameChange : (String) -> Unit = {}, onPriceChange : (String) -> Unit = {}, onDiscountChange : (String) -> Unit = {}, onQuantityChange : (String) -> Unit = {},
                    onVC : (String) -> Unit = {}, modifier: Modifier = Modifier, onCancel : () -> Unit = {}, onDone : () -> Unit = {}){

    //var itemPopup = remember { mutableStateOf(item_popup("",0.00,0.00,0)) }

    val context : Context = LocalContext.current


    Box(
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(color = background_color, shape = RoundedCornerShape(20f))
            //.border(width = 1.dp, color = title_color, shape = RoundedCornerShape(20f))
            .shadow(elevation = 4.dp, ambientColor = Color.White, spotColor = Color.White)
    ) {

        Row(modifier = Modifier.fillMaxWidth()){

            // left side
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(5f)){
                Column (modifier = Modifier.fillMaxSize()){

                    //Name
                    Box (modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)){


                        form_fields(value = name,
                            onVc = { onNameChange(it) },
                            icon = Icons.Default.Search,label = "item name",modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth())
                    }

                    // Item price and discount
                    Box (modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)){

                            // Price
                            Box (modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .fillMaxWidth(0.8f)
                                .fillMaxHeight()
                                .align(Alignment.CenterStart)){
                                form_fields(
                                    suffix = "₹",
                                    keyboardOptions = KeyboardOptions(
                                        autoCorrect = false,
                                        keyboardType = KeyboardType.Decimal),
                                    value = price,
                                    onVc = {
                                        onPriceChange(it)
                                           },
                                    icon = Icons.Default.KeyboardArrowUp,label = "price ₹",modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Center))
                            }


                    }

                    // Item price and discount
                    Box (modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)){
                            // Discount
                            Box (modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .fillMaxWidth(0.8f)
                                .fillMaxHeight()
                                .align(Alignment.CenterStart)){
                                form_fields(
                                    suffix = "%",
                                    keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Decimal),
                                    value = discount,
                                    onVc = {
                                        onDiscountChange(it)
                                    },icon = Icons.Default.KeyboardArrowDown,label = "discount %",modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Center))
                            }

                    }

                    // Quantity
                    Box (modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f)){
                        // Discount
                        Box (modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight()
                            .align(Alignment.CenterStart)){
                            form_fields(
                                keyboardOptions = KeyboardOptions(
                                    autoCorrect = false,
                                    keyboardType = KeyboardType.Decimal),
                                value = quantity,
                                onVc = {
                                    onQuantityChange(it)
                                },
                                icon = Icons.Default.Info,label = "quantity",modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center))
                        }

                    }
                }
            }

            //Right side
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)){

                Column (modifier = Modifier.fillMaxSize()){

                    // Cancel button
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 3.dp, start = 5.dp, end = 8.dp, top = 8.dp)
                        .weight(1f)
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(20f))
                        .background(
                            color = add_purchase_screen_item_card_delete_color,
                            shape = RoundedCornerShape(20f)
                        )
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            onCancel()
                        }
                    ){
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "",modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center))
                    }

                    // Save button
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, start = 5.dp, end = 8.dp, top = 3.dp)
                        .weight(1f)
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(20f))
                        .background(
                            color = sale_button_background_color,
                            shape = RoundedCornerShape(20f)
                        )
                        .align(Alignment.CenterHorizontally)
                        .clickable {

                            if (name == "" || price == "" || price == "0.0" || quantity == "" || quantity == "0") {
                                Toast.makeText(context, "field is empty", Toast.LENGTH_SHORT).show()
                            }else{
                                onDone()
                               // Log.d("pop_UP","totalAmount : " + (quantity.value.toDouble() *  ( price.value.toDouble() - ( discount.value.toDouble() * (price.value.toDouble() / 100) ) ))).toString()
                            }

                        }
                    ){
                        Icon(Icons.Default.Check, contentDescription = "",modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                        )
                    }
                }

            }

        }
    }
}


@Preview
@Composable
fun Amount_Section_2(title: String = "Total amount", amount: String = "56,900 $", modifier: Modifier = Modifier) {
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
                text = "$amount",
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


@Preview
@Composable
fun Variable_Amount_Row_2(totalItems : Int = 0, totalAmount : Double = 0.00,modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(), // Fix height issue
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left section: Today's Sale
        Amount_Section_2(
            title = "Total items",
            amount = totalItems.toString(),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
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
            title = "Total amount",
            amount = totalAmount.toString(),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun checkout_Strip(enabled: Boolean = true,onClick: () -> Unit = {}){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color = Color.White)){
        Row (horizontalArrangement = Arrangement.Center,modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp)
            .align(Alignment.Center)){
            Icon(Icons.AutoMirrored.Filled.ExitToApp, tint = title_color,contentDescription = "", modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically)
                .clickable(enabled = enabled) { onClick() })
            Text("Save", color = title_color,fontSize = 30.sp,
                style = TextStyle(
                    shadow = Shadow(
                        color = shadow_color,
                        offset = Offset(0f, 4f),
                        blurRadius = 4f
                    ),
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable(enabled = enabled) { onClick() })
        }
    }
}

@Preview
@Composable
fun Add_button_Strip(enabled: Boolean = true,onClick: () -> Unit = {}){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color = title_color)){
        Row (horizontalArrangement = Arrangement.Center,modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp)
            .align(Alignment.Center)){
            Icon(Icons.Default.Add, tint = background_color,contentDescription = "", modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically)
                .clickable(enabled = enabled) { onClick() })
            Text("Item", color = background_color,fontSize = 30.sp,
                style = TextStyle(
                    shadow = Shadow(
                        color = shadow_color,
                        offset = Offset(0f, 4f),
                        blurRadius = 4f
                    ),
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable(enabled = enabled) { onClick() })
        }
    }
}


