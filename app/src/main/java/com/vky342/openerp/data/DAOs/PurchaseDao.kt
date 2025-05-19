package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Purcahase
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {
    @Insert
    suspend fun insert(purchase : Purcahase)

    @Update
    suspend fun update(purchase : Purcahase)

    @Delete
    suspend fun delete(purchase : Purcahase)

    @Query("SELECT * FROM Purchases")
    fun getAllPurchase() : Flow<List<Purcahase>>

    @Query("SELECT * FROM Purchases WHERE ledgerId = :ledgerId")
    fun getPurchaseByLedgerId(ledgerId : Int) : List<Purcahase>

    @Query("SELECT * FROM Purchases WHERE purchaseId = (SELECT max(purchaseId) FROM Purchases)")
    suspend fun getLastPurchase() : Purcahase

}