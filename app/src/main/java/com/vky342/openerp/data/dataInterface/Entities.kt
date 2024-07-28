package com.vky342.openerp.data.dataInterface

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Accounts")
data class Account(
    @PrimaryKey
    val name : String,
    val address : String,
    val contact : Int,
    val netBalance : Int,

    // Foreign key

    )

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

@Entity(tableName = "ItemInventory")
data class Item(
    @PrimaryKey
    val itemName : String,
    val itemListPrice : Int,
    val itemPurchasePrice : Int,
    val itemQuantity : Int,
)

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

@Entity(tableName = "Ledgers", foreignKeys = [ForeignKey(entity = Account::class, parentColumns = ["name"], childColumns = ["accountNameFk"], onDelete = ForeignKey.CASCADE)])
data class Ledger(
    @PrimaryKey(autoGenerate = true)
    val ledgerId : Int,
    val ledgerNetBalance: Int,

    // Foreign Keys

    val accountNameFk : String,
)