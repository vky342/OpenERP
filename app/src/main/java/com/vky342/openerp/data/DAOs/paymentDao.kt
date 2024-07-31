package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Payment
import kotlinx.coroutines.flow.Flow

@Dao
interface paymentsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(payment: Payment)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(payment: Payment)

    @Delete
    suspend fun delete(payment: Payment)

    // Function for screening

    @Query("SELECT * FROM Payments")
    fun getAllPayments() : Flow<List<Payment>>

    @Query("SELECT * FROM Payments WHERE paymentDate = :date")
    fun getAllPaymentOnDate(date: Int) : Flow<List<Payment>>

    @Query("SELECT * FROM Payments WHERE accountLedgerId = :ledgerId")
    fun getAllPaymentsInLedger(ledgerId : Int) : Flow<List<Payment>>
}