package com.loginov.hospital.api.ward

import com.loginov.hospital.api.CallbackImpl
import com.loginov.hospital.api.RetrofitClient
import com.loginov.hospital.api.diagnosis.DiagnosisService
import com.loginov.hospital.api.person.PersonApi
import com.loginov.hospital.model.Ward
import okhttp3.ResponseBody

object WardApi {
    private val tag = WardApi::class.java.simpleName
    private val service = RetrofitClient.createService(WardService::class.java)

    fun getAllWards(onComplete: (List<Ward>) -> Unit) {
        service.getAllWards().enqueue(CallbackImpl<List<Ward>>(tag + "__all") { res, _ ->
            onComplete(res ?: mutableListOf())
        })
    }

    fun postWard(ward: Ward, onComplete: (Ward?) -> Unit) {
        service.createWard(ward).enqueue(CallbackImpl<Ward>(tag + "__post") { res, _ ->
            onComplete(res)
        })
    }
}