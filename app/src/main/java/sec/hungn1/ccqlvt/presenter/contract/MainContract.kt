package sec.hungn1.ccqlvt.presenter.contract

import android.view.MenuItem
import sec.hungn1.ccqlvt.core.presentation.BasePresenter
import sec.hungn1.ccqlvt.core.presentation.BaseView

/**
 * Created by hung.nq1 on 8/7/2017.
 */

interface MainContract {

    interface View : BaseView {
    }

    interface Presenter : BasePresenter<View> {
        fun onFabClick(v: android.view.View)
        fun addHuman(name: String)
    }
}