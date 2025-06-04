package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Payment

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
    suspend fun getAllPayments() : List<Payment>

    @Query("SELECT * FROM Payments WHERE paymentDate = :date")
    fun getAllPaymentOnDate(date: String) : List<Payment>

    @Query("SELECT * FROM Payments WHERE ledgerId = :ledgerId")
    fun getAllPaymentsInLedger(ledgerId : Int) : List<Payment>

    @Query("SELECT * FROM Payments WHERE paymentId = (SELECT max(paymentId) FROM Payments)")
    suspend fun getLastPayment() : Payment?

    @Query("SELECT * FROM Payments WHERE paymentId = :paymentId")
    suspend fun getPaymentById(paymentId: Int): Payment?
}