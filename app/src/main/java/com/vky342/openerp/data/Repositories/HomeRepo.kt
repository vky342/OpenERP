package com.vky342.openerp.data.Repositories

import com.vky342.openerp.data.Modules.OpenERPDataBase
import com.vky342.openerp.utility.getTodayDate


interface homeRepository{
    suspend fun getTodaySale() : Double
    suspend fun getTodayReceipt() : Double
}

class HomeRepo ( private val openERPDataBase: OpenERPDataBase) : homeRepository {
    private val saleDao = openERPDataBase.getSaleDao()
    private val receiptDao = openERPDataBase.getReceiptDao()

    override suspend fun getTodaySale(): Double {
        val result : Double? = saleDao.getSalesTotalByDate(date = getTodayDate())
        if (result == null) {
            return 0.0
        }else {
            return result
        }

    }

    override suspend fun getTodayReceipt(): Double {
        val result : Double? = receiptDao.getReceiptTotalByDate(date = getTodayDate())
        if (result == null){
            return 0.0
        }else {
            return result
        }
    }

}