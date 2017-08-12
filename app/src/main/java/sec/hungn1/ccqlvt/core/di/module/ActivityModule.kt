package sec.hungn1.ccqlvt.core.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import sec.hungn1.ccqlvt.core.di.scope.ActivityScope

/**
 * Created by hung.nq1 on 8/7/2017.
 */
@Module
class ActivityModule(val mActivity: Activity) {
    @Provides
    @ActivityScope
    fun providerActivity(): Activity = mActivity;
}