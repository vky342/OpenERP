package com.vky342.openerp.data.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Repositories.InventoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InventoryList_VM @Inject constructor( private val inventoryRepo: InventoryRepo) : ViewModel() {

    val item_list : MutableState<List<Item>> = mutableStateOf(listOf())
    var item_to_modify : Item = Item("",0.0,0.0,0)

    init {
        viewModelScope.launch {
            inventoryRepo.retrun_All_items_in_inventory().collect(){
                    newData -> item_list.value = newData
                Log.d("STATUS", "collected items line 25 -vm")
            }
        }
    }

    private fun validateNewItem(
        newName: String,
        newSP: String,
        newPP: String,
        newQuantity: String
    ): Item? {
        // Check if name is blank or too short
        if (newName.isBlank() || newName.length < 2) {
            Log.d("validateNewItem", "Invalid name")
            return null
        }

        val sellingPrice: Double
        val purchasePrice: Double
        val quantity: Int

        try {
            sellingPrice = newSP.toDouble()
            purchasePrice = newPP.toDouble()
            quantity = newQuantity.toInt()
        } catch (e: NumberFormatException) {
            Log.d("validateNewItem", "Invalid number format in price or quantity")
            return null
        }

        // Check for non-negative values
        if (sellingPrice < 0.0 || purchasePrice < 0.0 || quantity < 0) {
            Log.d("validateNewItem", "Negative values found")
            return null
        }

        // Check for duplicate item name
        val isDuplicate = item_list.value.any { it.itemName == newName }
        if (isDuplicate) {
            Log.d("validateNewItem", "Duplicate item name")
            return null
        }

        // All validations passed, return the new Item
        return Item(
            itemName = newName,
            itemSellingPrice = sellingPrice,
            itemPurchasePrice = purchasePrice,
            itemQuantity = quantity
        )
    }


    private fun validateEditItem(
        newName: String,
        newSP: String,
        newPP: String,
        newQuantity: String
    ): Item? {
        // Check if name is blank or too short
        if (newName.isBlank() || newName.length < 2) {
            Log.d("validateEditItem", "Invalid name")
            return null
        }

        val sellingPrice: Double
        val purchasePrice: Double
        val quantity: Int

        try {
            sellingPrice = newSP.toDouble()
            purchasePrice = newPP.toDouble()
            quantity = newQuantity.toInt()
        } catch (e: NumberFormatException) {
            Log.d("validateEditItem", "Invalid number format")
            return null
        }

        // Check for non-negative values
        if (sellingPrice < 0.0 || purchasePrice < 0.0 || quantity < 0) {
            Log.d("validateEditItem", "Negative values found")
            return null
        }

        // Check for duplicate item name (excluding the item being modified)
        val isDuplicateName = item_list.value
            .filter { it.itemName != item_to_modify.itemName }
            .any { it.itemName == newName }

        if (isDuplicateName) {
            Log.d("validateEditItem", "Duplicate item name")
            return null
        }

        // All checks passed, return the new Item object
        return Item(
            itemName = newName,
            itemSellingPrice = sellingPrice,
            itemPurchasePrice = purchasePrice,
            itemQuantity = quantity
        )
    }


    fun updateItem(newName : String, newSP : String, newPP : String, newQuantity : String) : Boolean{
        val validationResult = validateEditItem(newName,newSP,newPP,newQuantity)
        if(validationResult == null){
            return false
        }
        viewModelScope.launch {
            inventoryRepo.updateItem(item_to_modify,validationResult)
        }
        return true
    }

    fun NewItem(newName : String, newSP : String, newPP : String, newQuantity : String) : Boolean{
        val validationResult = validateNewItem(newName,newSP,newPP,newQuantity)
        if (validationResult == null) {
            return false
        }
        viewModelScope.launch {
            inventoryRepo.addNewItem(validationResult)
        }
        return true
    }
}