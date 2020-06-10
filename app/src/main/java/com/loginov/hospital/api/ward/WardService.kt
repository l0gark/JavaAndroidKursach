package com.loginov.hospital.api.ward

import com.loginov.hospital.model.Ward
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WardService {

    @GET("/ward/all")
    fun getAllWards(): Call<List<Ward>>

    @POST("/ward")
    fun createWard(@Body ward: Ward) : Call<Ward>
}