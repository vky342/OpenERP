package com.vky342.openerp.data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Payments")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val paymentId : Int,
    val paymentDate : String,
    val paymentAmount : Double,
    val ledgerId : Int
)