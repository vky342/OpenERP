package com.vky342.openerp.data.Repositories

import android.util.Log
import com.vky342.openerp.data.DAOs.accountsDao
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Payment
import com.vky342.openerp.data.Modules.OpenERPDataBase
import kotlinx.coroutines.flow.Flow

class PaymentRepo (private val openERPDataBase: OpenERPDataBase ){

    private val account = openERPDataBase.getAccountDao()
    private val ledgerDao = openERPDataBase.getLedgerDao()
    private val paymentsDao = openERPDataBase.getPaymentDao()

    fun get_every_Account() : Flow<List<Account>> {
        return account.getAllAccounts()
    }

    suspend fun AddPayment (payment: Payment,amount: Double, name: String){
        val ledger = updateLedger(amount,name)
        paymentsDao.insert(payment.copy(ledgerId = ledger.ledgerId))
    }

    suspend fun getLatestPaymentID(): Int{
        try {
            val pid = paymentsDao.getLastSale().paymentId
            return pid
        } catch (e: Exception){
            Log.d("STATUS","No previous sale found; returning id as 0")
            return 0
        }
    }

    suspend fun getPaymentByID(ID: Int): Payment {
        return paymentsDao.getPaymentById(ID) ?: Payment(0, "", 0.0, 0)
    }

    suspend fun getLedgerByID(ID: Int) : Ledger {
        return ledgerDao.getLedger(id = ID) ?: Ledger(0,0.0,"")
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

    suspend fun deletePayment(payment: Payment, ledger: Ledger){
        ledgerDao.update(ledger.copy(ledgerNetBalance = ledger.ledgerNetBalance + payment.paymentAmount))
        paymentsDao.delete(payment)
    }
}

