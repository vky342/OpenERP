package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface itemInventoryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item : Item)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    // Functions for screening

    @Query("SELECT * FROM iteminventory")
    fun getAllItemInInventory() : Flow<List<Item>>

    @Query("SELECT * FROM iteminventory WHERE itemName = :name")
    fun getItemByName(name : String) : Flow<Item>
}