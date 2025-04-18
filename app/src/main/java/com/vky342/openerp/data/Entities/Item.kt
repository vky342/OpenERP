package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemInventory")
data class Item(
    @PrimaryKey
    val itemName : String,
    val itemSellingPrice : Double = 0.0,
    val itemPurchasePrice : Double,
    val itemQuantity : Int,
)