package com.vky342.openerp.data.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vky342.openerp.data.Entities.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface accountsDao {

    @Insert
    suspend fun insert(account: Account)

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)

    // Functions for screening

    @Query("SELECT * FROM Accounts")
    fun getAllAccounts() : List<Account>

    @Query("SELECT * FROM Accounts WHERE name = :name")
    fun getAccountByName(name : String) : Account

}