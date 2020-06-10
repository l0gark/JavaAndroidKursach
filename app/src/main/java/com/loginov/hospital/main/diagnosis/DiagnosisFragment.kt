package com.loginov.hospital.main.diagnosis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.loginov.hospital.R
import com.loginov.hospital.UserUtils
import com.loginov.hospital.main.diagnosis.adapter.DiagnosisAdapter
import com.loginov.hospital.main.showCreateDiagnosisDialog
import kotlinx.android.synthetic.main.fragment_diagnosis_list.*

class DiagnosisFragment : Fragment() {

    private lateinit var viewModel: DiagnosisViewModel
    private lateinit var adapter: DiagnosisAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[DiagnosisViewModel::class.java]
        viewModel.getDiagnosis().observe(viewLifecycleOwner, Observer {
            adapter.bindData(it)
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_diagnosis_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DiagnosisAdapter {
            viewModel.deleteDiagnosis(it)
        }
        frg_diagnosis_list__recycler.adapter = adapter

        if (!UserUtils.isAdmin) {
            frg_diagnosis_list__add.hide()
        }
        frg_diagnosis_list__add.setOnClickListener {
            showCreateDiagnosisDialog {
                viewModel.postDiagnosis(it)
            }
        }
    }
}
