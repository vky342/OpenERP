package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Ledger
import kotlinx.coroutines.flow.Flow


@Dao
interface ledgerDao{

    @Insert
    suspend fun insert(ledger: Ledger)

    @Update
    suspend fun update(ledger: Ledger)

    @Delete
    suspend fun delete(ledger: Ledger)

    // Function for Screening

    @Query("SELECT * FROM Ledgers")
    fun getAllLedger() : List<Ledger>

    @Query("SELECT * FROM Ledgers WHERE accountName = :name")
    suspend fun getLedgerByAccountName(name : String) : Ledger

    @Query("SELECT * FROM Ledgers WHERE ledgerId = :id")
    suspend fun getLedger(id : Int) : Ledger

}