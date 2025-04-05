package com.vky342.openerp.data.Repositories

import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Modules.OpenERPDataBase
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

class InventoryRepo (private val openERPDataBase: OpenERPDataBase) {

    val inventoryDao = openERPDataBase.getItemInventoryDao()

    fun retrun_All_items_in_inventory () : Flow<List<Item>> {
        return inventoryDao.getAllItemInInventory()
    }

    suspend fun insert_item_into_inventory ( list_of_item : List<Item> ) {
        for (item in list_of_item) {
            inventoryDao.insert(item)
        }
    }

    suspend fun addNewItem(item: Item){
        inventoryDao.insert(item)

    }
}