package com.vky342.openerp.data.ViewModels.transaction

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.DAOs.ledgerDao
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.Repositories.AccountRepo
import com.vky342.openerp.data.Repositories.InventoryRepo
import com.vky342.openerp.data.Repositories.LedgerRepo
import com.vky342.openerp.data.Repositories.SaleRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Modify_Sale_Vm @Inject constructor(
    private val saleRepo: SaleRepo,
    private val accountRepo: AccountRepo,
    private val inventoryRepo: InventoryRepo,
    private val ledgerRepo: LedgerRepo
) : ViewModel(){
    var bill_id_to_modify = mutableStateOf(0)
    val sale_list : MutableState<List<Sale>> = mutableStateOf(listOf())
    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    val oldSale : MutableState<Sale> = mutableStateOf(Sale(0,"",0,0.0,""))
    val all_items_in_inventory : MutableState<List<Item>> = mutableStateOf(listOf())

    val accountNameLoaded = MutableLiveData<Boolean>(false)
    val saleEntriesLoaded = MutableLiveData<Boolean>(false)

    val old_Account_Name : MutableState<String> = mutableStateOf("")
    val old_list_of_saleEntries : MutableState<List<SaleEntry>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            saleRepo.get_every_Sale().collect(){
                    newData -> sale_list.value = newData
                Log.d("STATUS", "collected sale line 27 -vm")
            }
        }
        viewModelScope.launch {
            accountRepo.get_every_Account().collect(){
                    newData -> old_Account_list.value = newData
                Log.d("STATUS", "collected accounts line 24 -vm")
            }
        }
        viewModelScope.launch {
            inventoryRepo.retrun_All_items_in_inventory().collect(){
                    newData -> all_items_in_inventory.value = newData
                Log.d("STATUS", "collected items line 40 -vm")
            }
        }
    }

    fun update_Sale(
        account_name: String,
        sale: Sale,
        new_list_of_saleEntries: List<SaleEntry>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            saleRepo.updateSale(
                oldName = old_Account_Name.value,
                newName = account_name,
                oldSale = oldSale.value,
                newSale = sale,
                old_list_of_saleEntry = old_list_of_saleEntries.value,
                new_list_of_saleEntry = new_list_of_saleEntries
            )
        }

    }

    fun getSaleByID(ID : Int) : Sale {
        val old_sale = sale_list.value.find { it.saleId == ID}!!
        oldSale.value = old_sale
        viewModelScope.launch {
            old_list_of_saleEntries.value = saleRepo.getListOfSaleEntriesByID(saleID = ID)
            saleEntriesLoaded.value = true
            Log.d("DEBUG"," collected sale entries : " + old_list_of_saleEntries.value)
        }
        viewModelScope.launch {
            old_Account_Name.value =  ledgerRepo.getLedger(old_sale.ledgerId).accountName
            accountNameLoaded.value = true
            Log.d("DEBUG"," collected account name : " + old_Account_Name.value)
        }
        return old_sale
    }


}