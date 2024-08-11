package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Payment
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface paymentsDao {

    @Insert
    suspend fun insert(payment: Payment)

    @Update
    suspend fun update(payment: Payment)

    @Delete
    suspend fun delete(payment: Payment)

    // Function for screening

    @Query("SELECT * FROM Payments")
    fun getAllPayments() : List<Payment>

    @Query("SELECT * FROM Payments WHERE paymentDate = :date")
    fun getAllPaymentOnDate(date: String) : List<Payment>

    @Query("SELECT * FROM Payments WHERE ledgerId = :ledgerId")
    fun getAllPaymentsInLedger(ledgerId : Int) : List<Payment>
}