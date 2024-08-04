package com.vky342.openerp.data.Repositories

import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Modules.OpenERPDataBase

class AccountRepo (private val openERPDataBase: OpenERPDataBase) {

    private val accountdao = openERPDataBase.getAccountDao()
    private val ledgerdao = openERPDataBase.getLedgerDao()


    // Add Account ViewModel

    fun get_all_account() = accountdao.getAllAccounts()

    suspend fun saveAccount(account: Account) = accountdao.insert(account)

    suspend fun createLedger(ledger: Ledger) = ledgerdao.insert(ledger)

}