package sec.hungn1.ccqlvt.core.di.module

import android.app.Activity
import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import sec.hungn1.ccqlvt.core.di.scope.FragmentScope

/**
 * Created by hung.nq1 on 8/8/2017.
 */
@Module
class FragmentModule(val mFragment: Fragment) {
    @Provides
    @FragmentScope
    fun providerActivity(): Activity = mFragment.getActivity()
}