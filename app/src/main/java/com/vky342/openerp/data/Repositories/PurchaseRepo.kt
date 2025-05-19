package com.vky342.openerp.data.Repositories

import android.util.Log
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Entities.Purcahase
import com.vky342.openerp.data.Entities.PurchaseEntry
import com.vky342.openerp.data.Entities.Sale
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

    suspend fun getLatestPurchaseID() : Int{
        try {
            val pid = purchaseDao.getLastPurchase().purchaseId
            return pid
        } catch (e: Exception){
            Log.d("STATUS","No previous purchase found; returning id as 0")
            return 0
        }
    }

    // function to be used within AddPurchase
    private suspend fun add_purchase_entry (list_of_purchaseEntry: List<PurchaseEntry>) {
        val latestPurchase = purchaseDao.getLastPurchase()
        for (purchaseEntry in list_of_purchaseEntry){
            item_Stock_update_purchase(itemName = purchaseEntry.itemName, purchaseEntry.entryQuantity)
            purchaseEntryDao.insert(purchaseEntry.copy(purchaseId = latestPurchase.purchaseId))
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

    fun get_every_Purchase() : Flow<List<Purcahase>> {
        return purchaseDao.getAllPurchase()
    }

}