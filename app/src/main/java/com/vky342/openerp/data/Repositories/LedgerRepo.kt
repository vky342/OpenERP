package com.vky342.openerp.data.Repositories

import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Modules.OpenERPDataBase
import com.vky342.openerp.ui.screens.Ledgers.AccountLedgerItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LedgerRepo (private val openERPDataBase: OpenERPDataBase) {

    val purchaseDao = openERPDataBase.getPurchaseDao()
    val saleDao = openERPDataBase.getSaleDao()
    val paymentsDao = openERPDataBase.getPaymentDao()
    val receiptDao = openERPDataBase.getReceiptDao()
    val ledgerDao = openERPDataBase.getLedgerDao()

    // Function to convert date string to LocalDate
    private fun parseDate(dateStr: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.parse(dateStr, formatter)
    }

    suspend fun getLedgerItemList(account: Account) : List<AccountLedgerItem> {

        val ledgerItems = mutableListOf<AccountLedgerItem>()
        val ledger = ledgerDao.getLedgerByAccountName(account.name)
        val purchases = purchaseDao.getPurchaseByLedgerId(ledger.ledgerId)
        val sales = saleDao.getSalesByLedgerId(ledger.ledgerId)
        val payments = paymentsDao.getAllPaymentsInLedger(ledger.ledgerId)
        val receipts = receiptDao.getAllReceiptsInLedger(ledger.ledgerId)

        purchases.mapTo(ledgerItems) {
            AccountLedgerItem(it.purchaseDate, "Purchase", it.purchaseAmount, CashOrCredit = it.purchaseType)
        }
        sales.mapTo(ledgerItems) {
            AccountLedgerItem(it.saleDate, "Sales", it.saleAmount, CashOrCredit = it.saleType)
        }
        payments.mapTo(ledgerItems) {
            AccountLedgerItem(it.paymentDate, "Payment", it.paymentAmount)
        }
        receipts.mapTo(ledgerItems) {
            AccountLedgerItem(it.receiptDate, "Receipt", it.receiptAmount)
        }
        val sortedLedgerItems = ledgerItems.sortedBy { parseDate(it.BillDate) }
        return sortedLedgerItems
    }

}