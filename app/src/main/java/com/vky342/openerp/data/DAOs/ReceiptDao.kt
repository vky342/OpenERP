package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Receipt

@Dao
interface ReceiptDao {

    @Insert
    suspend fun insert(receipt: Receipt)

    @Update
    suspend fun update(receipt: Receipt)

    @Delete
    suspend fun delete(receipt: Receipt)

    // Function for screening

    @Query("SELECT * FROM Receipts")
    fun getAllReceipts() : List<Receipt>

    @Query("SELECT * FROM Receipts WHERE receiptDate = :date")
    suspend fun getReceiptsOnDate(date: String) : List<Receipt>

    @Query("SELECT * FROM Receipts WHERE ledgerId = :ledgerId")
    fun getAllReceiptsInLedger(ledgerId : Int) : List<Receipt>

    @Query("SELECT * FROM Receipts WHERE receiptId = (SELECT max(receiptId) FROM Receipts)")
    suspend fun getLastReceipt() : Receipt?

    @Query("SELECT * FROM Receipts WHERE receiptId = :receiptId")
    suspend fun getReceiptById(receiptId: Int): Receipt?

    @Query("SELECT SUM(receiptAmount) FROM Receipts")
    suspend fun getTotalReceiptAmount(): Double?

    @Query("""
        SELECT SUM(receiptAmount) FROM Receipts
        WHERE receiptDate LIKE :monthPattern
    """)
    suspend fun getMonthlyReceiptAmount(monthPattern: String): Double?

    @Query("SELECT SUM(receiptAmount) FROM receipts WHERE receiptDate = :date")
    suspend fun getReceiptTotalByDate(date: String): Double?
}