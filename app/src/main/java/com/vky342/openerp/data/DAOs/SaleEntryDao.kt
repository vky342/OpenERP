package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.SaleEntry
import kotlinx.coroutines.flow.Flow


@Dao
interface SaleEntryDao{
    @Insert
    suspend fun insert(saleEntry: SaleEntry)

    @Update
    suspend fun update(saleEntry: SaleEntry)

    @Delete
    suspend fun delete(saleEntry: SaleEntry)

    @Query("SELECT * FROM SaleEntries WHERE saleId = :saleId")
    suspend fun getAllSaleEntriesBySaleId(saleId : Int) : List<SaleEntry>

    @Query("SELECT * FROM SaleEntries WHERE itemName = :itemName")
    fun getAllSaleEntriesByItemName(itemName : String) : List<SaleEntry>
}