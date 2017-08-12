package sec.hungn1.ccqlvt.presenter.contract

import sec.hungn1.ccqlvt.core.database.entities.Material
import sec.hungn1.ccqlvt.core.presentation.BasePresenter
import sec.hungn1.ccqlvt.core.presentation.BaseView

/**
 * Created by hung.nq1 on 8/8/2017.
 */

interface MaterialsContract {
    interface View : BaseView {
        fun updateDataToView(data: ArrayList<Material>)
    }

    interface Presenter : BasePresenter<View> {
        fun updateData()
        fun deleteMaterial(material: Material)
    }
}