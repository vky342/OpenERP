package com.vky342.openerp.ui.screens.Inventory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vky342.openerp.ui.screens.ACCOUNTS.new_card
import com.vky342.openerp.ui.theme.background_color


@Preview
@Composable
fun inventoryOptionsScreenPrev() {

    val (height, width) = LocalConfiguration.current.run { screenHeightDp.dp to screenWidthDp.dp }
    val sidePadding = width.value * 0.08

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
            .background(color = background_color)
    ) {

    }
}