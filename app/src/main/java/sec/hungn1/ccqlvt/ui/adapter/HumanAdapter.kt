package sec.hungn1.ccqlvt.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_view_human.view.*
import sec.hungn1.ccqlvt.R
import sec.hungn1.ccqlvt.core.database.entities.Human

/**
 * Created by hung.nq1 on 8/8/2017.
 */

class HumanAdapter(var mListener: OnItemClickListener? = null
) : RecyclerView.Adapter<HumanAdapter.ViewHolder>() {

    protected var mData: ArrayList<Human> = ArrayList()

    override fun getItemCount(): Int {
        return mData.size
    }

    fun getItem(position: Int): Human {
        return mData.get(position);
    }

    fun updateData(data: ArrayList<Human>) {
        mData.clear()
        val all = Human(null, "Tất cả")
        mData.add(all)
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder is ViewHolder) {
            val item = getItem(position)
            item.let {
                with(holder) {
                    mHumanNameView.text = item.name
                    mHumanNameView.setOnClickListener { _ -> listener?.onItemClick(item) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.item_view_human, parent, false)
        return ViewHolder(view, mListener);
    }

    open class ViewHolder(itemView: View, var listener: OnItemClickListener? = null) : RecyclerView.ViewHolder(itemView) {
        val mHumanNameView: TextView = itemView.tvHumanName
    }

    interface OnItemClickListener {
        fun onItemClick(human: Human)
    }
}