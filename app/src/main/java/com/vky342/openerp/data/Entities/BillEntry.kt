package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "BillEntries",
    foreignKeys = [
        ForeignKey(entity = Bill::class, parentColumns = ["BillId"], childColumns = ["BillIdFk"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        ForeignKey(entity = Item::class, parentColumns = ["itemName"], childColumns = ["itemNameFk"], onDelete = ForeignKey.NO_ACTION, onUpdate = ForeignKey.CASCADE)
    ])
data class BillEntry(
    @PrimaryKey(autoGenerate = true)
    var entryId : Int,
    var entryQuantity : Int,
    var entryPrice : Double,
    var discount : Double = 0.0,
    var finalPrice : Double?,

    //Foreign keys

    var billIdFk : Int,
    var itemNameFk : String,
)