package com.vky342.openerp.data.ViewModels.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.Repositories.SaleRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Add_sale_Vm @Inject constructor( private val saleRepo: SaleRepo) : ViewModel() {

    val SampleSaleEntries = listOf(
        SaleEntry(entryId = 0, entryQuantity = 5, entryPrice = 100.0, discount = 10.0, finalPrice = 450.0, itemName = "Laptop", saleId = 0),
        SaleEntry(entryId = 0, entryQuantity = 2, entryPrice = 200.0, discount = 20.0, finalPrice = 360.0, itemName = "Monitor", saleId = 0),
        SaleEntry(entryId = 0, entryQuantity = 10, entryPrice = 50.0, discount = 5.0, finalPrice = 475.0, itemName = "Keyboard", saleId = 0),
        SaleEntry(entryId = 0, entryQuantity = 3, entryPrice = 150.0, discount = 15.0, finalPrice = 405.0, itemName = "Mouse", saleId = 0),
        SaleEntry(entryId = 0, entryQuantity = 4, entryPrice = 75.0, discount = 0.0, finalPrice = 300.0, itemName = "Headphones", saleId = 0),
        SaleEntry(entryId = 0, entryQuantity = 1, entryPrice = 500.0, discount = 50.0, finalPrice = 450.0, itemName = "Printer", saleId = 0),
        SaleEntry(entryId = 0, entryQuantity = 8, entryPrice = 20.0, discount = 0.0, finalPrice = 160.0, itemName = "USB Cable", saleId = 0),
        SaleEntry(entryId = 0, entryQuantity = 7, entryPrice = 30.0, discount = 2.0, finalPrice = 196.0, itemName = "Charger", saleId = 0),
        SaleEntry(entryId = 0, entryQuantity = 12, entryPrice = 15.0, discount = 1.5, finalPrice = 174.0, itemName = "HDMI Cable", saleId = 0),
        SaleEntry(entryId = 0, entryQuantity = 6, entryPrice = 40.0, discount = 4.0, finalPrice = 216.0, itemName = "Wireless Mouse", saleId = 0)
    )

    private fun Save_Sale (account_name : String, sale: Sale, list_of_saleEntries : List<SaleEntry>) {

        /*
        *
        * name should be passed with Pre-Checking
        * sale should contain id = 0, and ledgerId = 0
        * list_of_saleEntries should contain entryId = 0 and purchaseId = 0, it will be set automatically
        * only name is to be passed and other data will be set automatically , as per the latest Purchase Id and account's Ledger Id.
        *
        */


        viewModelScope.launch (Dispatchers.IO){
            saleRepo.AddSale(
                account_name = account_name,
                sale = sale,
                list_of_saleEntry = list_of_saleEntries
            )
        }

    }

    fun test_initialize() {
        Save_Sale(account_name = "kunal", sale = Sale(0,"something",0,1800), list_of_saleEntries = SampleSaleEntries)
    }
}