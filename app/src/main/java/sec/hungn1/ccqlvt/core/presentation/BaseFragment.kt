@file:Suppress("UNCHECKED_CAST")

package sec.hungn1.ccqlvt.core.presentation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_cc.*
import me.yokeyword.fragmentation.SupportFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.App
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.di.component.DaggerFragmentComponent
import sec.hungn1.ccqlvt.core.di.component.FragmentComponent
import sec.hungn1.ccqlvt.core.di.module.FragmentModule
import sec.hungn1.ccqlvt.ui.adapter.HumanAdapter
import sec.hungn1.ccqlvt.util.Event
import javax.inject.Inject

/**
 * Created by hung.nq1 on 8/8/2017.
 */

abstract class BaseFragment<T : BasePresenter<V>, V : BaseView> : SupportFragment(), BaseView {
    @Inject
    protected lateinit var mPresenter: T
    protected var mView: View? = null
    protected var mActivity: Activity? = null
    protected var mContext: Context? = null
    protected var mIsInited = false

    val component: FragmentComponent by lazy {
        DaggerFragmentComponent.builder()
                .appComponent(App.mAppComponent)
                .fragmentModule(FragmentModule(this))
                .build()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: Event) {
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(getLayoutId(), null)
        initInject()
        return mView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this as V)
        if (savedInstanceState == null) {
            if (!isHidden) {
                mIsInited = true
                initEventAndData()
            }
        } else {
            if (isSupportVisible) {
                mIsInited = true
                initEventAndData()
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!mIsInited && !hidden) {
            mIsInited = true
            initEventAndData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    protected abstract fun initInject()
    protected abstract fun getLayoutId(): Int
    protected abstract fun initEventAndData()
}