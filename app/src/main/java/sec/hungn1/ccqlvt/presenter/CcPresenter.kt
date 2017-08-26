package sec.hungn1.ccqlvt.presenter

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import sec.hungn1.ccqlvt.core.database.dao.CcRecordDao
import sec.hungn1.ccqlvt.core.database.entities.RecordItem
import sec.hungn1.ccqlvt.core.presentation.BasePresenter
import sec.hungn1.ccqlvt.core.presentation.RxPresenter
import sec.hungn1.ccqlvt.presenter.contract.CcContract
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by hung.nq1 on 8/8/2017.
 */
class CcPresenter @Inject constructor(recordDao: CcRecordDao) : RxPresenter<CcContract.View>(), CcContract.Presenter {
    var mRecordDao: CcRecordDao = recordDao

    override fun getData(hid: Int?) {
        addSubscribe(mRecordDao.getAllRecordItem(hid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t -> sortAndSetSection(t) }
                .subscribe({ t ->
                    mView?.updateData(t)
                }))
    }

    fun sortAndSetSection(itemList: List<RecordItem>): ArrayList<RecordItem> {
        val tmpList: ArrayList<RecordItem> = ArrayList()
        Collections.sort(itemList)
        var header: Long = 0
        var total: Float = 0F
        for (item in itemList) {
            if (header != item.time) {
                val tmp: RecordItem = RecordItem()
                tmp.time = item.time
                tmp.isSection = true
                header = item.time
                tmpList.add(tmp)
            }
            if (item.value > 0F) {
                tmpList.add(item)
                total += item.value
            }
        }
        mView?.updateTotal(total)
        return tmpList
    }

    override fun attachView(view: CcContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

}