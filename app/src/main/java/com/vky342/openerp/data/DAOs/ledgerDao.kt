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

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(ledger: Ledger)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(ledger: Ledger)

    @Delete
    suspend fun delete(ledger: Ledger)

    // Function for Screening

    @Query("SELECT * FROM Ledgers")
    fun getAllLedger() : Flow<List<Ledger>>

    @Query("SELECT * FROM Ledgers WHERE accountNameFk = :name")
    fun getLedgerByAccountName(name : String) : Flow<Ledger>

}