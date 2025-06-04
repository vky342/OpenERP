package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Payments")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    var paymentId : Int,
    var paymentDate : String,
    var paymentAmount : Double,
    var ledgerId : Int
)