package sec.hungn1.ccqlvt.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_view_human.view.*
import sec.hungn1.ccqlvt.core.database.entities.Human

/**
 * Created by hung.nq1 on 8/8/2017.
 */

class HumanAdapter(
        protected var mlayout: Int,
        protected var mData: List<Human>
) : RecyclerView.Adapter<HumanAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return mData.size
    }

    fun getItem(position: Int): Human {
        return mData.get(position);
    }

    fun updateData(data: List<Human>) {
        mData = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder is ViewHolder) {
            val item = getItem(position)
            item?.let {
                with(holder) {
                    itemView.tvHumanName.text = item.name
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(mlayout, parent, false)
        return ViewHolder(view);
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mHumanNameView: TextView = itemView.tvHumanName
    }
}