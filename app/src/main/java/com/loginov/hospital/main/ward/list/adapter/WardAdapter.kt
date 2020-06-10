package com.loginov.hospital.main.ward.list.adapter

import android.view.View
import com.loginov.hospital.R
import com.loginov.hospital.main.RecyclerAdapter
import com.loginov.hospital.model.Ward
import kotlinx.android.synthetic.main.item_ward.view.*

class WardAdapter(private val onWardClick: (Long) -> Unit) : RecyclerAdapter<Ward>() {
    override fun itemLayoutId() = R.layout.item_ward

    override fun bind(itemView: View, item: Ward) {
        with(itemView) {
            item_ward__name.text = item.name
            item_ward__max_count.text = context.getString(R.string.ward_max_count, item.maxCount)
            item_ward__bin.visibility = View.GONE
            setOnClickListener {
                onWardClick(item.id)
            }
        }
    }
}