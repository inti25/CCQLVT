package sec.hungn1.ccqlvt.ui

import android.content.DialogInterface
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import me.yokeyword.fragmentation.SupportFragment
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.entities.Material
import sec.hungn1.ccqlvt.core.database.entities.Other
import sec.hungn1.ccqlvt.core.presentation.BaseActivity
import sec.hungn1.ccqlvt.presenter.MainPresenter
import sec.hungn1.ccqlvt.presenter.contract.MainContract
import sec.hungn1.ccqlvt.ui.dialog.AddCcFormDialog
import sec.hungn1.ccqlvt.ui.dialog.AddMaterialDialog
import sec.hungn1.ccqlvt.ui.dialog.AddOtherDialog
import sec.hungn1.ccqlvt.ui.dialog.HumanFillterDialog
import sec.hungn1.ccqlvt.util.SharedPreferenceUtil

class MainActivity() : BaseActivity<MainPresenter, MainContract.View>(), NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    var mCcFragment: CcFragment = CcFragment()
    var mMaterialsFragment: MaterialsFragment = MaterialsFragment()
    var mEtcFragment: EtcFragment = EtcFragment()
    var mCurrentFragment = Constants.CC_PAGE
    var mShowFragment = Constants.CC_PAGE

    override fun initInject() {
        component.inject(this);
    }

    override fun getLayout(): Int = R.layout.activity_main

    override fun initEventAndData() {
        setSupportActionBar(toolbar)
        loadMultipleRootFragment(R.id.rootFragment, 0, mCcFragment, mMaterialsFragment, mEtcFragment)
        mShowFragment = SharedPreferenceUtil.getCurrentPage()
        showHideFragment(getTargetFragment(mShowFragment), getTargetFragment(mCurrentFragment))
        mCurrentFragment = mShowFragment
        fabReport.setOnClickListener { _ ->
            when (mCurrentFragment) {
                Constants.CC_PAGE -> AddCcFormDialog(this).show()
                Constants.MATERIALS_PAGE -> AddMaterialDialog(this, Material()).show()
                Constants.ETC_PAGE -> AddOtherDialog(this, Other()).show()
            }
            floatingMenu.close(true)
        }
        fabFillter.setOnClickListener { _ ->
            when (mCurrentFragment) {
                Constants.CC_PAGE -> HumanFillterDialog(this).show()
            }
            floatingMenu.close(true)
        }
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        updateTitle(mCurrentFragment)
    }

    override fun onResume() {
        super.onResume()
        updateTitle(mCurrentFragment)
    }

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressedSupport() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedSupport()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                showDialogInputHuman()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun getTargetFragment(item: Int): SupportFragment {
        when (item) {
            Constants.CC_PAGE -> return mCcFragment
            Constants.MATERIALS_PAGE -> return mMaterialsFragment
            Constants.ETC_PAGE -> return mEtcFragment
        }
        return mCcFragment;
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {
                mShowFragment = Constants.CC_PAGE;
            }
            R.id.nav_gallery -> {
                mShowFragment = Constants.MATERIALS_PAGE;
            }
            R.id.nav_slideshow -> {
                mShowFragment = Constants.ETC_PAGE;
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        SharedPreferenceUtil.setCurrentPage(mShowFragment)
        showHideFragment(getTargetFragment(mShowFragment), getTargetFragment(mCurrentFragment))
        mCurrentFragment = mShowFragment
        updateTitle(mCurrentFragment)
        return true
    }

    fun updateTitle(page: Int) {
        when (page) {
            Constants.CC_PAGE -> toolbar.setTitle("Bảng chấm công")
            Constants.MATERIALS_PAGE -> toolbar.setTitle("Nguyên vật liệu")
            Constants.ETC_PAGE -> return toolbar.setTitle("Khoản phụ khác")
        }
    }

    fun showDialogInputHuman() {
        val textView: EditText = EditText(this)

        AlertDialog.Builder(this)
                .setTitle("Thêm thợ")
                .setMessage("Nhập tên thợ rồi bấm thêm")
                .setView(textView)
                .setPositiveButton("Thêm", { _: DialogInterface, _: Int ->
                    mPresenter.addHuman(textView.text.toString())
                })
                .setNegativeButton("Hủy", { dialog: DialogInterface?, _: Int -> dialog?.dismiss() })
                .show()
    }
}
