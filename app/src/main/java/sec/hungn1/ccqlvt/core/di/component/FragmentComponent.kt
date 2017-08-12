package sec.hungn1.ccqlvt.core.di.component

import android.app.Activity
import dagger.Component
import sec.hungn1.ccqlvt.core.di.module.FragmentModule
import sec.hungn1.ccqlvt.core.di.scope.FragmentScope
import sec.hungn1.ccqlvt.ui.CcFragment
import sec.hungn1.ccqlvt.ui.EtcFragment
import sec.hungn1.ccqlvt.ui.MaterialsFragment

/**
 * Created by hung.nq1 on 8/8/2017.
 */
@FragmentScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun getActivity(): Activity

    fun inject(mCcFragment: CcFragment)

    fun inject(mMaterialsFragment: MaterialsFragment)

    fun inject(mEtcFragment: EtcFragment)
}