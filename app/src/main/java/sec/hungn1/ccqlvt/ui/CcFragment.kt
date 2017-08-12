package sec.hungn1.ccqlvt.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.footer_cc.view.*
import kotlinx.android.synthetic.main.fragment_cc.*
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.presentation.BaseFragment
import sec.hungn1.ccqlvt.presenter.CcPresenter
import sec.hungn1.ccqlvt.presenter.contract.CcContract
import sec.hungn1.ccqlvt.ui.adapter.HumanAdapter
import sec.hungn1.ccqlvt.ui.adapter.ViewCcAdapter
import sec.hungn1.ccqlvt.util.Event

/**
 * Created by hung.nq1 on 8/8/2017.
 */

class CcFragment : BaseFragment<CcPresenter, CcContract.View>(), CcContract.View {
    lateinit var mAdapter: ViewCcAdapter
    var mFooterView: View? = null

    override fun updateTotal(long: Float) {
        mFooterView?.tvtcvl?.text = long.toString()
    }

    override fun showError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMessageEvent(event: Event) {
        when (event.key) {
            Constants.EVENT_RECORD_CHANGED -> {
                mAdapter.updateData(mPresenter.getData())
            }
        }
    }

    override fun initInject() {
        component.inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_cc
    }

    override fun initEventAndData() {
        mFooterView = (mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.footer_cc, null, false)
        mFooterView?.payed?.visibility = View.GONE
        mFooterView?.payedvl?.visibility = View.GONE
        mFooterView?.notpay?.visibility = View.GONE
        mFooterView?.notpayvl?.visibility = View.GONE
        lstCcView.addFooterView(mFooterView)
        mAdapter = ViewCcAdapter(mContext, mPresenter.getData())
        lstCcView.adapter = mAdapter
    }

}