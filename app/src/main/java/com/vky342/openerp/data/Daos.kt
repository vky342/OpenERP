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

    @Query("SELECT * FROM bills WHERE ledgerType = :type")
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

@Dao
interface paymentsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(payment: Payment)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(payment: Payment)

    @Delete
    suspend fun delete(payment: Payment)

    // Function for screening

    @Query("SELECT * FROM Payments")
    fun getAllPayments() : Flow<List<Payment>>

    @Query("SELECT * FROM Payments WHERE paymentDate = :date")
    fun getAllPaymentOnDate(date: Int) : Flow<List<Payment>>

    @Query("SELECT * FROM Payments WHERE accountLedgerId = :ledgerId")
    fun getAllPaymentsInLedger(ledgerId : Int) : Flow<List<Payment>>
}

@Dao
interface receiptDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(receipt: Receipt)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(receipt: Receipt)

    @Delete
    suspend fun delete(receipt: Receipt)

    // Function for screening

    @Query("SELECT * FROM Receipts")
    fun getAllPayments() : Flow<List<Receipt>>

    @Query("SELECT * FROM Receipts WHERE receiptDate = :date")
    fun getAllPaymentOnDate(date: Int) : Flow<List<Receipt>>

    @Query("SELECT * FROM Receipts WHERE accountLedgerId = :ledgerId")
    fun getAllPaymentsInLedger(ledgerId : Int) : Flow<List<Receipt>>
}

@Dao
interface ledgerDao{

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(ledger: Ledger)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(ledger: Ledger)

    @Delete
    suspend fun delete(ledger: Ledger)

    // Function for Screening

    @Query("SELECT * FROM ledgers")
    fun getAllLedger() : Flow<List<Ledger>>

    @Query("SELECT * FROM ledgers WHERE accountNameFk = :name")
    fun getLedgerByAccountName(name : String) : Flow<Ledger>

}