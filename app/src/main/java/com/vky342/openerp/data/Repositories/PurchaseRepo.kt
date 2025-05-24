package com.vky342.openerp.data.Repositories

import android.util.Log
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Entities.Purcahase
import com.vky342.openerp.data.Entities.PurchaseEntry
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.Modules.OpenERPDataBase
import kotlinx.coroutines.flow.Flow

class PurchaseRepo (private val openERPDataBase: OpenERPDataBase) {

    private val ledgerDao = openERPDataBase.getLedgerDao()
    private val purchaseDao = openERPDataBase.getPurchaseDao()
    private val purchaseEntryDao = openERPDataBase.getPurchaseEntryDao()
    private val itemInventoryDao = openERPDataBase.getItemInventoryDao()

    // public function to add purchase
    suspend fun AddPurchase (account_name : String,purchase : Purcahase, list_of_purchaseEntry : List<PurchaseEntry>) {
        add_purchase(account_name, purchase)
        add_purchase_entry(list_of_purchaseEntry)
    }

    suspend fun updatePurchase(oldName: String, newName: String, oldPurhcase: Purcahase, newPurchase: Purcahase, old_list_of_purhcaseEntry: List<PurchaseEntry>, new_list_of_purchaseEntry: List<PurchaseEntry>){
        updateLedgerNetBalance(newName = newName, oldName = oldName, oldPurchase = oldPurhcase, newPurchase = newPurchase)
        privateUpdateSale(newName,newPurchase)
        updateItemStock(old_list_of_purchaseEntry = old_list_of_purhcaseEntry, new_list_of_purchaseEntry = new_list_of_purchaseEntry)
        updatePurchaseEntry(purchaseID = oldPurhcase.purchaseId, old_list_of_purhcaseEntry, new_list_of_purchaseEntry)
    }

    suspend fun getLatestPurchaseID() : Int{
        try {
            val pid = purchaseDao.getLastPurchase().purchaseId
            return pid
        } catch (e: Exception){
            Log.d("STATUS","No previous purchase found; returning id as 0")
            return 0
        }
    }

    suspend fun getListOfPurchaseEntriesByID(purchaseID: Int) : List<PurchaseEntry> {
        return purchaseEntryDao.getAllPurchaseEntriesByPurchaseId(purchaseId = purchaseID)
    }

    fun get_every_Purchase() : Flow<List<Purcahase>> {
        return purchaseDao.getAllPurchase()
    }

    // Private functions
    private suspend fun add_purchase_entry (list_of_purchaseEntry: List<PurchaseEntry>) {
        val latestPurchase = purchaseDao.getLastPurchase()
        for (purchaseEntry in list_of_purchaseEntry){
            item_Stock_update_purchase(itemName = purchaseEntry.itemName, purchaseEntry.entryQuantity)
            purchaseEntryDao.insert(purchaseEntry.copy(purchaseId = latestPurchase.purchaseId))
        }
    }

    private suspend fun updatePurchaseEntry(purchaseID: Int, old_list_of_purchaseEntry: List<PurchaseEntry>, new_list_of_purchaseEntry: List<PurchaseEntry>){
        for (entry in old_list_of_purchaseEntry){
            purchaseEntryDao.delete(entry.copy(purchaseId = purchaseID))
        }
        for (entry in new_list_of_purchaseEntry){
            purchaseEntryDao.insert(entry.copy(purchaseId = purchaseID))
        }
    }

    // function to be used within AddPurchase
    private suspend fun add_purchase (name : String, purchase: Purcahase) {
        val ledger = ledgerDao.getLedgerByAccountName(name)
        if (purchase.purchaseType == "Credit"){
            ledgerDao.update(ledger.copy(ledgerNetBalance = ledger.ledgerNetBalance + purchase.purchaseAmount))
        }
        purchaseDao.insert(purchase.copy(ledgerId = ledger.ledgerId))
    }


    private suspend fun item_Stock_update_purchase (itemName : String, itemQuantity : Int) {
        var item = itemInventoryDao.getItemByName(itemName)
        itemInventoryDao.update(item.copy(itemQuantity = item.itemQuantity + itemQuantity))
    }

    // 1st process for updating
    private suspend fun updateLedgerNetBalance(newName : String, oldName: String, oldPurchase : Purcahase,newPurchase: Purcahase){

        //reversing old ledger
        val oldLedger = ledgerDao.getLedgerByAccountName(oldName)
        if (oldPurchase.purchaseType == "Credit"){
            oldLedger.ledgerNetBalance = oldLedger.ledgerNetBalance - oldPurchase.purchaseAmount
            ledgerDao.update(oldLedger)
        }

        // updating new ledger
        val newLedger = ledgerDao.getLedgerByAccountName(newName)
        if (newPurchase.purchaseType == "Credit"){
            newLedger.ledgerNetBalance = newLedger.ledgerNetBalance + newPurchase.purchaseAmount
            ledgerDao.update(newLedger)
        }
    }

    //second process for updating
    private suspend fun privateUpdateSale(newName : String,newPurchase: Purcahase){
        val newLedger = ledgerDao.getLedgerByAccountName(newName)
        purchaseDao.update(newPurchase.copy(ledgerId = newLedger.ledgerId))
    }

    // 3rd and final process for updating
    private suspend fun updateItemStock(old_list_of_purchaseEntry: List<PurchaseEntry>, new_list_of_purchaseEntry: List<PurchaseEntry>){

        //reversing the item stock for old_list_of_purchaseEntry
        for (Entry in old_list_of_purchaseEntry) {
            var item = itemInventoryDao.getItemByName(Entry.itemName)
            itemInventoryDao.update(item.copy(itemQuantity = item.itemQuantity - Entry.entryQuantity))
        }

        // inserting new Entry and updating item stock for new_list_saleEntry
        for (Entry in new_list_of_purchaseEntry) {
            var item = itemInventoryDao.getItemByName(Entry.itemName)
            itemInventoryDao.update(item.copy(itemQuantity = item.itemQuantity + Entry.entryQuantity))
        }

    }

}