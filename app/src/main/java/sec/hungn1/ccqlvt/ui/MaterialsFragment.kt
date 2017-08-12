package sec.hungn1.ccqlvt.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.fragment_materials.*
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.entities.Material
import sec.hungn1.ccqlvt.core.presentation.BaseFragment
import sec.hungn1.ccqlvt.presenter.MaterialsPresenter
import sec.hungn1.ccqlvt.presenter.contract.MaterialsContract
import sec.hungn1.ccqlvt.ui.adapter.MaterialAdapter
import sec.hungn1.ccqlvt.util.Event

/**
 * Created by hung.nq1 on 8/8/2017.
 */
class MaterialsFragment : BaseFragment<MaterialsPresenter, MaterialsContract.View>(), MaterialsContract.View {

    lateinit var mAdapter: MaterialAdapter
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

    override fun updateDataToView(data: ArrayList<Material>) {
        mAdapter.updateData(data)
    }

    override fun getLayoutId(): Int = R.layout.fragment_materials

    override fun initEventAndData() {
        mLayoutManager = LinearLayoutManager(mContext);
        mAdapter = MaterialAdapter()
        material_list_view.layoutManager = mLayoutManager
        material_list_view.adapter = mAdapter
        mPresenter.updateData()
    }

    override fun onMessageEvent(event: Event) {
        when (event.key) {
            Constants.EVENT_MATERIAL_UPDATEED -> {
                mPresenter.updateData()
            }
            Constants.EVENT_MATERIAL_REMOVE -> {
                if (event.data is Material) {
                    mPresenter.deleteMaterial(event.data as Material)
                }
            }
        }
    }

}