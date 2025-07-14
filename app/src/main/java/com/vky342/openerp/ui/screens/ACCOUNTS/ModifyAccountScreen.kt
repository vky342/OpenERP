package com.vky342.openerp.ui.screens.ACCOUNTS


import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.systemGestureExclusion
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.text2.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.ViewModels.Account.modify_Account_vm
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.shadow_color

@Composable
fun ModifyAccountScreen( viewModel: modify_Account_vm = hiltViewModel() ){

    val context : Context = LocalContext.current

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    var old_Account by remember { mutableStateOf(Account(0,"","","","")) }
    
    var new_Account by remember { mutableStateOf(Account(0,"","","","")) }

    var selectedOptionText by remember { mutableStateOf("") }

    val options = viewModel.old_Account_list.value

    val expanded = remember { mutableStateOf(false) }

    val select_account_selected_enalbled = remember { mutableStateOf(true) }

    // filter options based on text field value
    val filteringOptions = options.filter {
        it.name.contains(selectedOptionText, ignoreCase = true) || it.address.contains(selectedOptionText, ignoreCase = true) || it.contact.contains(selectedOptionText, ignoreCase = true)
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
                Text(text = "Edit account", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(
                    Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            // Account search bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {

                account_search_bar_for_edit_account(onReset = {
                    select_account_selected_enalbled.value = true
                    old_Account = Account(0,"","","","")
                    new_Account = Account(0,"","","","")
                    selectedOptionText = ""
                    viewModel.account_to_modify = old_Account
                    Toast.makeText(context,"Select an account please", Toast.LENGTH_SHORT).show()
                                                              },
                    enabled = select_account_selected_enalbled.value,modifier = Modifier
                    ,current_value = selectedOptionText
                    ,onVc = {
                        selectedOptionText = it
                        expanded.value = true
                    })
            }

            // Account Type Selector
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                account_registration_type_selector(enabled = !select_account_selected_enalbled.value,
                    selected_type = new_Account.type,
                    customer_click = {new_Account = new_Account.copy(type = "customer")},
                    supplier_click = {new_Account = new_Account.copy(type = "supplier")},
                    regular_click = {new_Account = new_Account.copy(type = "regular")})
            }


            // Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(enabled = old_Account != Account(0,"","","",""),onVc = {new_Account = new_Account.copy(name = it)},value = new_Account.name,icon = Icons.Outlined.Person,label = "Name",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }

            // Address
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                form_fields(enabled = old_Account != Account(0,"","","",""),value = new_Account.address, onVc = {new_Account = new_Account.copy(address = it)},icon = Icons.Outlined.LocationOn,label = "Address",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }
            // Contact
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                form_fields(keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
                    enabled = old_Account != Account(0,"","","",""),value = new_Account.contact, onVc = {new_Account = new_Account.copy(contact = it)},icon = Icons.Outlined.Call,label = "Contact",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }
            // save button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                Save_button(enabled = old_Account != Account(0,"","","",""),onClick = {
                    viewModel.account_to_modify = old_Account

                    if(viewModel.update_Account(new_Account)){
                        Toast.makeText(context,"Account saved", Toast.LENGTH_SHORT).show()
                        old_Account = Account(0,"","","","")
                        new_Account = Account(0,"","","","")
                        selectedOptionText = ""
                        select_account_selected_enalbled.value = true
                    }else{
                        Toast.makeText(context,"Account Invalid", Toast.LENGTH_SHORT).show()}
                }
                    ,modifier = Modifier.align(Alignment.Center), label = "Save")
            }

        }

        // suggestions popUP
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
                items (filteringOptions){ account ->
                    Text(text = account.name + " " + "(" +account.type+ ")",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(350),
                        modifier = Modifier.padding(vertical = 1.dp, horizontal = 2.dp).fillMaxWidth()
                        .clickable{
                        old_Account = account
                        new_Account = account
                        selectedOptionText = account.name + " " + "(" +account.type+ ")"
                            select_account_selected_enalbled.value = false
                        expanded.value= false
                            Toast.makeText(context,"Account selected", Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
    }
}
