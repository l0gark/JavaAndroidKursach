package com.loginov.hospital.api.diagnosis

import com.loginov.hospital.model.Diagnosis
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DiagnosisService {
    @GET("/diagnosis/all")
    fun getAllDiagnosis(): Call<List<Diagnosis>>

    @DELETE("/diagnosis/{id}")
    fun delete(@Path("id") id : Long): Call<ResponseBody>

    @POST("/diagnosis")
    fun post(@Body diagnosis: Diagnosis): Call<Diagnosis>
}