package sec.hungn1.ccqlvt.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_materials.*
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.entities.Other
import sec.hungn1.ccqlvt.core.presentation.BaseFragment
import sec.hungn1.ccqlvt.presenter.EtcPresenter
import sec.hungn1.ccqlvt.presenter.contract.EtcContract
import sec.hungn1.ccqlvt.ui.adapter.OtherAdapter
import sec.hungn1.ccqlvt.util.Event

/**
 * Created by hung.nq1 on 8/8/2017.
 */
class EtcFragment : BaseFragment<EtcPresenter, EtcContract.View>(), EtcContract.View {

    lateinit var mAdapter: OtherAdapter
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun showError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initInject() {
        component.inject(this)
    }

    override fun clearData() {
        mAdapter.clearData()
    }

    override fun updateData(data: ArrayList<Other>) {
        mAdapter.updateData(data)
    }

    override fun getLayoutId(): Int = R.layout.fragment_materials

    override fun initEventAndData() {
        mLayoutManager = LinearLayoutManager(mContext);
        mAdapter = OtherAdapter()
        material_list_view.layoutManager = mLayoutManager
        material_list_view.adapter = mAdapter
        mPresenter.getData()
    }

    override fun onMessageEvent(event: Event) {
        when (event.key) {
            Constants.EVENT_OTHER_UPDATEED -> {
                mPresenter.getData()
            }
            Constants.EVENT_OTHER_REMOVE -> {
                if (event.data is Other) {
                    mPresenter.deleteOther(event.data as Other)
                }
            }
        }
    }

}