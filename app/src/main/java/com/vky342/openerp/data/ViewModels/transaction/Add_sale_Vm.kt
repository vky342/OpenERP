package com.vky342.openerp.data.ViewModels.transaction

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Entities.Purcahase
import com.vky342.openerp.data.Entities.PurchaseEntry
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.Repositories.AccountRepo
import com.vky342.openerp.data.Repositories.InventoryRepo
import com.vky342.openerp.data.Repositories.SaleRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Add_sale_Vm @Inject constructor(
    private val saleRepo: SaleRepo,
    private val accountRepo: AccountRepo,
    private val inventoryRepo: InventoryRepo
) : ViewModel() {

    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    val all_items_in_inventory : MutableState<List<Item>> = mutableStateOf(listOf())
    val saleID : MutableState<Int> = mutableStateOf(0)

    init {
        viewModelScope.launch {
            accountRepo.get_every_Account().collect(){
                    newData -> old_Account_list.value = newData
                Log.d("STATUS", "collected accounts line 24 -vm")
            }
        }
    }

    init {
        viewModelScope.launch {
            inventoryRepo.retrun_All_items_in_inventory().collect(){
                    newData -> all_items_in_inventory.value = newData
                Log.d("STATUS", "collected items line 40 -vm")
            }
        }
    }

    init {
        viewModelScope.launch {
            saleID.value = saleRepo.getLatestSaleID()
            Log.d("STATUS", "fetched latest sale ID")
        }
    }

    private fun reloadSale(){
        viewModelScope.launch {
            saleID.value = saleRepo.getLatestSaleID()
            Log.d("DEBUG",saleID.value.toString())
            Log.d("STATUS", "fetched latest sale ID")
        }

    }

    private fun Save_Sale (account_name : String, sale: Sale, list_of_saleEntries : List<SaleEntry>) {

        viewModelScope.launch (Dispatchers.IO){
            saleRepo.AddSale(
                account_name = account_name,
                sale = sale,
                list_of_saleEntry = list_of_saleEntries
            )
            reloadSale()
        }

    }

    fun add_sale(name : String,sale: Sale, listOfEntry: List<SaleEntry>){
        Save_Sale(name,sale,listOfEntry)
    }

    fun validateItemPopUPInput(
        selectedItemName: String,
        selectedItemPrice: String,
        selectedItemDiscount: String,
        selectedItemQuantity: String
    ): Boolean {
        // 1. Validate item name
        if (selectedItemName.isBlank() || selectedItemName.length < 2) {
            return false
        }

        // 2. Try parsing and validating price
        val price = selectedItemPrice.toDoubleOrNull()
        if (price == null || price < 0.0 || price > 1_000_000) {
            return false
        }

        // 3. Handle empty discount as 0.0
        val discount = if (selectedItemDiscount.isBlank()) {
            0.0
        } else {
            selectedItemDiscount.toDoubleOrNull()
        }

        if (discount == null || discount < 0.0 || discount > 100.0) {
            return false
        }

        // 4. Try parsing and validating quantity
        val quantity = selectedItemQuantity.toIntOrNull()
        if (quantity == null || quantity < 0 || quantity > 10_000) {
            return false
        }

        // 5. Check that effective price after discount is not negative
        val effectivePrice = price - (price * discount / 100)
        if (effectivePrice < 0.0) {
            return false
        }

        // All validations passed
        return true
    }

}