package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Bills",
    foreignKeys = [
        ForeignKey(entity = Account::class, parentColumns = ["name"], childColumns = ["accountNameFk"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        ForeignKey(entity = Ledger::class, parentColumns = ["ledgerId"], childColumns = ["ledgerIdFk"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
    ]
)
data class Bill(
    @PrimaryKey(autoGenerate = true)
    val billId : Int,
    var billDate : Int,
    var billAmount : Double = 0.0,
    var ledgerType: Int, /// either sale(0) or purchase(1)

    //foreign keys
    var accountNameFk : String,
    var ledgerIdFk: Int

)