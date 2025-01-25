package com.vky342.openerp.data.Repositories


import com.vky342.openerp.data.Modules.OpenERPDataBase

// ab mere ko yahan se flow start krna hai
class HomeRepo ( private val openERPDataBase: OpenERPDataBase){
    val db = openERPDataBase.getSaleDao()
}