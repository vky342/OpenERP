package com.vky342.openerp.data.Modules

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vky342.openerp.data.DAOs.PurchaseDao
import com.vky342.openerp.data.DAOs.PurchaseEntryDao
import com.vky342.openerp.data.DAOs.SaleDao
import com.vky342.openerp.data.DAOs.SaleEntryDao
import com.vky342.openerp.data.DAOs.accountsDao
import com.vky342.openerp.data.DAOs.itemInventoryDao
import com.vky342.openerp.data.DAOs.ledgerDao
import com.vky342.openerp.data.DAOs.paymentsDao
import com.vky342.openerp.data.DAOs.receiptDao
import com.vky342.openerp.data.Entities.Account
import com.vky342.openerp.data.Entities.Item
import com.vky342.openerp.data.Entities.Ledger
import com.vky342.openerp.data.Entities.Payment
import com.vky342.openerp.data.Entities.Purcahase
import com.vky342.openerp.data.Entities.PurchaseEntry
import com.vky342.openerp.data.Entities.Receipt
import com.vky342.openerp.data.Entities.Sale
import com.vky342.openerp.data.Entities.SaleEntry
import com.vky342.openerp.data.Repositories.AccountRepo
import com.vky342.openerp.data.Repositories.HomeRepo
import com.vky342.openerp.data.Repositories.InventoryRepo
import com.vky342.openerp.data.Repositories.PurchaseRepo
import com.vky342.openerp.data.Repositories.SaleRepo
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

    @Provides
    @Singleton
    fun providePurchaseRepo(openDb: OpenERPDataBase) : PurchaseRepo{
        return PurchaseRepo(openDb)
    }

    @Provides
    @Singleton
    fun provideInventoryRepo(openDb: OpenERPDataBase) : InventoryRepo{
        return InventoryRepo(openDb)
    }

    @Provides
    @Singleton
    fun provideSaleRepo (openDb: OpenERPDataBase) : SaleRepo {
        return SaleRepo(openDb)
    }

}


@Database(entities = [Account::class, Item::class,Ledger::class,Payment::class,Purcahase::class,PurchaseEntry::class,Receipt::class,SaleEntry::class,Sale::class], version = 1)
abstract class OpenERPDataBase : RoomDatabase(){

    abstract fun getAccountDao() : accountsDao

    abstract fun getLedgerDao() : ledgerDao

    abstract fun getItemInventoryDao() : itemInventoryDao

    abstract fun getPaymentDao() : paymentsDao

    abstract fun getPurchaseDao() : PurchaseDao

    abstract fun getPurchaseEntryDao() : PurchaseEntryDao

    abstract fun getSaleDao() : SaleDao

    abstract fun getSaleEntryDao() : SaleEntryDao

    abstract fun getReceiptDao() : receiptDao

}