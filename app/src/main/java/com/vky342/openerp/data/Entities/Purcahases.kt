package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "Purchases")
data class Purcahase(
    @PrimaryKey(autoGenerate = true)
    var purchaseId : Int,
    var purchaseDate : String,
    var ledgerId  : Int,
    var purchaseAmount : Int,
    )