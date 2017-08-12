package sec.hungn1.ccqlvt.presenter.contract

import sec.hungn1.ccqlvt.core.database.entities.Other
import sec.hungn1.ccqlvt.core.presentation.BasePresenter
import sec.hungn1.ccqlvt.core.presentation.BaseView

/**
 * Created by hung.nq1 on 8/8/2017.
 */
interface EtcContract {
    interface View : BaseView {
        fun clearData()
        fun updateData(data: ArrayList<Other>)
    }

    interface Presenter : BasePresenter<View> {
        fun getData()
        fun deleteOther(other: Other)
    }
}