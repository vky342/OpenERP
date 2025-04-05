package com.vky342.openerp.ui.screens.Inventory

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.ViewModels.InventoryList_VM
import com.vky342.openerp.ui.screens.ACCOUNTS.Save_button
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color


@Composable
fun newItem(viewModel : InventoryList_VM = hiltViewModel()) {

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    val context : Context = LocalContext.current

    val name = remember{ mutableStateOf("")}

    val sp = remember { mutableStateOf("") }

    var pp = remember { mutableStateOf("") }

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
                Text(text = "New Item", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(Alignment.CenterStart).padding(horizontal = sidePadding.dp))
            }

            // Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(trailing_icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,icon = Icons.Outlined.Info, onVc = {name.value = it},value = name.value,label = "Name",modifier = Modifier.padding(horizontal = sidePadding.dp).align(Alignment.CenterStart))
            }

            // set selling price
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(trailing_icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,keyboardOptions = KeyboardOptions(autoCorrect = false, keyboardType = KeyboardType.Decimal),icon = Icons.Outlined.Info, onVc = {sp.value = it},value = sp.value,label = "default selling price",modifier = Modifier.padding(horizontal = sidePadding.dp).align(Alignment.CenterStart))
            }

            // set cost price
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)
            ) {
                form_fields(trailing_icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,keyboardOptions = KeyboardOptions(autoCorrect = false, keyboardType = KeyboardType.Decimal),icon = Icons.Outlined.Info, onVc = {pp.value = it},value = pp.value,label = "default cost price",modifier = Modifier.padding(horizontal = sidePadding.dp).align(Alignment.CenterStart))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(70.dp)

            ) {
                Save_button(modifier = Modifier.align(Alignment.Center),

                    onClick = {

                    if(name.value != "" && sp.value != "" && pp.value != ""){

                        var res = viewModel.NewItem(item = Item(name.value,sp.value.toDouble(),pp.value.toDouble(),0))
                        if (res == ""){
                            Toast.makeText(context,"New Item added", Toast.LENGTH_SHORT).show()
                            name.value = ""
                            sp.value = ""
                            sp.value = ""
                        }else{
                            Toast.makeText(context,"Error Item name should be unique", Toast.LENGTH_SHORT).show()
                            name.value = ""
                            sp.value = ""
                            pp.value = ""
                        }

                    }
                    else{
                        Toast.makeText(context,"Please fill the form", Toast.LENGTH_SHORT).show()
                    }
                    },

                    label = "Save")
            }
        }
    }
}