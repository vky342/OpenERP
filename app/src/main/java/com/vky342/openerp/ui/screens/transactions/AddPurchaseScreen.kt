package com.vky342.openerp.ui.screens.transactions


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.vky342.openerp.data.ViewModels.transaction.Add_purchase_VM


@Composable
fun AddPurchaseScreen(viewModel : Add_purchase_VM = hiltViewModel()){
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "Hello world!", modifier = Modifier.align(Alignment.Center))
        viewModel.ini_test()
    }
}