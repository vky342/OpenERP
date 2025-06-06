package com.vky342.openerp.data.Repositories

import android.util.Log
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
        val trimmedName = itemName.trim()

        // Edge case checks
        if (trimmedName.isEmpty()) {
            // Log or handle the error case as needed
            return emptyList()
        }

        if (trimmedName.length < 2) {
            // Too short to be a valid product name
            return emptyList()
        }

        val invalidCharsRegex = Regex("[^a-zA-Z0-9\\s_-]")
        if (invalidCharsRegex.containsMatchIn(trimmedName)) {
            return emptyList()
        }

        return saleEntryDao.getAllSaleEntriesByItemName(trimmedName)
    }

    override suspend fun getMonthlySalesTotal(month: String?): Double {
        val pattern = if (!month.isNullOrBlank()) {
            val trimmed = month.trim()

            // Validate format: MM/yyyy
            val regex = Regex("""\d{2}/\d{4}""")
            if (!regex.matches(trimmed)) return 0.0

            // Check valid month number (01–12)
            val mm = trimmed.substringBefore("/").toIntOrNull()
            if (mm == null || mm !in 1..12) return 0.0

            // Build LIKE pattern: "__/MM/yyyy"
            "__/$trimmed"
        } else {
            val current = SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(Date())
            "__/$current"
        }

        return saleDao.getMonthlySalesTotal(pattern) ?: 0.0
    }

    override suspend fun getSalesTotalByDate(date: String?): Double {
        val targetDate = if (date.isNullOrBlank()) {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        } else {
            val trimmedDate = date.trim()
            // Check for correct format using regex (dd/MM/yyyy)
            val datePattern = Regex("""\d{2}/\d{2}/\d{4}""")
            if (!datePattern.matches(trimmedDate)) {
                return 0.0
            }
            // Additional parsing check to verify it's a real date
            try {
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
                    isLenient = false
                }.parse(trimmedDate)
            } catch (e: Exception) {
                return 0.0 // or log and return 0.0
            }

            trimmedDate
        }
        return saleDao.getSalesTotalByDate(targetDate) ?: 0.0
    }

}