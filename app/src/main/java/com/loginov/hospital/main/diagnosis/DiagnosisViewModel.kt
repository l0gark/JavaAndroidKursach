package com.loginov.hospital.main.diagnosis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loginov.hospital.MessageViewModel
import com.loginov.hospital.api.diagnosis.DiagnosisApi
import com.loginov.hospital.model.Diagnosis

open class DiagnosisViewModel : ViewModel() {
    private lateinit var diagnosisData: MutableLiveData<List<Diagnosis>>

    fun getDiagnosis(): LiveData<List<Diagnosis>> {
        if (!this::diagnosisData.isInitialized) {
            diagnosisData = MutableLiveData()
            loadDiagnosis()
        }
        return diagnosisData
    }

    fun loadDiagnosis() {
        DiagnosisApi.getAllDiagnsis {
            diagnosisData.postValue(it)
        }
    }

    fun deleteDiagnosis(id: Long) {
        DiagnosisApi.delete(id) { isSuccessful ->
            if (isSuccessful) {
                val diagnoses = diagnosisData.value ?: mutableListOf()
                diagnosisData.postValue(diagnoses.filter { diagnosis -> diagnosis.id != id })
            }
        }
    }

    fun postDiagnosis(diagnosis: Diagnosis) {
        DiagnosisApi.post(diagnosis) { res ->
            if (res != null) {
                val diagnoses = mutableListOf<Diagnosis>()
                diagnoses.addAll(diagnosisData.value ?: listOf())
                diagnoses.add(res)
                diagnosisData.postValue(diagnoses)
            }
        }
    }
}