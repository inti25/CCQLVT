package sec.hungn1.ccqlvt.presenter.contract

import sec.hungn1.ccqlvt.core.database.entities.RecordItem
import sec.hungn1.ccqlvt.core.presentation.BasePresenter
import sec.hungn1.ccqlvt.core.presentation.BaseView

/**
 * Created by hung.nq1 on 8/8/2017.
 */
interface CcContract {
    interface View : BaseView {
        fun updateTotal(long: Float)
        fun updateData(data: ArrayList<RecordItem>)
    }

    interface Presenter : BasePresenter<View> {
        fun getData(hid: Int? = null)
    }
}