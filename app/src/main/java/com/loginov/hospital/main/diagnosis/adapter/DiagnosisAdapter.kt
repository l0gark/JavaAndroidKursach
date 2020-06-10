package com.loginov.hospital.main.diagnosis.adapter

import android.view.View
import com.loginov.hospital.R
import com.loginov.hospital.main.RecyclerAdapter
import com.loginov.hospital.model.Diagnosis
import kotlinx.android.synthetic.main.item_diagnosis.view.*

class DiagnosisAdapter(private val onRemove: (Long) -> Unit) : RecyclerAdapter<Diagnosis>() {
    override fun itemLayoutId() = R.layout.item_diagnosis

    override fun bind(itemView: View, item: Diagnosis) {
        with(itemView) {
            item_diagnosis__name.text = item.name
            item_diagnosis__count.text = context.getString(R.string.diagnosis_count, item.count)
            if (item.count == 0) {
                item_diagnosis__bin.visibility = View.VISIBLE
                item_diagnosis__bin.setOnClickListener {
                    onRemove(item.id)
                }
            } else {
                item_diagnosis__bin.visibility = View.GONE
            }
        }
    }
}