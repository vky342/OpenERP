package com.vky342.openerp.data.Repositories


import android.util.Log
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Modules.OpenERPDataBase
import javax.inject.Inject


class HomeRepo ( private val openERPDataBase: OpenERPDataBase){
    val account_dao = openERPDataBase.getAccountDao()
    suspend fun add_account(){
        account_dao.insert(Account(name = "kunal", address = "India", contact = 4283584, netBalance = 500))
        Log.d("ADDED", "ACCOUNT HOME repo")
    }
}