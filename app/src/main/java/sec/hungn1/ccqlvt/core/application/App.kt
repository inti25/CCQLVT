package sec.hungn1.ccqlvt.core.application

import android.app.Application
import sec.hungn1.ccqlvt.core.di.component.AppComponent
import sec.hungn1.ccqlvt.core.di.module.AppModule
import sec.hungn1.ccqlvt.core.di.component.DaggerAppComponent

/**
 * Created by sev_user on 8/7/2017.
 */
open class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                //.networkModule(NetworkModule())
                .appModule(AppModule(this))
                .build()
    }

    companion object {
        lateinit var mInstance: App
        lateinit var mAppComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mAppComponent = this.component
    }

}