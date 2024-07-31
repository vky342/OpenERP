package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Ledgers", foreignKeys = [ForeignKey(entity = Account::class, parentColumns = ["name"], childColumns = ["accountNameFk"], onDelete = ForeignKey.CASCADE)])
data class Ledger(
    @PrimaryKey(autoGenerate = true)
    val ledgerId : Int,
    val ledgerNetBalance: Int,

    // Foreign Keys

    val accountNameFk : String,
)