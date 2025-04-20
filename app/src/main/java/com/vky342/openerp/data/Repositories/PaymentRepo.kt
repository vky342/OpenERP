package com.vky342.openerp.data.Repositories

import android.util.Log
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Payment
import com.vky342.openerp.data.Modules.OpenERPDataBase
import kotlinx.coroutines.flow.Flow

class PaymentRepo (private val openERPDataBase: OpenERPDataBase ){

    private val accountdao = openERPDataBase.getAccountDao()
    private val ledgerDao = openERPDataBase.getLedgerDao()
    private val paymentsDao = openERPDataBase.getPaymentDao()

    fun get_every_Account() : Flow<List<Account>> {
        return accountdao.getAllAccounts()
    }

    suspend fun AddPayment (payment: Payment,amount: Double, name: String){
        val ledger = updateLedger(amount,name)
        paymentsDao.insert(payment.copy(ledgerId = ledger.ledgerId))
    }
    private suspend fun updateLedger(amount: Double,name : String) : Ledger{
        val ledger = ledgerDao.getLedgerByAccountName(name)

        ledgerDao.update(ledger.copy(ledgerNetBalance = ledger.ledgerNetBalance - amount))
        Log.d("STATUS", "Update ledger called line 25")
        return ledger
    }

    suspend fun getBalance(name: String) : Double{
        val ledger = ledgerDao.getLedgerByAccountName(name)
        return ledger.ledgerNetBalance
    }
}

