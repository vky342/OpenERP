package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "SaleEntries")
data class SaleEntry(
    @PrimaryKey(autoGenerate = true)
    var entryId : Int,
    var entryQuantity : Int,
    var entryPrice : Double,
    var discount : Double = 0.0,
    var finalPrice : Double?,

    var itemName : String,
    var saleId : Int
)