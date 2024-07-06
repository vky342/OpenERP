package com.vky342.openerp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * This file contains All the Dao for entities
 */



@Dao
interface accountsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(account: Account)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)

    // Functions for screening

    @Query("SELECT * FROM Accounts")
    fun getAllAccounts() : Flow<List<Account>>

    @Query("SELECT * FROM Accounts WHERE name = :name")
    fun getAccountByName(name : String) : Flow<Account>

}

@Dao
interface billsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(bill: Bill)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(bill: Bill)

    @Delete
    suspend fun delete(bill: Bill)

    // Function fro screening

    @Query("SELECT * FROM bills")
    fun getAllBills() : Flow<List<Bill>>

    @Query("SELECT * FROM bills WHERE billDate = :date")
    fun getBillsByDate(date : Int) : Flow<List<Bill>>

    @Query("SELECT * FROM bills WHERE billType = :type")
    fun getBillsByType(type : Int) : Flow<List<Bill>>

    @Query("SELECT * FROM Bills WHERE accountNameFk = :name")
    fun getBillsByAccountName(name : String) : Flow<List<Bill>>
}

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