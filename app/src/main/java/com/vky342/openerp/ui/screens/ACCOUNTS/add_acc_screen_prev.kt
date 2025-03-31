package com.vky342.openerp.ui.screens.ACCOUNTS

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.account_list_type_selector_container_color
import com.vky342.openerp.ui.theme.account_list_type_selector_selected_txt_color
import com.vky342.openerp.ui.theme.account_list_type_selector_shadow_color
import com.vky342.openerp.ui.theme.account_list_type_selector_unselected_txt_color
import com.vky342.openerp.ui.theme.account_type_selector_selected_button_color
import com.vky342.openerp.ui.theme.account_type_selector_unselected_button_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.form_focused_container_color
import com.vky342.openerp.ui.theme.form_focused_indicator_color
import com.vky342.openerp.ui.theme.form_focused_label_color
import com.vky342.openerp.ui.theme.form_focused_leading_icon_color
import com.vky342.openerp.ui.theme.form_unfocused_container_color
import com.vky342.openerp.ui.theme.form_unfocused_indicator_color
import com.vky342.openerp.ui.theme.form_unfocused_label_color
import com.vky342.openerp.ui.theme.form_unfocused_leading_icon_color
import com.vky342.openerp.ui.theme.save_button_container_color
import kotlinx.coroutines.Dispatchers


@Preview
@Composable
fun add_acc_screen_prev(){

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
            Text(text = "New account", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = sidePadding.dp))
        }

        // Account Type Selector
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)
        ) {
            account_registration_type_selector(selected_type = "customer")
        }

        // Name
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)
        ) {
            form_fields(icon = Icons.Outlined.Person,label = "Name",modifier = Modifier
                .padding(horizontal = sidePadding.dp)
                .align(Alignment.CenterStart))
        }

        // Address
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)

        ) {
            form_fields(icon = Icons.Outlined.LocationOn,label = "Address",modifier = Modifier
                .padding(horizontal = sidePadding.dp)
                .align(Alignment.CenterStart))
        }
        // Contact
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)

        ) {
            form_fields(icon = Icons.Outlined.Call,label = "Contact",modifier = Modifier
                .padding(horizontal = sidePadding.dp)
                .align(Alignment.CenterStart))
        }
        // save button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)

        ) {
            Save_button(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
fun form_fields(modifier: Modifier = Modifier, onVc : (String) -> Unit = {},value : String = "",label:String = "label",icon : ImageVector = Icons.Default.Person){

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        label = { Text(text = label)},
        onValueChange = {onVc(it)},
        colors = TextFieldDefaults.colors().copy(
            unfocusedContainerColor = form_unfocused_container_color,
            focusedContainerColor = form_focused_container_color,
            focusedIndicatorColor = form_focused_indicator_color,
            unfocusedIndicatorColor = form_unfocused_indicator_color,
            focusedLabelColor = form_focused_label_color,
            unfocusedLabelColor = form_unfocused_label_color,
            focusedLeadingIconColor = form_focused_leading_icon_color,
            unfocusedLeadingIconColor = form_unfocused_leading_icon_color,
        ),
        singleLine = true,
        leadingIcon = { Icon(imageVector = icon, contentDescription = "")},

    )
}


@Composable
fun account_registration_type_selector(modifier: Modifier = Modifier, selected_type : String ,customer_click : () -> Unit = {}, supplier_click : () -> Unit = {}, regular_click : () -> Unit = {}){

    val context : Context = LocalContext.current

    var customer_type_txt_color = account_list_type_selector_unselected_txt_color
    var customer_type_button_color = account_type_selector_unselected_button_color
    var customer_type_elevation = 0.dp

    var supplier_type_txt_color = account_list_type_selector_unselected_txt_color
    var supplier_type_button_color = account_type_selector_unselected_button_color
    var supplier_type_elevation = 0.dp

    var regular_type_txt_color = account_list_type_selector_unselected_txt_color
    var regular_type_button_color = account_type_selector_unselected_button_color
    var regular_type_elevation = 0.dp

    if (selected_type == "customer"){
        customer_type_txt_color = account_list_type_selector_selected_txt_color
        customer_type_button_color = account_type_selector_selected_button_color
        customer_type_elevation = 5.dp
    }

    if (selected_type == "supplier"){
        supplier_type_txt_color = account_list_type_selector_selected_txt_color
        supplier_type_button_color = account_type_selector_selected_button_color
        supplier_type_elevation = 5.dp
    }

    if (selected_type == "regular"){
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

            // customer
            Box (modifier = Modifier
                .clickable {
                    customer_click()
                    Toast.makeText(context,"Customer selected", Toast.LENGTH_SHORT).show()
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

                Text(text = "Customer",
                    fontSize = 15.sp,
                    color = customer_type_txt_color,
                    modifier = Modifier.align(Alignment.Center))

            }


            // supplier
            Box (modifier = Modifier
                .clickable {
                    supplier_click()
                    Toast.makeText(context,"Supplier selected", Toast.LENGTH_SHORT).show()
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

                Text(text = "Supplier",
                    fontSize = 15.sp,
                    color = supplier_type_txt_color,
                    modifier = Modifier.align(Alignment.Center)
                )

            }

            // regular
            Box (modifier = Modifier
                .clickable {
                    regular_click()
                    Toast.makeText(context,"Regular selected", Toast.LENGTH_SHORT).show()
                }
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
                    shape = RoundedCornerShape(
                        topStart = 20f,
                        topEnd = 20f,
                        bottomEnd = 20f,
                        bottomStart = 20f
                    )
                )
                .align(Alignment.CenterVertically)) {

                Text(text = "Regular",
                    fontSize = 15.sp,
                    color = regular_type_txt_color,
                    modifier = Modifier.align(Alignment.Center)
                )

            }

        }
    }
}

@Preview
@Composable
fun Save_button(modifier: Modifier = Modifier, onClick : () -> Unit = {},enabled : Boolean = true,label: String = ""){
    val context : Context = LocalContext.current
    Button(colors = ButtonDefaults.buttonColors().copy(containerColor = save_button_container_color),
        modifier = modifier
            .width(100.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 7.dp, pressedElevation = 3.dp),
        enabled = enabled,
        onClick = {
            onClick()
            //Toast.makeText(context,"Account saved", Toast.LENGTH_SHORT).show()
            }
    ) {
        Text(label, color = Color.White)
    }
}