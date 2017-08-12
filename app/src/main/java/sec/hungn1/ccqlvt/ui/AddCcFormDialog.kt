package sec.hungn1.ccqlvt.ui

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.dialog_add_cc_form.*
import org.greenrobot.eventbus.EventBus
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.App
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.dao.CcRecordDao
import sec.hungn1.ccqlvt.core.database.dao.HumanDao
import sec.hungn1.ccqlvt.core.database.entities.CcRecord
import sec.hungn1.ccqlvt.core.presentation.BaseDialog
import sec.hungn1.ccqlvt.ui.adapter.AddCcAdapter
import sec.hungn1.ccqlvt.util.Event
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by hung.nq1 on 8/9/2017.
 */
class AddCcFormDialog(context: Context) : BaseDialog(context) {

    @Inject
    lateinit var mRecordDao: CcRecordDao
    @Inject
    lateinit var mHumanDao: HumanDao

    lateinit var mAdapter: AddCcAdapter
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    var mContext = context
    var myCalendar = Calendar.getInstance()
    val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, monthOfYear)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        setNonHour()
        updateLabel()
        updateData()
    }

    override fun initInject() {
        App.mAppComponent.inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.dialog_add_cc_form;
    }

    override fun initViewAndData() {
        setNonHour()
        updateLabel()
        val data: ArrayList<CcRecord> = ArrayList()
        mAdapter = AddCcAdapter(data);
        mLayoutManager = LinearLayoutManager(mContext);
        listAddCC.layoutManager = mLayoutManager
        listAddCC.adapter = mAdapter
        updateData()
        btnAdd.setOnClickListener { view ->
            run {
                addRecord()
                dismiss()
            }
        }
        btnCancel.setOnClickListener { view ->
            run {
                dismiss()
            }
        }
        add_cc_date.setOnClickListener { view ->
            run {
                DatePickerDialog(mContext, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }
    }

    fun addRecord() {
        for (record in mAdapter.mData) {
            record.time = myCalendar.timeInMillis
            try {
                if (record.id == null) {
                    mRecordDao.insertRecord(record)
                } else {
                    mRecordDao.updateRecord(record)
                }
            } catch (e: Exception) {
                Log.d("hung.nq1", e.message.toString())
            }
        }
        EventBus.getDefault().post(Event(Constants.EVENT_RECORD_CHANGED))
    }

    private fun updateLabel() {
        val myFormat = "dd/MM/yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        add_cc_date.setText(sdf.format(myCalendar.time))
    }

    fun setNonHour() {
        myCalendar.set(Calendar.HOUR, 0)
        myCalendar.set(Calendar.MINUTE, 0)
        myCalendar.set(Calendar.SECOND, 0)
        myCalendar.set(Calendar.MILLISECOND, 0)
    }

    fun updateData() {
        val record: List<CcRecord> = mRecordDao.getRecordByTime(myCalendar.timeInMillis)
        val data: ArrayList<CcRecord> = ArrayList()
        val lstHuman = mHumanDao.getAllHuman()
        for (human in lstHuman) {
            var ccRecord = CcRecord()
            ccRecord.hid = human.id
            ccRecord.humanName = human.name
            for (r in record) {
                if (human.id == r.hid) {
                    ccRecord = r
                    break
                }
            }
            data.add(ccRecord)
        }
        mAdapter.updateData(data)
    }
}