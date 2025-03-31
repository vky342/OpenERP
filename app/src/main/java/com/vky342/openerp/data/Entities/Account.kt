package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var name : String,
    var address : String,
    var contact : String,
    var type : String
)