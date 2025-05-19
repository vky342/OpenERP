package com.vky342.openerp.ui.screens.Inventory

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.ViewModels.InventoryList_VM
import com.vky342.openerp.ui.screens.ACCOUNTS.Save_button
import com.vky342.openerp.ui.screens.ACCOUNTS.account_search_bar_for_edit_account
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.theme.Greye
import com.vky342.openerp.ui.theme.GreyeHome
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.ReceiptIconPin
import com.vky342.openerp.ui.theme.SaleiconPin
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.search_account_border_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_container_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_content_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_focused_container_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_focused_text_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_icon_color_for_edit_account
import com.vky342.openerp.ui.theme.search_account_unfocused_text_color_for_edit_account
import com.vky342.openerp.ui.theme.search_item_card_shadow_color


@Composable
fun ItemEdit(viewModel : InventoryList_VM = hiltViewModel()){

    val context : Context = LocalContext.current

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    var selectedOptionText by remember { mutableStateOf("") }

    var old_item by remember { mutableStateOf(Item("",0.0,0.0,0)) }

    var new_item by remember { mutableStateOf(Item("",0.0,0.0,0)) }

    var select_item_selected_enalbled = remember { mutableStateOf(true) }

    var options = viewModel.item_list.value

    var expanded = remember { mutableStateOf(false) }

    // filter options based on text field value
    var filteringOptions = options.filter {
        it.itemName.contains(selectedOptionText, ignoreCase = true)
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
                .pointerInput(Unit){
                    detectTapGestures(
                        onTap = {expanded.value = false},
                        onPress = {expanded.value = false}
                    )
                }
        ) {
            // Title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(50.dp)
            ) {
                Text(text = "Edit Item", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(
                    Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            // Item search bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                item_search_bar_for_edit_item(onReset = {
                    select_item_selected_enalbled.value = true
                    old_item = Item("",0.0,0.0,0)
                    new_item = Item("",0.0,0.0,0)
                    selectedOptionText = ""
                    viewModel.item_to_modify = old_item
                    Toast.makeText(context,"Select an account please", Toast.LENGTH_SHORT).show()
                },
                    enabled = select_item_selected_enalbled.value,modifier = Modifier
                    ,current_value = selectedOptionText
                    ,onVc = {
                        selectedOptionText = it
                        expanded.value = true
                    })
            }

            // Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(enabled = old_item != Item("",0.0,0.0,0),onVc = {new_item = new_item.copy(itemName = it)},value = new_item.itemName,icon = Icons.Outlined.Person,label = "Name",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }

            // selling price
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),enabled = old_item != Item("",0.0,0.0,0),onVc = {
                    try{
                    new_item = new_item.copy(itemSellingPrice = it.toDouble())
                    } catch (e : Exception){
                        Toast.makeText(context, "Please enter numbers only", Toast.LENGTH_SHORT).show()
                    }
                                                                            },value = new_item.itemSellingPrice.toString(),icon = Icons.Outlined.Person,label = "selling price",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }

            // cost price
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),enabled = old_item != Item("",0.0,0.0,0),onVc = {
                    try {
                        if (it == ""){
                            new_item = new_item.copy(itemPurchasePrice = 0.0)
                        }
                        new_item = new_item.copy(itemPurchasePrice = it.toDouble())
                    }catch (e : Exception){
                        Toast.makeText(context, "Please enter numbers only", Toast.LENGTH_SHORT).show()
                    }

                                                                            },value = new_item.itemPurchasePrice.toString(),icon = Icons.Outlined.Person,label = "purchase price",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }

            // quantity
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),enabled = old_item != Item("",0.0,0.0,0),onVc = {
                    try{
                        if (it == ""){
                            new_item = new_item.copy(itemQuantity = 0)
                        }
                    new_item = new_item.copy(itemQuantity = it.toInt())

                    } catch (e : Exception){
                        Toast.makeText(context, "Please enter numbers only", Toast.LENGTH_SHORT).show()
                    }
                                                                            },value = new_item.itemQuantity.toString(),icon = Icons.Outlined.Person,label = "quantity",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                Save_button(enabled = old_item != Item("",0.0,0.0,0),onClick = {
                    viewModel.item_to_modify = old_item
                    viewModel.updateItem(new_item)
                    Toast.makeText(context,"item saved", Toast.LENGTH_SHORT).show()

                    old_item = Item("",0.0,0.0,0)
                    new_item = Item("",0.0,0.0,0)
                    selectedOptionText = ""
                    select_item_selected_enalbled.value = true
                }
                    ,modifier = Modifier.align(Alignment.Center), label = "Save")
            }
        }

        // suggestion popUp
        if (expanded.value) {

            BackHandler (enabled = expanded.value) {
                expanded.value = false
            }

            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp)
                    .padding(top = 150.dp)
                    .padding(horizontal = (sidePadding/2).dp)
                    .align(Alignment.TopCenter)
                    .shadow(elevation = 4.dp,shape = RoundedCornerShape(10f))
                    .background(color = Color.White, shape = RoundedCornerShape(10f))
            ){
                items (filteringOptions){ item ->
                    Text(text = item.itemName + " " + "(" +item.itemQuantity+ ")",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(350),
                        modifier = Modifier.padding(vertical = 1.dp, horizontal = 2.dp).fillMaxWidth()
                            .clickable{
                                old_item = item
                                new_item = item
                                selectedOptionText = item.itemName + " " + "(" +item.itemQuantity+ ")"
                                select_item_selected_enalbled.value = false
                                expanded.value= false
                                Toast.makeText(context,"item selected", Toast.LENGTH_SHORT).show()
                            })
                }
            }
        }
    }
}


@Composable
fun item_search_bar_for_edit_item(onReset : () -> Unit,enabled : Boolean = true,modifier: Modifier = Modifier,onVc : (String) -> Unit,current_value: String){
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
                placeholder = { Text("Select item", color = search_account_content_color_for_edit_account)},
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
