package com.vky342.openerp.ui.screens.ACCOUNTS


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.ViewModels.Account.modify_Account_vm
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color

@Composable
fun ModifyAccountScreen( viewModel: modify_Account_vm = hiltViewModel() ){

    val context : Context = LocalContext.current

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    var old_Account by remember { mutableStateOf(Account(0,"","","","")) }
    
    var new_Account by remember { mutableStateOf(Account(0,"","","","")) }

    var selectedOptionText by remember { mutableStateOf("") }

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
                Account_selector(onClick = {selectedOptionText = it},
                    onVC = {selectedOptionText = it},
                    selectedOptionText = selectedOptionText,
                    defaultOption = old_Account,
                    options = viewModel.old_Account_list.value,
                    onSelect = {
                        old_Account = it
                        new_Account = it
                    })
            }

            // Account Type Selector
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                account_registration_type_selector(selected_type = new_Account.type,
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
                form_fields(onVc = {new_Account = new_Account.copy(name = it)},value = new_Account.name,icon = Icons.Outlined.Person,label = "Name",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }

            // Address
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                form_fields(value = new_Account.address, onVc = {new_Account = new_Account.copy(address = it)},icon = Icons.Outlined.LocationOn,label = "Address",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }
            // Contact
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                form_fields(value = new_Account.contact, onVc = {new_Account = new_Account.copy(contact = it)},icon = Icons.Outlined.Call,label = "Contact",modifier = Modifier.padding(horizontal = sidePadding.dp).align(
                    Alignment.CenterStart))
            }
            // save button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                Save_button(onClick = {
                    viewModel.account_to_modify = old_Account
                    if(viewModel.update_Account(new_Account)){
                        Toast.makeText(context,"Account saved", Toast.LENGTH_SHORT).show()
                    }else{Toast.makeText(context,"Account Invalid", Toast.LENGTH_SHORT).show()}
                    old_Account = Account(0,"","","","")
                    new_Account = Account(0,"","","","")
                    selectedOptionText = ""
                                   }
                    ,modifier = Modifier.align(Alignment.Center), label = "Save")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Account_selector(selectedOptionText : String, onClick: (String) -> Unit,
                     onVC : (String) -> Unit,
                     defaultOption : Account, options : List<Account> = listOf(Account(id = 8, name = "Olivia Taylor", address = "505 Walnut St, AZ", contact = "2109876543", type = "Customer")
), onSelect : (Account) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {

        account_search_bar_for_edit_account(modifier = Modifier.menuAnchor()
            ,current_value = selectedOptionText
            ,onVc = {onVC(it)})

        // filter options based on text field value
        val filteringOptions = options.filter {
            it.name.contains(selectedOptionText, ignoreCase = true)
                    || it.address.contains(selectedOptionText, ignoreCase = true)
                    || it.contact.contains(selectedOptionText, ignoreCase = true)
        }


        if (filteringOptions.isNotEmpty()) {
            DropdownMenu(offset = DpOffset(x = 10.dp,y=0.dp),
                modifier = Modifier
                    .background(Color.White)
                    .exposedDropdownSize(true)
                ,
                properties = PopupProperties(focusable = false),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                filteringOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.name) },
                        onClick = {
                            onClick(selectionOption.name)
                            expanded = false
                            onSelect(selectionOption)
                            ImeAction.Done
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}
