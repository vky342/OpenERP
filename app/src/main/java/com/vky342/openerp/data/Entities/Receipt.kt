package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "Receipts")
data class Receipt(
    @PrimaryKey(autoGenerate = true)
    val receiptId : Int,
    val receiptDate : String,
    val receiptAmount : Double,
    val ledgerId : String
)