package com.vky342.openerp.data.Repositories

import com.vky342.openerp.data.Entities.Purcahase
import com.vky342.openerp.data.Entities.PurchaseEntry
import com.vky342.openerp.data.Modules.OpenERPDataBase

class PurchaseRepo (private val openERPDataBase: OpenERPDataBase) {

    private val ledgerDao = openERPDataBase.getLedgerDao()

    private val purchaseDao = openERPDataBase.getPurchaseDao()

    private val purchaseEntryDao = openERPDataBase.getPurchaseEntryDao()

    private val itemInventoryDao = openERPDataBase.getItemInventoryDao()

    private val accountDao = openERPDataBase.getAccountDao()

    // public function to add purchase
    suspend fun AddPurchase (account_name : String, purchase : Purcahase, list_of_purchaseEntry : List<PurchaseEntry>) {

        add_purchase(account_name, purchase)
        add_purchase_entry(list_of_purchaseEntry)

    }

    suspend fun getAccounts() = accountDao.getAllAccounts()


    // function to be used within AddPurchase
    private suspend fun add_purchase_entry (list_of_purchaseEntry: List<PurchaseEntry>) {

        val latestPurchase = purchaseDao.getLastPurchase()

        for (purchaseEntry in list_of_purchaseEntry){

            item_Stock_update(purchaseEntry.itemName, purchaseEntry.entryQuantity, purchaseEntry.finalPrice)
            purchaseEntryDao.insert(purchaseEntry.copy(purchaseId = latestPurchase.purchaseId))


        }
    }


    // function to be used within AddPurchase
    private suspend fun add_purchase (name : String, purchase: Purcahase) {

        val ledger = ledgerDao.getLedgerByAccountName(name)

        purchaseDao.insert(purchase.copy(ledgerId = ledger.ledgerId))
    }


    private suspend fun item_Stock_update (itemName : String, itemQuantity : Int, itemNewPurchasePrice : Double) {

        val all_Items = itemInventoryDao.getAllItemInInventory()

        for (item in all_Items){
            if (item.itemName == itemName){
                /// function to increase item stock in inventory
                itemInventoryDao.update(item = item.copy(itemQuantity = item.itemQuantity + itemQuantity, itemPurchasePrice = itemNewPurchasePrice))
            }

        }
    }


}