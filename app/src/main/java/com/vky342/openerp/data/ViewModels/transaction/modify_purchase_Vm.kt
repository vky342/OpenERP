package com.vky342.openerp.data.ViewModels.transaction

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Entities.Purcahase
import com.vky342.openerp.data.Entities.PurchaseEntry
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.Repositories.AccountRepo
import com.vky342.openerp.data.Repositories.InventoryRepo
import com.vky342.openerp.data.Repositories.LedgerRepo
import com.vky342.openerp.data.Repositories.PurchaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class modify_purchase_Vm @Inject constructor(
    private val purchaseRepo: PurchaseRepo,
    private val accountRepo: AccountRepo,
    private val inventoryRepo: InventoryRepo,
    private val ledgerRepo: LedgerRepo
) : ViewModel() {
    var bill_id_to_modify = mutableStateOf(0)
    val purcahase_list : MutableState<List<Purcahase>> = mutableStateOf(listOf())
    val old_Account_list : MutableState<List<Account>> = mutableStateOf(listOf())
    val oldPurchase : MutableState<Purcahase> = mutableStateOf(Purcahase(0,"",0,0.0,""))
    val all_items_in_inventory : MutableState<List<Item>> = mutableStateOf(listOf())

    val accountNameLoaded = MutableLiveData<Boolean>(false)
    val purchaseEntriesLoaded = MutableLiveData<Boolean>(false)

    val old_Account_Name : MutableState<String> = mutableStateOf("")
    val old_list_of_purchaseEntries : MutableState<List<PurchaseEntry>> = mutableStateOf(listOf())

    var latestPurchaseId : MutableState<Int> = mutableStateOf(0)

    init {
        viewModelScope.launch {
            purchaseRepo.get_every_Purchase().collect(){
                    newData -> purcahase_list.value = newData
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
        viewModelScope.launch {
            latestPurchaseId.value = purchaseRepo.getLatestPurchaseID()
        }
    }

    fun update_Purchase(
        account_name: String,
        purchase: Purcahase,
        new_list_of_purchaseEntries: List<PurchaseEntry>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            purchaseRepo.updatePurchase(
                oldName = old_Account_Name.value,
                newName = account_name,
                oldPurhcase = oldPurchase.value,
                newPurchase = purchase,
                old_list_of_purhcaseEntry = old_list_of_purchaseEntries.value,
                new_list_of_purchaseEntry = new_list_of_purchaseEntries
            )
            old_list_of_purchaseEntries.value = listOf()
            bill_id_to_modify.value = 0
            oldPurchase.value = Purcahase(0,"",0,0.0,"")
            old_Account_Name.value = ""
        }
        accountNameLoaded.value = false
        purchaseEntriesLoaded.value = false

    }

    fun getPurchaseByID(ID : Int) : Purcahase {
        val old_purchase = purcahase_list.value.find { it.purchaseId == ID}!!
        oldPurchase.value = old_purchase
        viewModelScope.launch {
            old_list_of_purchaseEntries.value = purchaseRepo.getListOfPurchaseEntriesByID(purchaseID = ID)
            purchaseEntriesLoaded.value = true
            Log.d("DEBUG"," collected purchase entries : " + old_list_of_purchaseEntries.value)
        }
        viewModelScope.launch {
            old_Account_Name.value = ledgerRepo.getLedger(old_purchase.ledgerId).accountName
            accountNameLoaded.value = true
            Log.d("DEBUG"," collected account name : " + old_Account_Name.value)
        }
        return old_purchase
    }

}