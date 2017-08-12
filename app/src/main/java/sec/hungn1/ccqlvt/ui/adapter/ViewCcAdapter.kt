package sec.hungn1.ccqlvt.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_cc_detail.view.*
import kotlinx.android.synthetic.main.item_cc_header.view.*
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.database.entities.RecordItem
import sec.hungn1.ccqlvt.util.Utils

/**
 * Created by hung.nq1 on 8/10/2017.
 */
class ViewCcAdapter(context: Context?, var itemList: ArrayList<RecordItem>) : ArrayAdapter<RecordItem>(context, 0, itemList) {
    var mInflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v: View
        val item = getItem(position)
        if (item.isSection) {
            v = mInflater.inflate(R.layout.item_cc_header, null)
            v.header_title.text = Utils.converTimeStampToDateString(item.time)
        } else {
            v = mInflater.inflate(R.layout.item_cc_detail, null)
            v.humanNam.text = item.humanName
            v.recordValue.text = item.value.toString()
        }
        return v
    }

    fun updateData(data: ArrayList<RecordItem>) {
        itemList.clear()
        itemList.addAll(data)
        notifyDataSetChanged()
    }
}