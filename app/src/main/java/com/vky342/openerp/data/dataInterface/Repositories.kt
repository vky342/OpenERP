package com.vky342.openerp.data.dataInterface

import kotlinx.coroutines.flow.Flow

/**
 *This file contains Repositories for every ViewModel. Repositories are designed in a way which suits UI and Screening.
 * Repositories will have business logic as well.
 */


interface accountScreeningRepository {

    suspend fun insert(account: Account)

    suspend fun update(account: Account)

    suspend fun delete(account: Account)

    suspend fun getAllAccounts() : Flow<List<Account>>

    suspend fun getAccountByName(name : String) : Flow<Account>

}