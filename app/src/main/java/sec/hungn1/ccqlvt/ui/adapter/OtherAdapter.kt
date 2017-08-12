package sec.hungn1.ccqlvt.ui.adapter

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.footer_cc.view.*
import kotlinx.android.synthetic.main.item_view_other.view.*
import org.greenrobot.eventbus.EventBus
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.application.Constants
import sec.hungn1.ccqlvt.core.database.entities.Other
import sec.hungn1.ccqlvt.ui.AddOtherDialog
import sec.hungn1.ccqlvt.util.Event
import sec.hungn1.ccqlvt.util.Utils

/**
 * Created by hung.nq1 on 8/10/2017.
 */
class OtherAdapter() : RecyclerView.Adapter<OtherAdapter.ViewHolder>() {

    var mData = ArrayList<Other>()

    companion object {
        val FOOTER_VIEW = 1
        val NORMAL_VIEW = 0
    }

    override fun getItemViewType(position: Int): Int {
        if (position == mData.size)
            return FOOTER_VIEW
        return NORMAL_VIEW
    }

    override fun getItemCount(): Int {
        return mData.size + 1
    }

    fun clearData() {
        Log.d("hung.nq1", "clear Data")
        mData.clear()
        notifyDataSetChanged()
    }

    fun addItem(other: Other) {
        mData.add(other)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Other {
        return mData.get(position);
    }

    fun updateData(data: ArrayList<Other>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<Other> = mData

    fun getTotal(): Long {
        var total: Long = 0
        for (material in mData) {
            total += material.total
        }
        return total
    }

    fun getPayedTotal(): Long {
        var total: Long = 0
        for (material in mData) {
            if (material.isPayed) {
                total += material.total
            }
        }
        return total
    }

    fun getNotPayTotal(): Long {
        var total: Long = 0
        for (material in mData) {
            if (!material.isPayed) {
                total += material.total
            }
        }
        return total
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        if (viewType == FOOTER_VIEW) {
            val inflater = LayoutInflater.from(parent?.context)
            val view = inflater.inflate(R.layout.footer_cc, parent, false)
            return FooterHolder(view);
        } else {
            val inflater = LayoutInflater.from(parent?.context)
            val view = inflater.inflate(R.layout.item_view_other, parent, false)
            return NormalHolder(view);
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder is NormalHolder) {
            var item = getItem(position)
            item.let {
                with(holder) {
                    if (item.id == null) {
                        holder.mContainer.visibility = View.GONE
                        holder.mHeaderView.visibility = View.VISIBLE
                        holder.mHeaderTitle.text = Utils.converTimeStampToDateString(item.time)
                    } else {
                        holder.mContainer.visibility = View.VISIBLE
                        holder.mHeaderView.visibility = View.GONE
                        holder.mTitle.text = item.title.toString()
                        holder.mPrice.text = item.total.toString()
                        if (item.isPayed) {
                            holder.mPrice.setTextColor(Color.GREEN)
                            holder.mPrice.paintFlags = holder.mPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                        }
                        holder.mContainer.isLongClickable = true
                        holder.mContainer.setOnLongClickListener { view: View ->
                            android.support.v7.app.AlertDialog.Builder(view.context)
                                    .setTitle("Hành động")
                                    .setPositiveButton("Xóa", { dialog: DialogInterface, i: Int ->
                                        EventBus.getDefault().post(Event(Constants.EVENT_OTHER_REMOVE, item))
                                        dialog.dismiss()
                                    })
                                    .setNegativeButton("Sửa", { dialog: DialogInterface?, i: Int ->
                                        AddOtherDialog(view.context, item).show()
                                        dialog?.dismiss()
                                    })
                                    .show()
                            true
                        }
                    }
                }
            }
        } else if (holder is FooterHolder) {
            holder.mValue.text = getTotal().toString()
            holder.mPayedValue.text = getPayedTotal().toString()
            holder.mNotPayValue.text = getNotPayTotal().toString()
        }
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    class NormalHolder(itemView: View) : ViewHolder(itemView) {
        var mHeaderView: View = itemView.header
        var mHeaderTitle: TextView = itemView.header_title
        var mContainer: View = itemView.container
        var mTitle: TextView = itemView.title
        var mPrice: TextView = itemView.price
    }

    class FooterHolder(itemView: View) : ViewHolder(itemView) {
        var mValue: TextView = itemView.tvtcvl
        var mPayedValue: TextView = itemView.payedvl
        var mNotPayValue: TextView = itemView.notpayvl
    }
}