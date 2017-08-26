package sec.hungn1.ccqlvt.ui.dialog

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_fillter.*
import org.greenrobot.eventbus.EventBus
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.App
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.dao.HumanDao
import sec.hungn1.ccqlvt.core.database.entities.Human
import sec.hungn1.ccqlvt.core.presentation.BaseDialog
import sec.hungn1.ccqlvt.ui.adapter.HumanAdapter
import sec.hungn1.ccqlvt.util.Event
import javax.inject.Inject

/**
 * Created by hung.nq1 on 8/23/2017.
 */
class HumanFillterDialog(context: Context) : BaseDialog(context) {

    @Inject
    lateinit var mHumanDao: HumanDao
    lateinit var mAdapter: HumanAdapter
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    var mContext = context

    override fun getLayoutId(): Int {
        return R.layout.dialog_fillter
    }

    override fun initViewAndData() {
        mAdapter = HumanAdapter(object : HumanAdapter.OnItemClickListener {
            override fun onItemClick(human: Human) {
                EventBus.getDefault().post(Event(Constants.EVENT_HUMAN_FILLTER, human))
                dismiss()
            }

        })
        mLayoutManager = LinearLayoutManager(mContext)
        rvListHuman.layoutManager = mLayoutManager
        rvListHuman.adapter = mAdapter
        mAdapter.updateData(mHumanDao.getAllHuman() as ArrayList<Human>)
    }

    override fun initInject() {
        App.mAppComponent.inject(this)
    }

}