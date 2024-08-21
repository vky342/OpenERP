package com.vky342.openerp.data.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Repositories.InventoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InventoryList_VM @Inject constructor( private val inventoryRepo: InventoryRepo) : ViewModel() {


    val sampleItems = listOf(
        Item(
            itemName = "Laptop",
            itemSellingPrice = 1200.0,
            itemPurchasePrice = 1000.0,
            itemQuantity = 10
        ),
        Item(
            itemName = "Smartphone",
            itemSellingPrice = 800.0,
            itemPurchasePrice = 650.0,
            itemQuantity = 25
        ),
        Item(
            itemName = "Headphones",
            itemSellingPrice = 150.0,
            itemPurchasePrice = 100.0,
            itemQuantity = 50
        ),
        Item(
            itemName = "Keyboard",
            itemSellingPrice = 75.0,
            itemPurchasePrice = 50.0,
            itemQuantity = 40
        ),
        Item(
            itemName = "Monitor",
            itemSellingPrice = 300.0,
            itemPurchasePrice = 250.0,
            itemQuantity = 20
        ),
        Item(
            itemName = "Mouse",
            itemSellingPrice = 30.0,
            itemPurchasePrice = 20.0,
            itemQuantity = 60
        ),
        Item(
            itemName = "Printer",
            itemSellingPrice = 200.0,
            itemPurchasePrice = 150.0,
            itemQuantity = 15
        ),
        Item(
            itemName = "Router",
            itemSellingPrice = 100.0,
            itemPurchasePrice = 80.0,
            itemQuantity = 30
        ),
        Item(
            itemName = "External Hard Drive",
            itemSellingPrice = 120.0,
            itemPurchasePrice = 90.0,
            itemQuantity = 35
        ),
        Item(
            itemName = "Webcam",
            itemSellingPrice = 80.0,
            itemPurchasePrice = 60.0,
            itemQuantity = 45
        )
    )

    fun Add_Item_to_inventory (list_of_item : List<Item> = sampleItems) {
        viewModelScope.launch (Dispatchers.IO) {
            inventoryRepo.insert_item_into_inventory(list_of_item)
        }
    }

    fun init_Start () {
        inventoryRepo.retrun_All_items_in_inventory()
    }
}