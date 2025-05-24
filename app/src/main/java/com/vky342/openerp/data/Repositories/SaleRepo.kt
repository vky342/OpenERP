package com.vky342.openerp.data.Repositories

import android.util.Log
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.Modules.OpenERPDataBase
import kotlinx.coroutines.flow.Flow
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

    suspend fun updateSale(oldName: String, newName: String, oldSale: Sale, newSale: Sale, old_list_of_saleEntry: List<SaleEntry>, new_list_of_saleEntry: List<SaleEntry>){
        updateLedgerNetBalance(newName = newName, oldName = oldName, oldSale = oldSale,newSale = newSale)
        privateUpdateSale(newName,newSale)
        updateItemStock(old_list_of_saleEntry = old_list_of_saleEntry, new_list_of_saleEntry = new_list_of_saleEntry)
        updateSaleEntry(saleID = oldSale.saleId, old_list_of_saleEntry,new_list_of_saleEntry)
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

    suspend fun getListOfSaleEntriesByID(saleID : Int) : List<SaleEntry> {
        return saleEntryDao.getAllSaleEntriesBySaleId(saleID)
    }

    fun get_every_Sale() : Flow<List<Sale>> {
        return saleDao.getAllSales()
    }

    //Private functions
    private suspend fun add_sale_entry (list_of_saleEntry: List<SaleEntry>){
        val latestSale = saleDao.getLastSale()
        for (saleEntry in list_of_saleEntry) {
            item_Stock_Update(saleEntry.itemName, saleEntry.entryQuantity)
            saleEntryDao.insert(saleEntry.copy(saleId = latestSale.saleId))
        }
    }

    private suspend fun updateSaleEntry(saleID: Int, old_list_of_saleEntry: List<SaleEntry>, new_list_of_saleEntry: List<SaleEntry>){
        for (entry in old_list_of_saleEntry){
            saleEntryDao.delete(entry.copy(saleId = saleID))
        }
        for (entry in new_list_of_saleEntry){
            saleEntryDao.insert(entry.copy(saleId = saleID))
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

    // 1st process for updating
    private suspend fun updateLedgerNetBalance(newName : String, oldName: String, oldSale : Sale,newSale: Sale){

        //reversing old ledger
        val oldLedger = ledgerDao.getLedgerByAccountName(oldName)
        if (oldSale.saleType == "Credit"){
            oldLedger.ledgerNetBalance = oldLedger.ledgerNetBalance + oldSale.saleAmount
            ledgerDao.update(oldLedger)
        }

        // updating new ledger
        val newLedger = ledgerDao.getLedgerByAccountName(newName)
        if (newSale.saleType == "Credit"){
            newLedger.ledgerNetBalance = newLedger.ledgerNetBalance - newSale.saleAmount
            ledgerDao.update(newLedger)
        }
    }

    //second process for updating
    private suspend fun privateUpdateSale(newName : String,newSale: Sale){
        val newLedger = ledgerDao.getLedgerByAccountName(newName)
        saleDao.update(newSale.copy(ledgerId = newLedger.ledgerId))
    }

    // 3rd and final process for updating
    private suspend fun updateItemStock(old_list_of_saleEntry: List<SaleEntry>, new_list_of_saleEntry: List<SaleEntry>){

        //reversing the item stock for old_list_of_saleEntry
        for (saleEntry in old_list_of_saleEntry) {
            var item = itemInventoryDao.getItemByName(saleEntry.itemName)
            itemInventoryDao.update(item.copy(itemQuantity = item.itemQuantity + saleEntry.entryQuantity))
        }

        // inserting new Entry and updating item stock for new_list_saleEntry
        for (saleEntry in new_list_of_saleEntry) {
            var item = itemInventoryDao.getItemByName(saleEntry.itemName)
            itemInventoryDao.update(item.copy(itemQuantity = item.itemQuantity - saleEntry.entryQuantity))
        }

    }
}