package com.vky342.openerp.data.Repositories

import android.util.Log
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.Modules.OpenERPDataBase
import javax.inject.Inject

class SaleRepo @Inject constructor(private val openERPDataBase: OpenERPDataBase) {
    private val ledgerDao = openERPDataBase.getLedgerDao()
    private val saleDao = openERPDataBase.getSaleDao()
    private val saleEntryDao = openERPDataBase.getSaleEntryDao()
    private val itemInventoryDao = openERPDataBase.getItemInventoryDao()

    // public function to add sale
    suspend fun AddSale (account_name : String, sale: Sale, list_of_saleEntry : List<SaleEntry>) {
        add_Sale(account_name, sale)
        add_sale_entry(list_of_saleEntry)
    }

    suspend fun getLatestSaleID() : Int{
        try {
            val pid = saleDao.getLastSale().saleId
            return pid
        } catch (e: Exception){
            Log.d("STATUS","No previous sale found; returning id as 0")
            return 0
        }
    }

    // function to be used within AddSale
    private suspend fun add_sale_entry (list_of_saleEntry: List<SaleEntry>){
        val latestSale = saleDao.getLastSale()
        for (saleEntry in list_of_saleEntry) {
            item_Stock_Update(saleEntry.itemName, saleEntry.entryQuantity)
            saleEntryDao.insert(saleEntry.copy(saleId = latestSale.saleId))
        }
    }

    private suspend fun add_Sale (name : String, sale: Sale) {
        val ledger = ledgerDao.getLedgerByAccountName(name)
        if (sale.saleType == "Credit"){
            ledgerDao.update(ledger.copy(ledgerNetBalance = ledger.ledgerNetBalance - sale.saleAmount))
        }
        saleDao.insert(sale.copy(ledgerId = ledger.ledgerId))
    }

    private suspend fun item_Stock_Update (itemName : String, itemQuantity : Int) {
        var item = itemInventoryDao.getItemByName(itemName)
        itemInventoryDao.update(item.copy(itemQuantity = item.itemQuantity - itemQuantity))

    }

}