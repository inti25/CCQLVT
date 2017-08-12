package sec.hungn1.ccqlvt.core.presentation

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import me.yokeyword.fragmentation.SupportActivity
import sec.hungn1.ccqlvt.core.application.App
import sec.hungn1.ccqlvt.core.di.component.ActivityComponent
import sec.hungn1.ccqlvt.core.di.component.DaggerActivityComponent
import sec.hungn1.ccqlvt.core.di.module.ActivityModule
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<T : BasePresenter<V>, V : BaseView> : SupportActivity(), BaseView {

    @Inject
    protected lateinit var mPresenter: T
    protected lateinit var mContext: Activity

    val component: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .appComponent((application as App).component)
                .activityModule(ActivityModule(this))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        mContext = this
        initInject()
        mPresenter.attachView(this as V)
        initEventAndData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    protected abstract fun initInject()
    protected abstract fun getLayout(): Int
    protected abstract fun initEventAndData()

}