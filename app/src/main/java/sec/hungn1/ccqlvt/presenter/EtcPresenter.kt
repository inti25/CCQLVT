package sec.hungn1.ccqlvt.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.dao.OtherDao
import sec.hungn1.ccqlvt.core.database.entities.Other
import sec.hungn1.ccqlvt.core.presentation.RxPresenter
import sec.hungn1.ccqlvt.presenter.contract.EtcContract
import sec.hungn1.ccqlvt.util.Event
import java.util.*
import javax.inject.Inject

/**
 * Created by hung.nq1 on 8/8/2017.
 */
class EtcPresenter : RxPresenter<EtcContract.View>, EtcContract.Presenter {

    var mOtherDao: OtherDao

    @Inject
    constructor(otherDao: OtherDao) {
        mOtherDao = otherDao
    }

    override fun getData() {
        addSubscribe(mOtherDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t -> sortAndSetSection(t) }
                .subscribe({ t ->
                    mView?.updateData(t)
                }))
    }

    override fun deleteOther(other: Other) {
        mOtherDao.delete(other)
        EventBus.getDefault().post(Event(Constants.EVENT_OTHER_UPDATEED))
    }

    fun sortAndSetSection(itemList: List<Other>): ArrayList<Other> {
        val tmpList: ArrayList<Other> = ArrayList()
        Collections.sort(itemList)
        var header: Long = 0
        for (item in itemList) {
            if (header != item.time) {
                val tmp: Other = Other()
                tmp.time = item.time
                header = item.time
                tmpList.add(tmp)
            }
            tmpList.add(item)
        }
        return tmpList
    }

}