package com.vky342.openerp.data.Repositories

import com.vky342.openerp.data.Modules.OpenERPDataBase
import javax.inject.Inject

data class dayBookItem(
    val accountName : String,
    val BillType    : String,
    val BillAmount  : Double,
    val CashOrCredit : String = "",
)

interface DayBookRepository{
    suspend fun getDayLedger(date: String) : List<dayBookItem>
}

class DayBookRepo @Inject constructor(openERPDataBase: OpenERPDataBase) : DayBookRepository{

    private val saleDao = openERPDataBase.getSaleDao()
    private val purchaseDao = openERPDataBase.getPurchaseDao()
    private val paymentsDao = openERPDataBase.getPaymentDao()
    private val receiptDao = openERPDataBase.getReceiptDao()
    private val ledgerDao = openERPDataBase.getLedgerDao()

    override suspend fun getDayLedger(date : String): List<dayBookItem> {
        return getDaySale(date) + getDayPurchase(date) + getDayReceipt(date) + getDayPayment(date)
    }

    private suspend fun getDaySale(date: String) : List<dayBookItem> {
        val listOfItem = saleDao.getSalesByDate(date)
        return listOfItem.map { dayBookItem(accountName = ledgerDao.getLedger(it.ledgerId)!!.accountName, BillType = "Sale", BillAmount = it.saleAmount, CashOrCredit = it.saleType) }
    }

    private suspend fun getDayPurchase(date : String) : List<dayBookItem> {
        val listOfItem = purchaseDao.getPurchaseByDate(date)
        return listOfItem.map { dayBookItem(accountName = ledgerDao.getLedger(it.ledgerId)!!.accountName, BillType = "Purchase", BillAmount = it.purchaseAmount, CashOrCredit = it.purchaseType) }
    }

    private suspend fun getDayPayment(date : String) : List<dayBookItem> {
        val listOfItem = paymentsDao.getPaymentOnDate(date)
        return listOfItem.map { dayBookItem(accountName = ledgerDao.getLedger(it.ledgerId)!!.accountName, BillType = "Payment", BillAmount = it.paymentAmount, CashOrCredit = "") }
    }

    private suspend fun getDayReceipt(date : String) : List<dayBookItem>{
        val listOfItem = receiptDao.getReceiptsOnDate(date)
        return listOfItem.map { dayBookItem(accountName = ledgerDao.getLedger(it.ledgerId)!!.accountName, BillType = "Receipt", BillAmount = it.receiptAmount, CashOrCredit = "") }
    }
}