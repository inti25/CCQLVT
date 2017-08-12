package sec.hungn1.ccqlvt.presenter

import sec.hungn1.ccqlvt.core.database.dao.CcRecordDao
import sec.hungn1.ccqlvt.core.database.entities.RecordItem
import sec.hungn1.ccqlvt.core.presentation.BasePresenter
import sec.hungn1.ccqlvt.presenter.contract.CcContract
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by hung.nq1 on 8/8/2017.
 */
class CcPresenter : BasePresenter<CcContract.View>, CcContract.Presenter {
    var mView: CcContract.View? = null
    lateinit var mRecordDao: CcRecordDao

    @Inject
    constructor(recordDao: CcRecordDao) {
        mRecordDao = recordDao
    }

    override fun getData(): ArrayList<RecordItem> {
        return sortAndSetSection(mRecordDao.getAllRecordItem())
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