package sec.hungn1.ccqlvt.presenter

import android.support.design.widget.Snackbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.DatabaseManager
import sec.hungn1.ccqlvt.core.database.dao.HumanDao
import sec.hungn1.ccqlvt.core.database.entities.Human
import sec.hungn1.ccqlvt.core.presentation.BasePresenter
import sec.hungn1.ccqlvt.presenter.contract.MainContract
import sec.hungn1.ccqlvt.util.Event
import javax.inject.Inject

/**
 * Created by hung.nq1 on 8/7/2017.
 */

class MainPresenter : BasePresenter<MainContract.View>, MainContract.Presenter {

    val TAG = "MainPresenter"
    var mHumanDao: HumanDao

    @Inject
    constructor(humanDao: HumanDao) {
        mHumanDao = humanDao
    }

    override fun addHuman(name: String) {
        val human: Human = Human(name = name)
        try {
            mHumanDao.insertHuman(human)
            EventBus.getDefault().post(Event(Constants.EVENT_HUMAN_ADDED))
        } catch (e: Exception) {
            mView?.showError(e.message.toString())
        }
    }

    var mView: MainContract.View? = null

    override fun onFabClick(v: View) {
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    override fun attachView(view: MainContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

}