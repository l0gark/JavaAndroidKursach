package com.loginov.hospital.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val DEFAUlT_TAG = "RetrofitCallback"
private const val PREFIX = "RETROFIT__"
typealias CompleteHandler<T> = (T?, String?) -> Unit

class CallbackImpl<T>(private val tag: String = DEFAUlT_TAG, private val handler: CompleteHandler<T>?) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful && response.body() != null) {
            Log.w(PREFIX.plus(tag), "Response: code = ${response.code()}, msg = ${response.message()}")
            return run(response.body(), null)
        }
        Log.w(PREFIX.plus(tag), "Error: code = ${response.code()}, body = ${response.errorBody().toString()}")
        run(null, response.errorBody().toString())
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.w(PREFIX.plus(tag),  "Callback onFailure ${t.localizedMessage}",  t)
        run(null, t.localizedMessage)
    }

    private fun run(response: T?, e: String?) {
        handler?.let {
            it(response, e)
        }
    }
}