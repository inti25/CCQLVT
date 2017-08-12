package sec.hungn1.ccqlvt.core.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by hung.nq1 on 8/12/2017.
 */
open class RxPresenter<V : BaseView> : BasePresenter<V> {

    var mView: V? = null
    var mCompositeDisposable: CompositeDisposable? = null

    fun addSubscribe(subcription: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(subcription)
    }

    fun unSubscribe() {
        mCompositeDisposable?.dispose()
    }

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
        unSubscribe()
    }

}