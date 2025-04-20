package com.vky342.openerp.data.Repositories

import android.util.Log
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Receipt
import com.vky342.openerp.data.Modules.OpenERPDataBase
import kotlinx.coroutines.flow.Flow

class ReceiptRepo (private val openERPDataBase: OpenERPDataBase ){

    private val accountDao = openERPDataBase.getAccountDao()
    private val ledgerDao = openERPDataBase.getLedgerDao()
    private val receiptDao = openERPDataBase.getReceiptDao()

    fun get_every_Account() : Flow<List<Account>> {
        return accountDao.getAllAccounts()
    }

    suspend fun AddReceipt (receipt: Receipt,amount: Double, name: String){
        val ledger = updateLedger(amount,name)
        receiptDao.insert(receipt.copy(ledgerId = ledger.ledgerId))
    }
    private suspend fun updateLedger(amount: Double,name : String) : Ledger{
        val ledger = ledgerDao.getLedgerByAccountName(name)

        ledgerDao.update(ledger.copy(ledgerNetBalance = ledger.ledgerNetBalance + amount))
        Log.d("STATUS", "Update ledger called line 28")
        return ledger
    }

    suspend fun getBalance(name: String) : Double{
        val ledger = ledgerDao.getLedgerByAccountName(name)
        return ledger.ledgerNetBalance
    }
}

