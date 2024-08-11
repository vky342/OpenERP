package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Receipt
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface receiptDao {

    @Insert
    suspend fun insert(receipt: Receipt)

    @Update
    suspend fun update(receipt: Receipt)

    @Delete
    suspend fun delete(receipt: Receipt)

    // Function for screening

    @Query("SELECT * FROM Receipts")
    fun getAllPayments() : List<Receipt>

    @Query("SELECT * FROM Receipts WHERE receiptDate = :date")
    fun getAllPaymentOnDate(date: String) : List<Receipt>

    @Query("SELECT * FROM Receipts WHERE ledgerId = :ledgerId")
    fun getAllPaymentsInLedger(ledgerId : Int) : List<Receipt>
}