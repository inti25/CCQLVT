package sec.hungn1.ccqlvt.core.di.component

import android.content.Context
import dagger.Component
import sec.hungn1.ccqlvt.core.application.App
import sec.hungn1.ccqlvt.core.database.DatabaseManager
import sec.hungn1.ccqlvt.core.database.dao.CcRecordDao
import sec.hungn1.ccqlvt.core.database.dao.HumanDao
import sec.hungn1.ccqlvt.core.database.dao.MaterialDao
import sec.hungn1.ccqlvt.core.database.dao.OtherDao
import sec.hungn1.ccqlvt.core.di.module.AppModule
import sec.hungn1.ccqlvt.core.di.module.DatabaseModule
import sec.hungn1.ccqlvt.core.presentation.BaseDialog
import sec.hungn1.ccqlvt.ui.AddCcFormDialog
import sec.hungn1.ccqlvt.ui.AddMaterialDialog
import sec.hungn1.ccqlvt.ui.AddOtherDialog
import javax.inject.Singleton

/**
 * Created by hung.nq1 on 8/7/2017.
 */

@Component(modules = arrayOf(AppModule::class, DatabaseModule::class))
@Singleton
interface AppComponent {
    fun getCCApplication(): App
    fun getDbManager(): DatabaseManager
    fun getHumanDao(): HumanDao
    fun getRecordDao(): CcRecordDao
    fun getMaterialDao(): MaterialDao
    fun getOtherDao(): OtherDao
    fun inject(dialog: AddCcFormDialog)
    fun inject(dialog: AddMaterialDialog)
    fun inject(dialog: AddOtherDialog)
}