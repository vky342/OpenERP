package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.BillEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface billentriesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(billEntries: BillEntry)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(billEntries: BillEntry)

    @Delete
    suspend fun delete(billEntries: BillEntry)

    // Function for screening

    @Query("SELECT * FROM BillEntries WHERE billIdFk = :billId")
    fun getBillEntriesByBillId(billId : Int) : Flow<List<BillEntry>>

    @Query("SELECT * FROM BillEntries WHERE itemNameFk = :name")
    fun getBillEntriesByItemNameFk(name : String) : Flow<List<BillEntry>>

}