package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "Receipts")
data class Receipt(
    @PrimaryKey(autoGenerate = true)
    var receiptId : Int,
    var receiptDate : String,
    var receiptAmount : Double,
    var ledgerId : Int
)