package sec.hungn1.ccqlvt.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.footer_cc.view.*
import kotlinx.android.synthetic.main.fragment_cc.*
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.entities.Human
import sec.hungn1.ccqlvt.core.database.entities.RecordItem
import sec.hungn1.ccqlvt.core.presentation.BaseFragment
import sec.hungn1.ccqlvt.presenter.CcPresenter
import sec.hungn1.ccqlvt.presenter.contract.CcContract
import sec.hungn1.ccqlvt.ui.adapter.ViewCcAdapter
import sec.hungn1.ccqlvt.util.Event
import sec.hungn1.ccqlvt.util.Utils.updateVisibility

/**
 * Created by hung.nq1 on 8/8/2017.
 */

class CcFragment : BaseFragment<CcPresenter, CcContract.View>(), CcContract.View {
    lateinit var mAdapter: ViewCcAdapter
    var mFooterView: View? = null
    var mCurrentIdFilter: Int? = null

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
                mPresenter.getData(mCurrentIdFilter)
            }
            Constants.EVENT_HUMAN_FILLTER -> {
                if (event.data != null && event.data is Human) {
                    val human = event.data as Human
                    mCurrentIdFilter = human.id
                    mPresenter.getData(mCurrentIdFilter)
                }
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
        mAdapter = ViewCcAdapter(mContext, ArrayList())
        lstCcView.adapter = mAdapter
        lstCcView.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    activity.floatingMenu?.updateVisibility(View.GONE)
                } else {
                    activity.floatingMenu?.updateVisibility(View.VISIBLE)
                }
            }

            override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {

            }
        })
        mPresenter.getData(mCurrentIdFilter)
    }

    override fun updateData(data: ArrayList<RecordItem>) {
        mAdapter.updateData(data)
    }
}