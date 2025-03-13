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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.ViewModels.transaction.Add_purchase_VM
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color


@Composable
fun AddPurchaseScreen(viewModel : Add_purchase_VM = hiltViewModel()){

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    val item_fill_popUp_status = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)

    )
    {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = background_color)
                .verticalScroll(rememberScrollState())
        ) {

            // Title
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(45.dp)
            ) {
                Text(text = "New purchase", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier.align(
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
                    DatePickerComposable (label = "Date"){  }
                }
            }
            Column (modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(top = 25.dp)){
                item_Card(modifier = Modifier.padding(horizontal = sidePadding.dp, vertical = 10.dp))
                item_Card(modifier = Modifier.padding(horizontal = sidePadding.dp, vertical = 10.dp))
                item_Card(modifier = Modifier.padding(horizontal = sidePadding.dp, vertical = 10.dp))
            }
        }

        floating_add_button(onClick = {item_fill_popUp_status.value = true},modifier = Modifier.padding(vertical = 85.dp, horizontal = (sidePadding/2).dp).align(Alignment.BottomEnd))

        if (item_fill_popUp_status.value){

            Box (modifier = Modifier
                .fillMaxSize()
                .background(color = Color.DarkGray.copy(alpha = 0.5f))
                .clickable {  }) {

                // touch barrier

            }

            item_fill_popUp(modifier = Modifier.padding(horizontal = 10.dp, vertical = 150.dp).align(Alignment.TopCenter),
                onCancel = {item_fill_popUp_status.value = false},
                onDone = {item_fill_popUp_status.value = false}
            )

        }

    }
}