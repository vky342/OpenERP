package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val address : String,
    val contact : Int,
    val type : String
)