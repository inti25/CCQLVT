package sec.hungn1.ccqlvt.core.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import sec.hungn1.ccqlvt.core.application.App
import javax.inject.Singleton

/**
 * Created by hung.nq1 on 8/7/2017.
 */
@Module
class AppModule(val mApp: App) {
    @Provides
    @Singleton
    fun providerCCApplication() = mApp
}