package sec.hungn1.ccqlvt.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_view_addcc_form.view.*
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.database.entities.CcRecord

/**
 * Created by hung.nq1 on 8/9/2017.
 */
class AddCcAdapter(data: ArrayList<CcRecord>) : RecyclerView.Adapter<AddCcAdapter.ViewHolder>() {

    var mData = data;
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder is ViewHolder) {
            val item = getItem(position)
            item?.let {
                with(holder) {
                    holder.hName.text = item.humanName
                    holder.rgroup.setOnCheckedChangeListener { radioGroup, i ->
                        run {
                            val rd: RadioButton = radioGroup.findViewById(i)
                            item.value = rd.text.toString().toFloat()
                        }
                    }
                    when (item.value) {
                        0F -> holder.rgroup.check(R.id.rb_btn0)
                        0.5F -> holder.rgroup.check(R.id.rb_btn1)
                        1F -> holder.rgroup.check(R.id.rb_btn2)
                    }
                }

            }
        }
    }

    fun updateData(data: ArrayList<CcRecord>) {
        mData.clear()
        mData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.item_view_addcc_form, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun getItem(position: Int): CcRecord {
        return mData.get(position);
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rgroup: RadioGroup = itemView.rg_add_cc
        var hName: TextView = itemView.tv_hname
    }
}