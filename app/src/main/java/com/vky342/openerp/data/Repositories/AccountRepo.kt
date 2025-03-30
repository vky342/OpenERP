package com.vky342.openerp.data.Repositories

import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Modules.OpenERPDataBase
import kotlinx.coroutines.flow.Flow

class AccountRepo (private val openERPDataBase: OpenERPDataBase) {

    private val accountdao = openERPDataBase.getAccountDao()
    private val ledgerdao = openERPDataBase.getLedgerDao()

    suspend fun insert_Account(name : String, address : String, contact : String, type: String){
        accountdao.insert(Account(0,name, address, contact, type))
        ledgerdao.insert(ledger = Ledger(0,0,name))
    }

    private suspend fun get_Ledger_by_accountName(name : String) : Ledger{
        return ledgerdao.getLedgerByAccountName(name)
    }

    suspend fun update_Account(oldAccount: Account, newAccount: Account){

        accountdao.delete(oldAccount)
        ledgerdao.update(get_Ledger_by_accountName(oldAccount.name).copy(accountName = newAccount.name))
        accountdao.insert(newAccount)

    }

    fun get_every_Account() : Flow<List<Account>> {
        return accountdao.getAllAccounts()
    }

    suspend fun delete_Account(account : Account){
        accountdao.delete(account)
        ledgerdao.delete(get_Ledger_by_accountName(account.name))
    }

}