package sec.hungn1.ccqlvt.core.presentation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle

/**
 * Created by hung.nq1 on 8/11/2017.
 */
abstract class BaseDialog(context: Context) : AlertDialog(context) {
    abstract fun getLayoutId(): Int
    abstract fun initViewAndData()
    abstract fun initInject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initInject()
        initViewAndData()
    }
}