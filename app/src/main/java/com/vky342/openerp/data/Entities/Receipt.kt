package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Receipts", foreignKeys = [ForeignKey(entity = Ledger::class, parentColumns = ["ledgerId"], childColumns = ["accountLedgerId"], onDelete = ForeignKey.CASCADE)])
data class Receipt(
    @PrimaryKey(autoGenerate = true)
    val receiptId : Int,
    val receiptDate : Int,
    val receiptAmount : Int,
    val ledgerType : Int = 0,

    // Foreign Keys

    val accountLedgerId: Int

)