package com.vky342.openerp.data.Repositories

import com.vky342.openerp.data.DAOs.LedgerDao
import com.vky342.openerp.data.DAOs.PurchaseDao
import com.vky342.openerp.data.DAOs.SaleDao
import com.vky342.openerp.data.DAOs.PaymentsDao
import com.vky342.openerp.data.DAOs.ReceiptDao
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Modules.OpenERPDataBase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

interface CashStatsRepository {

    suspend fun getTotalCashInflow(): Double

    suspend fun getTotalCashOutflow(): Double

    suspend fun getTotalPendingPayable(): Double

    suspend fun getTotalPendingReceivable(): Double

    suspend fun getMonthlyCashInflow(month: String): Double

    suspend fun getMonthlyCashOutflow(month: String): Double

    suspend fun getPendingPayableLedgers(): List<Ledger>
}

class CashStatsRepo @Inject constructor(openERPDataBase: OpenERPDataBase) : CashStatsRepository{

    private val paymentsDao: PaymentsDao = openERPDataBase.getPaymentDao()
    private val receiptDao: ReceiptDao = openERPDataBase.getReceiptDao()
    private val saleDao: SaleDao = openERPDataBase.getSaleDao()
    private val ledgerDao: LedgerDao = openERPDataBase.getLedgerDao()
    private val purchaseDao: PurchaseDao = openERPDataBase.getPurchaseDao()

    override suspend fun getTotalCashInflow(): Double {
        val saleSum = saleDao.getTotalCashSales() ?: 0.0
        val receiptSum = receiptDao.getTotalReceiptAmount() ?: 0.0
        return saleSum + receiptSum
    }

    override suspend fun getTotalCashOutflow(): Double {
        val purchaseSum = purchaseDao.getTotalCashPurchases() ?: 0.0
        val paymentSum = paymentsDao.getTotalPaymentAmount() ?: 0.0
        return paymentSum + purchaseSum
    }

    override suspend fun getTotalPendingReceivable(): Double {
        var totalReceivableBalance = ledgerDao.getTotalNegativeLedgerBalance() ?: 0.0
        if (totalReceivableBalance < 0.0){
            totalReceivableBalance = totalReceivableBalance / -1
        }
        return totalReceivableBalance
    }

    override suspend fun getMonthlyCashInflow(month: String): Double {
        val monthToUse = if (month.isBlank()) {
            SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(Date())
        } else {
            // Validate format strictly as MM/yyyy
            val regex = Regex("""^(0[1-9]|1[0-2])/(\d{4})$""")
            if (!regex.matches(month)) {
                throw IllegalArgumentException("Invalid month format. Expected MM/yyyy.")
            }

            val invalidChars = listOf(';', '\'', '"', '%', '=', '-', '\\')
            if (invalidChars.any { it in month }) {
                throw IllegalArgumentException("Month contains invalid or potentially harmful characters.")
            }
            month
        }
        val monthPattern = "%/$monthToUse"
        val saleSum = saleDao.getMonthlySalesTotal(monthPattern) ?: 0.0
        val receiptSum = receiptDao.getMonthlyReceiptAmount(monthPattern) ?: 0.0
        return saleSum + receiptSum
    }

    override suspend fun getMonthlyCashOutflow(month: String): Double {
        val monthToUse = if (month.isBlank()) {
            SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(Date())
        } else {
            val regex = Regex("""^(0[1-9]|1[0-2])/(\d{4})$""")
            if (!regex.matches(month)) {
                throw IllegalArgumentException("Invalid month format. Expected MM/yyyy.")
            }

            val invalidChars = listOf(';', '\'', '"', '%', '=', '-', '\\')
            if (invalidChars.any { it in month }) {
                throw IllegalArgumentException("Month contains invalid or potentially harmful characters.")
            }
            month
        }
        val monthPattern = "%/$monthToUse"
        val purchaseSum = purchaseDao.getMonthlyPurchaseAmount(monthPattern) ?: 0.0
        val paymentSum = paymentsDao.getMonthlyPaymentAmount(monthPattern) ?: 0.0
        return purchaseSum + paymentSum
    }

    override suspend fun getPendingPayableLedgers(): List<Ledger> {
        return ledgerDao.getLedgersWithPositiveBalance()
    }

    override suspend fun getTotalPendingPayable(): Double {
        return ledgerDao.getTotalPositiveLedgerBalance() ?: 0.0
    }

}