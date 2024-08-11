package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(
    tableName = "Sales"
)
data class Sale(
    @PrimaryKey(autoGenerate = true)
    var saleId : Int,
    var saleDate : String,
    var ledgerId  : Int,
    var saleAmount : Int,

    )