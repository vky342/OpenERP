package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Ledgers")
data class Ledger(
    @PrimaryKey(autoGenerate = true)
    var ledgerId : Int,
    var ledgerNetBalance: Double,
    var accountName : String,
)