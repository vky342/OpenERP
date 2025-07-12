package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Sale
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao{
    @Insert
    suspend fun insert(sale: Sale)

    @Update
    suspend fun update(sale: Sale)

    @Delete
    suspend fun delete(sale: Sale)

    @Query("SELECT * FROM Sales")
    fun getAllSales() : Flow<List<Sale>>

    @Query("SELECT * FROM Sales WHERE ledgerId = :ledgerId")
    fun getSalesByLedgerId(ledgerId : Int) : List<Sale>

    @Query("SELECT * FROM Sales WHERE saleId = (SELECT max(saleId) FROM Sales)")
    suspend fun getLastSale() : Sale

    @Query("""
    SELECT SUM(saleAmount) FROM Sales 
    WHERE saleDate LIKE :monthPattern
""")
    suspend fun getMonthlySalesTotal(monthPattern: String): Double?

    @Query("SELECT SUM(saleAmount) FROM Sales WHERE saleDate = :date")
    suspend fun getSalesTotalByDate(date: String): Double?

    @Query("""
    SELECT SUM(saleAmount) FROM Sales 
    WHERE saleType = 'Cash' AND saleDate LIKE :monthPattern
""")
    suspend fun getMonthlyCashSales(monthPattern: String): Double?

    @Query("SELECT SUM(saleAmount) FROM Sales WHERE saleType = 'Cash'")
    suspend fun getTotalCashSales(): Double?

    @Query("SELECT * FROM Sales WHERE saleDate = :date")
    suspend fun getSalesByDate(date: String): List<Sale>
}