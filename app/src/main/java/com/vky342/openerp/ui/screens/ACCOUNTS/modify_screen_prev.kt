package com.vky342.openerp.ui.screens.ACCOUNTS

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vky342.openerp.ui.screens.HOMES.Searchbar
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.search_account_border_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_container_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_content_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_focused_container_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_focused_text_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_icon_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_unfocused_text_color_for_edit_account
import com.vky342.openerp.ui.theme.search_item_border_color
import com.vky342.openerp.ui.theme.search_item_card_shadow_color
import com.vky342.openerp.ui.theme.search_item_container_colour
import com.vky342.openerp.ui.theme.search_item_content_color
import com.vky342.openerp.ui.theme.search_item_focused_container_colour

@Preview
@Composable
fun modify_screen_prev(){
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
            Text(text = "Edit account", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(
                Alignment.CenterStart).padding(horizontal = sidePadding.dp))
        }

        // Account Type Selector
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)
        ) {
            //account_search_bar_for_edit_account()
        }

        // Name
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)
        ) {
            form_fields(icon = Icons.Outlined.Person,label = "Name",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                Alignment.CenterStart))
        }

        // Address
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)

        ) {
            form_fields(icon = Icons.Outlined.LocationOn,label = "Address",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                Alignment.CenterStart))
        }
        // Contact
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)

        ) {
            form_fields(icon = Icons.Outlined.Call,label = "Contact",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                Alignment.CenterStart))
        }
        // save button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(70.dp)

        ) {
            Save_button(modifier = Modifier.align(Alignment.Center), label = "Save")
        }
    }
}


@Composable
fun account_search_bar_for_edit_account(onReset : () -> Unit,enabled : Boolean = true,modifier: Modifier = Modifier,onVc : (String) -> Unit,current_value: String){
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
            TextField(enabled = enabled,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .border(width = 1.dp, color = search_account_border_color_for_edit_account, shape = CircleShape.copy(
                        CornerSize(20f)
                    ))
                    .shadow(elevation = 5.dp, shape = CircleShape.copy(CornerSize(20f)), ambientColor = search_item_card_shadow_color, spotColor = search_item_card_shadow_color),
                value = current_value,
                placeholder = { Text("Select account", color = search_account_content_color_for_edit_account)},
                onValueChange = {
                    onVc(it)
                },
                trailingIcon = {
                    Box {
                        IconButton(
                            onClick = { onReset() },
                            enabled = true // Keep icon active
                        ) {
                            Icon(Icons.Default.Lock, contentDescription = "", tint = search_account_icon_color_for_edit_account)
                        } }
                               },
                colors = TextFieldDefaults.colors().copy(
                    focusedTextColor = search_account_focused_text_color_for_edit_account,
                    unfocusedTextColor = search_account_unfocused_text_color_for_edit_account,
                    focusedContainerColor = search_account_focused_container_color_for_edit_account,
                    unfocusedContainerColor = search_account_container_color_for_edit_account))
        }
    }
}


@Composable
fun Searchbar_for_edit_account(label : String,onVC : (String) -> Unit = {} , current_value: String = "") {

    TextField(
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .border(width = 1.dp, color = search_account_border_color_for_edit_account, shape = CircleShape.copy(
                CornerSize(20f)
            ))
            .shadow(elevation = 5.dp, shape = CircleShape.copy(CornerSize(20f)), ambientColor = search_item_card_shadow_color, spotColor = search_item_card_shadow_color),
        value = current_value,
        placeholder = { Text(label, color = search_account_content_color_for_edit_account)},
        onValueChange = {
            onVC(it)
        },
        trailingIcon = { Icon(Icons.Default.Check, contentDescription = "", tint = search_account_icon_color_for_edit_account) },
        colors = TextFieldDefaults.colors().copy(
            focusedTextColor = search_account_focused_text_color_for_edit_account,
            unfocusedTextColor = search_account_unfocused_text_color_for_edit_account,
            focusedContainerColor = search_account_focused_container_color_for_edit_account,
            unfocusedContainerColor = search_account_container_color_for_edit_account))

}