package sec.hungn1.ccqlvt.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_add_other.*
import org.greenrobot.eventbus.EventBus
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.App
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.dao.OtherDao
import sec.hungn1.ccqlvt.core.database.entities.Other
import sec.hungn1.ccqlvt.core.presentation.BaseDialog
import sec.hungn1.ccqlvt.util.Event
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by hung.nq1 on 8/11/2017.
 */
class AddOtherDialog(context: Context, var mOther: Other) : BaseDialog(context) {

    @Inject
    lateinit var mOtherDao: OtherDao
    var mContext = context
    var myCalendar = Calendar.getInstance()
    val mDateListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, monthOfYear)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        setNonHour()
        updateLabel()
    }

    override fun getLayoutId(): Int {
        return R.layout.dialog_add_other
    }

    override fun initViewAndData() {
        setNonHour()
        updateLabel()
        updateData()
        btnAdd.setOnClickListener { _ ->
            run {
                addMaterial()
                dismiss()
            }
        }
        btnCancel.setOnClickListener { _ ->
            run {
                dismiss()
            }
        }
        date.setOnClickListener { _ ->
            run {
                DatePickerDialog(mContext, mDateListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    private fun updateData() {
        if (mOther.id != null) {
            title.setText(mOther.title.toString())
            total.setText(mOther.total.toString())
            myCalendar.timeInMillis = mOther.time
            cbIsPayed.isChecked = mOther.isPayed
            updateLabel()
        }
    }

    private fun addMaterial() {
        mOther.time = myCalendar.timeInMillis
        mOther.title = title.text.toString()
        mOther.total = total.text.toString().toLong()
        mOther.isPayed = cbIsPayed.isChecked
        try {
            if (mOther.id == null)
                mOtherDao.insert(mOther)
            else
                mOtherDao.update(mOther)
            EventBus.getDefault().post(Event(Constants.EVENT_OTHER_UPDATEED))
        } catch (e: Exception) {
            Toast.makeText(mContext, e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun initInject() {
        App.mAppComponent.inject(this)
    }

    private fun updateLabel() {
        val myFormat = "dd/MM/yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        date.setText(sdf.format(myCalendar.time))
    }

    fun setNonHour() {
        myCalendar.set(Calendar.HOUR, 0)
        myCalendar.set(Calendar.MINUTE, 0)
        myCalendar.set(Calendar.SECOND, 0)
        myCalendar.set(Calendar.MILLISECOND, 0)
    }
}