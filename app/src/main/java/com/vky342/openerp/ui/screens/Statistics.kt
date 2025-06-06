package com.vky342.openerp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.ViewModels.Statistics.SaleStatsVM

@Composable
fun SalesStatistics(viewModel: SaleStatsVM = hiltViewModel()){
    val dataD = viewModel.totalTodaySales.observeAsState(0.0)
    val saleEntries = viewModel.listOfSaleEntries.observeAsState(listOf())
    val monthlyTotal = viewModel.totalMonthlySale.observeAsState()
    Column (modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
            LazyColumn (modifier = Modifier.fillMaxWidth()){ itemsIndexed(saleEntries.value){
                    index, item ->   Text(item.itemName+" : "+item.entryQuantity+" : "+item.entryPrice+" : "+item.discount+" : "+item.finalPrice)
            }
        }
            Text(dataD.value.toString())
            Text(monthlyTotal.value.toString())
        Button(onClick = {
            viewModel.getMonthlySalesTotal("04/2025") }, modifier = Modifier.width(50.dp).height(30.dp)) {
            Text("press")
            }

        }
    }
}