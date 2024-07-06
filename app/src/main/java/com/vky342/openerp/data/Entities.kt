package com.vky342.openerp.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Accounts")
data class Account(
    @PrimaryKey
    val name : String,
    val address : String,
    val contact : Int,
    val netBalance : Int
    )

@Entity(
    tableName = "Bills",
    foreignKeys = [
        ForeignKey(entity = Account::class, parentColumns = ["name"], childColumns = ["accountNameFk"])
    ]
)
data class Bill(
    @PrimaryKey
    val billId : Int,
    val billDate : Int,
    val billAmount : Int = 0,
    val billType : Int, /// either sale(1) or purchase(0)

//foreign keys
    val accountNameFk : String

)

@Entity(
    tableName = "BillEntries",
    foreignKeys = [
        ForeignKey(entity = Bill::class, parentColumns = ["BillId"], childColumns = ["BillIdFk"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        ForeignKey(entity = Item::class, parentColumns = ["itemName"], childColumns = ["itemNameFk"], onDelete = ForeignKey.RESTRICT, onUpdate = ForeignKey.CASCADE)
    ])
data class BillEntry(
    @PrimaryKey
    val entryId : Int,
    val entryQuantity : Int,
    val discount : Int = 0,
    val finalPrice : Int,

    //Foreign keys

    val billIdFk : Int,
    val itemNameFk : String,
)

@Entity(tableName = "ItemInventory")
data class Item(
    @PrimaryKey
    val itemName : String,
    val itemListPrice : Int,
    val itemPurchasePrice : Int,
    val itemQuantity : Int,
)