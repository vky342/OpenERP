package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Ledger


@Dao
interface LedgerDao{

    @Insert
    suspend fun insert(ledger: Ledger)

    @Update
    suspend fun update(ledger: Ledger)

    @Delete
    suspend fun delete(ledger: Ledger)

    @Query("SELECT * FROM Ledgers")
    fun getAllLedger() : List<Ledger>

    @Query("SELECT * FROM Ledgers WHERE accountName = :name")
    suspend fun getLedgerByAccountName(name : String) : Ledger

    @Query("SELECT * FROM Ledgers WHERE ledgerId = :id")
    suspend fun getLedger(id : Int) : Ledger?

    @Query("SELECT SUM(ledgerNetBalance) FROM Ledgers WHERE ledgerNetBalance < 0")
    suspend fun getTotalNegativeLedgerBalance(): Double?

    @Query("SELECT * FROM Ledgers WHERE ledgerNetBalance < 0")
    suspend fun getLedgersWithNegativeBalance(): List<Ledger>

    @Query("SELECT SUM(ledgerNetBalance) FROM Ledgers WHERE ledgerNetBalance > 0")
    suspend fun getTotalPositiveLedgerBalance(): Double?

    @Query("SELECT * FROM Ledgers WHERE ledgerNetBalance > 0")
    suspend fun getLedgersWithPositiveBalance(): List<Ledger>

}