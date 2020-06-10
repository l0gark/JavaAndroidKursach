package com.loginov.hospital.main.person.create

import android.content.Context
import com.jaredrummler.materialspinner.MaterialSpinnerBaseAdapter
import com.loginov.hospital.model.Diagnosis

class DiagnosisSpinnerAdapter(
    context: Context,
    private val diagnoses: MutableList<Diagnosis> = mutableListOf()
) : MaterialSpinnerBaseAdapter<Any>(context) {

    override fun getCount(): Int {
        return diagnoses.size
    }

    fun bindData(diagnoses: List<Diagnosis>) {
        this.diagnoses.clear()
        this.diagnoses.addAll(diagnoses)
        this.diagnoses.add(0, Diagnosis(1, ""))
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): String {
        if (position !in 0..count) {
            return ""
        }
        return diagnoses[position].name
    }

    override fun get(position: Int) = diagnoses[position].name

    override fun getItems(): List<Diagnosis> = diagnoses
}