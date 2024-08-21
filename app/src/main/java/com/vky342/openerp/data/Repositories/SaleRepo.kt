package com.vky342.openerp.data.Repositories

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

        saleDao.insert(sale.copy(ledgerId = ledger.ledgerId))
    }


    private suspend fun item_Stock_Update (itemName : String, itemQuantity : Int) {

        val all_Items = itemInventoryDao.getAllItemInInventory()

        for (item in all_Items) {
            if (item.itemName == itemName){
                // function to decrease item stock in inventory
                itemInventoryDao.update(item = item.copy(itemQuantity = item.itemQuantity - itemQuantity))
            }
        }

    }

}