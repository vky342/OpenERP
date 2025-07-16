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
import com.vky342.openerp.data.Repositories.AccountRepo
import com.vky342.openerp.data.Repositories.InventoryRepo
import com.vky342.openerp.data.Repositories.PurchaseRepo
import com.vky342.openerp.ui.screens.transactions.item_popup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Add_purchase_VM @Inject constructor(
    private val purchaseRepo: PurchaseRepo,
    private val accountRepo: AccountRepo,
    private val inventoryRepo: InventoryRepo
) : ViewModel() {

    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    val all_items_in_inventory : MutableState<List<Item>> = mutableStateOf(listOf())
    val purchaseID : MutableState<Int> = mutableStateOf(0)

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
            purchaseID.value = purchaseRepo.getLatestPurchaseID()
            Log.d("STATUS", "fetched latest purchase ID")
        }
    }

    private fun reloadPurchase(){
        viewModelScope.launch {
            purchaseID.value = purchaseRepo.getLatestPurchaseID()
            Log.d("DEBUG",purchaseID.value.toString())
            Log.d("STATUS", "fetched latest sale ID")
        }

    }

    private fun Save_Purchase(account_name : String,purchase : Purcahase, list_of_purEntries : List<PurchaseEntry>){

        viewModelScope.launch (Dispatchers.IO){

            purchaseRepo.AddPurchase(
                account_name = account_name,
                purchase = purchase,
                list_of_purchaseEntry = list_of_purEntries
            )
            reloadPurchase()

        }
    }

    fun add_purchase(name : String,purchase: Purcahase, listOfEntry: List<PurchaseEntry>){
        Save_Purchase(name,purchase,listOfEntry)
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