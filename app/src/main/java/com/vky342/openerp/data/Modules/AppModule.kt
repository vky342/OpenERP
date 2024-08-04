package com.vky342.openerp.data.Modules

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vky342.openerp.data.DAOs.accountsDao
import com.vky342.openerp.data.DAOs.billentriesDao
import com.vky342.openerp.data.DAOs.billsDao
import com.vky342.openerp.data.DAOs.itemInventoryDao
import com.vky342.openerp.data.DAOs.ledgerDao
import com.vky342.openerp.data.DAOs.paymentsDao
import com.vky342.openerp.data.DAOs.receiptDao
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Bill
import com.vky342.openerp.data.Entities.BillEntry
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Payment
import com.vky342.openerp.data.Entities.Receipt
import com.vky342.openerp.data.Repositories.AccountRepo
import com.vky342.openerp.data.Repositories.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{


    @Provides
    @Singleton
    fun provideOpenERPDB(@ApplicationContext context: Context) : OpenERPDataBase {
        Log.d("CREATED", "DATABASE INSTANCE")
        return Room.databaseBuilder(context, OpenERPDataBase::class.java, name = "OpenERPDB").build()

    }

    @Provides
    @Singleton
    fun provideHomeRepo(openDb : OpenERPDataBase) : HomeRepo{
        return HomeRepo(openDb)
    }

    @Provides
    @Singleton
    fun provideAccountRepo(openDb: OpenERPDataBase) : AccountRepo{
        return AccountRepo(openDb)
    }

}


@Database(entities = [Account::class, Bill::class, BillEntry::class, Item::class, Ledger::class, Payment::class, Receipt::class], version = 1)
abstract class OpenERPDataBase : RoomDatabase(){

    abstract fun getAccountDao() : accountsDao

    abstract fun getBillDao() : billsDao

    abstract fun getBillEntryDao() : billentriesDao

    abstract fun getItemInventoryDao() : itemInventoryDao

    abstract fun getLedgerDao() : ledgerDao

    abstract fun getPaymentDao() : paymentsDao

    abstract fun getReceiptDao() : receiptDao

}