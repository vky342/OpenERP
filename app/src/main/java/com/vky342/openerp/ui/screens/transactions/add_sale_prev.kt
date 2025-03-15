package com.vky342.openerp.ui.screens.transactions

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vky342.openerp.ui.screens.ACCOUNTS.form_fields
import com.vky342.openerp.ui.theme.New_account_title_color
import com.vky342.openerp.ui.theme.background_color
import com.vky342.openerp.ui.theme.title_color
import com.vky342.openerp.ui.theme.var_amount_row_colour

@Preview
@Composable
fun add_sale_prev(){
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
            Text(text = "New sale", color = New_account_title_color,fontSize = 32.sp, modifier = Modifier
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
                DatePickerComposable (label = "Date"){  }
            }
        }
        Column (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 25.dp)){
            Text("S a l e   S u m m a r y",
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