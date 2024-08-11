package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.PurchaseEntry


@Dao
interface PurchaseEntryDao {
    @Insert
    suspend fun insert(purchaseEntry: PurchaseEntry)

    @Update
    suspend fun update(purchaseEntry: PurchaseEntry)

    @Delete
    suspend fun delete(purchaseEntry: PurchaseEntry)

    @Query("SELECT * FROM PurchaseEntry WHERE purchaseId = :purchaseId")
    fun getAllPurchaseEntriesByPurchaseId(purchaseId : String) :  List<PurchaseEntry>

    @Query("SELECT * FROM PurchaseEntry WHERE itemName = :itemName")
    fun getAllPurchaseEntriesByItemName(itemName : String) : List<PurchaseEntry>
}