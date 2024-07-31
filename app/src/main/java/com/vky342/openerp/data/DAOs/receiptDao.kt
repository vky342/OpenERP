package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Receipt
import kotlinx.coroutines.flow.Flow

@Dao
interface receiptDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(receipt: Receipt)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(receipt: Receipt)

    @Delete
    suspend fun delete(receipt: Receipt)

    // Function for screening

    @Query("SELECT * FROM Receipts")
    fun getAllPayments() : Flow<List<Receipt>>

    @Query("SELECT * FROM Receipts WHERE receiptDate = :date")
    fun getAllPaymentOnDate(date: Int) : Flow<List<Receipt>>

    @Query("SELECT * FROM Receipts WHERE accountLedgerId = :ledgerId")
    fun getAllPaymentsInLedger(ledgerId : Int) : Flow<List<Receipt>>
}