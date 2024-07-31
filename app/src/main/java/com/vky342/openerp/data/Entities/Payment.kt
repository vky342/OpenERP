package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Payments", foreignKeys = [ForeignKey(entity = Ledger::class, parentColumns = ["ledgerId"], childColumns = ["accountLedgerId"], onDelete = ForeignKey.CASCADE)])
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val paymentId : Int,
    val paymentDate : Int,
    val paymentAmount : Int,
    val ledgerType : Int = 1,

    // Foreign keys

    val accountLedgerId : Int

)