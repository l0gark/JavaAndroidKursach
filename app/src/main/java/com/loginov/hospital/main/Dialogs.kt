package com.loginov.hospital.main

import android.app.Dialog
import android.text.TextUtils
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.loginov.hospital.R
import com.loginov.hospital.model.Diagnosis
import com.loginov.hospital.model.Person
import com.loginov.hospital.model.PersonDto
import com.loginov.hospital.model.Ward
import kotlinx.android.synthetic.main.dialog_create_diagnosis.*
import kotlinx.android.synthetic.main.dialog_create_person.*
import kotlinx.android.synthetic.main.dialog_create_ward.*

fun Fragment.showCreateWardDialog(onComplete: (Ward) -> Unit) {
    Dialog(requireContext(), android.R.style.Theme_Translucent).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_create_ward)

        dlg_ward__ok.setOnClickListener {
            if (!TextUtils.isEmpty(dlg_ward__name.text)
                && !TextUtils.isEmpty(dlg_ward__count.text)
                && TextUtils.isDigitsOnly(dlg_ward__count.text)
            ) {
                val wardName = dlg_ward__name.text.toString()
                val maxCount = Integer.parseInt(dlg_ward__count.text.toString())
                onComplete(Ward(name = wardName, maxCount = maxCount))
                dismiss()
            } else {
                Toast.makeText(requireContext(), R.string.wrong_data, Toast.LENGTH_LONG).show()
            }
        }
        dlg_ward__back.setOnClickListener {
            dismiss()
        }
    }.show()
}


fun Fragment.showCreateDiagnosisDialog(onComplete: (Diagnosis) -> Unit) {
    Dialog(requireContext(), android.R.style.Theme_Translucent).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_create_diagnosis)

        dlg_diagnosis__ok.setOnClickListener {
            if (!TextUtils.isEmpty(dlg_diagnosis__name.text)) {
                val diagnosisName = dlg_diagnosis__name.text.toString()
                onComplete(Diagnosis(-1, diagnosisName, -1))
                dismiss()
            } else {
                Toast.makeText(requireContext(), R.string.wrong_data, Toast.LENGTH_LONG).show()
            }
        }
        dlg_diagnosis__back.setOnClickListener {
            dismiss()
        }
    }.show()
}