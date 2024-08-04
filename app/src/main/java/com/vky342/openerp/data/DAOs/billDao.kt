package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Bill
import kotlinx.coroutines.flow.Flow

@Dao
interface billsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(bill: Bill)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(bill: Bill)

    @Delete
    suspend fun delete(bill: Bill)

    // Function fro screening

    @Query("SELECT * FROM Bills")
    fun getAllBills() : Flow<List<Bill>>

    @Query("SELECT * FROM Bills WHERE billDate = :date")
    fun getBillsByDate(date : Int) : Flow<List<Bill>>

    @Query("SELECT * FROM Bills WHERE ledgerType = :type")
    fun getBillsByType(type : Int) : Flow<List<Bill>>

    @Query("SELECT * FROM Bills WHERE accountNameFk = :name")
    fun getBillsByAccountName(name : String) : Flow<List<Bill>>

//    @Query("SELECT billId FROM Bills")
//    fun getListOfBillId() : Flow<List<Int>>
}