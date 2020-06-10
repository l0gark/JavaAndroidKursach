package com.loginov.hospital.api

import com.google.gson.GsonBuilder
import com.loginov.hospital.UserUtils
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://85.236.188.227:8080/"

object RetrofitClient {

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .authenticator { _, response ->
            val request = response.request()
            return@authenticator request
                .newBuilder()
                .header("Authorization", UserUtils.credentials)
                .build()
        }.build()


    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}
