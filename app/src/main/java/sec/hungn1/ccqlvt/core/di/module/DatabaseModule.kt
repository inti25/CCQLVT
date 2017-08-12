package sec.hungn1.ccqlvt.core.di.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import sec.hungn1.ccqlvt.core.application.App
import sec.hungn1.ccqlvt.core.database.DatabaseManager
import javax.inject.Singleton

/**
 * Created by hung.nq1 on 8/8/2017.
 */
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providerDatabase(context: App): DatabaseManager =
            Room.databaseBuilder(context, DatabaseManager::class.java, "cc_db")
                    .allowMainThreadQueries()
                    .build()

    @Provides
    fun providerHumanDao(databaseManager: DatabaseManager) = databaseManager.humanDao()

    @Provides
    fun providerRecordDao(databaseManager: DatabaseManager) = databaseManager.recordDao()

    @Provides
    fun providerMaterialDao(databaseManager: DatabaseManager) = databaseManager.materialDao()

    @Provides
    fun providerOtherDao(databaseManager: DatabaseManager) = databaseManager.otherDao()
}