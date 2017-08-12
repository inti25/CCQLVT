package sec.hungn1.ccqlvt.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.dao.MaterialDao
import sec.hungn1.ccqlvt.core.database.entities.Material
import sec.hungn1.ccqlvt.core.presentation.BasePresenter
import sec.hungn1.ccqlvt.core.presentation.RxPresenter
import sec.hungn1.ccqlvt.presenter.contract.MaterialsContract
import sec.hungn1.ccqlvt.util.Event
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by hung.nq1 on 8/8/2017.
 */
class MaterialsPresenter : RxPresenter<MaterialsContract.View>, MaterialsContract.Presenter {

    var mMaterialDao: MaterialDao

    @Inject
    constructor(materialDao: MaterialDao) {
        mMaterialDao = materialDao
    }

    override fun deleteMaterial(material: Material) {
        mMaterialDao.delete(material)
        EventBus.getDefault().post(Event(Constants.EVENT_MATERIAL_UPDATEED))
    }

    override fun updateData() {
        addSubscribe(mMaterialDao.getAllMaterial()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t -> sortAndSetSection(t) }
                .subscribe({ t ->
                    mView?.updateDataToView(t)
                }))
    }

    fun sortAndSetSection(itemList: List<Material>): ArrayList<Material> {
        val tmpList: ArrayList<Material> = ArrayList()
        Collections.sort(itemList)
        var header: Long = 0
        for (item in itemList) {
            if (header != item.time) {
                val tmp: Material = Material()
                tmp.time = item.time
                header = item.time
                tmpList.add(tmp)
            }
            tmpList.add(item)
        }
        return tmpList
    }
}