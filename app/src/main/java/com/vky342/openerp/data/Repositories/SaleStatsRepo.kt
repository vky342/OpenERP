package com.vky342.openerp.data.Repositories

import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.Modules.OpenERPDataBase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface SalesRepository {
    suspend fun getSalesTotalByDate(date: String? = null): Double
    suspend fun getMonthlySalesTotal(month: String? = null): Double
    suspend fun getSalesByProduct(itemName: String): List<SaleEntry>
}

class SaleStatsRepo(openERPDataBase: OpenERPDataBase) : SalesRepository {

    private val saleDao = openERPDataBase.getSaleDao()
    private val saleEntryDao = openERPDataBase.getSaleEntryDao()

    override suspend fun getSalesByProduct(itemName: String): List<SaleEntry> {
        return saleEntryDao.getAllSaleEntriesByItemName(itemName)
    }

    override suspend fun getMonthlySalesTotal(month: String?): Double {
        val pattern = if (month != null) {
            "%/$month"
        } else {
            val sdf = SimpleDateFormat("MM/yy", Locale.getDefault())
            val currentMonth = sdf.format(Date())
            "%/$currentMonth"
        }
        return saleDao.getMonthlySalesTotal(pattern) ?: 0.0
    }

    override suspend fun getSalesTotalByDate(date: String?): Double {
        val targetDate = date ?: SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(Date())
        return saleDao.getSalesTotalByDate(targetDate) ?: 0.0
    }
}