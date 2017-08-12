package sec.hungn1.ccqlvt.core.di.component

import android.app.Activity
import dagger.Component
import sec.hungn1.ccqlvt.ui.MainActivity
import sec.hungn1.ccqlvt.core.di.module.ActivityModule
import sec.hungn1.ccqlvt.core.di.scope.ActivityScope

/**
 * Created by hung.nq1 on 8/7/2017.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun getActivity(): Activity

    fun inject(mainActivity: MainActivity)
}