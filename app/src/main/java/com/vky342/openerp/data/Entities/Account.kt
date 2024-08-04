package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Accounts")
data class Account(
    @PrimaryKey
    val name : String,
    val address : String,
    val contact : Int,
    val StartingBalance : Int,
    val StartingBalanceType : String

    // Foreign key

)