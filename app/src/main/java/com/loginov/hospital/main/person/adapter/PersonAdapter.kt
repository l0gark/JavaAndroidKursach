package com.loginov.hospital.main.person.adapter

import android.view.View
import com.loginov.hospital.R
import com.loginov.hospital.main.RecyclerAdapter
import com.loginov.hospital.model.Diagnosis
import com.loginov.hospital.model.Person
import com.loginov.hospital.model.fullName
import kotlinx.android.synthetic.main.item_diagnosis.view.*
import kotlinx.android.synthetic.main.item_person.view.*

class PersonAdapter(private val onRemove: (Long) -> Unit) : RecyclerAdapter<Person>() {
    override fun itemLayoutId() = R.layout.item_person

    override fun bind(itemView: View, item: Person) {
        with(itemView) {
            item_person__name.text = item.fullName()
            item_person__ward.text = context.getString(R.string.person_ward, item.ward.name)
            item_person__diagnosis.text =
                context.getString(R.string.person_diagnosis, item.diagnosis.name)
            item_person__bin.setOnClickListener {
                onRemove(item.id)
            }
        }
    }
}