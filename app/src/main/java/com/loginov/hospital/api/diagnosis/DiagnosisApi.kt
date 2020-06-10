package com.loginov.hospital.api.diagnosis

import com.loginov.hospital.api.CallbackImpl
import com.loginov.hospital.api.RetrofitClient
import com.loginov.hospital.api.person.PersonApi
import com.loginov.hospital.model.Diagnosis
import com.loginov.hospital.model.Ward
import okhttp3.ResponseBody

object DiagnosisApi {
    private val tag = DiagnosisApi::class.java.simpleName
    private val service = RetrofitClient.createService(DiagnosisService::class.java)

    fun getAllDiagnsis(onComplete: (List<Diagnosis>) -> Unit) {
        service.getAllDiagnosis().enqueue(CallbackImpl<List<Diagnosis>>(tag + "__all") { res, _ ->
            onComplete(res ?: mutableListOf())
        })
    }

    fun delete(id: Long, onComplete: (Boolean) -> Unit) {
        service.delete(id).enqueue(CallbackImpl<ResponseBody>(tag + "__delete") { _, e ->
            onComplete(e == null)
        })
    }

    fun post(diagnosis: Diagnosis, onComplete: (Diagnosis?) -> Unit) {
        service.post(diagnosis).enqueue(CallbackImpl<Diagnosis>(tag + "__post") { res, _ ->
            onComplete(res)
        })
    }
}