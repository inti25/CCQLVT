package sec.hungn1.ccqlvt.core.presentation

/**
 * Created by hung.nq1 on 8/7/2017.
 */

interface BasePresenter<T : BaseView> {

    fun attachView(view: T)

    fun detachView()
}