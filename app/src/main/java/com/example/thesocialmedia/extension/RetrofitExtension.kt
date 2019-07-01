package com.example.thesocialmedia.extension

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> callback(success: ((Response<T>) -> Unit)?, failure: ((throwable: Throwable) -> Unit)? = null): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) { success?.invoke(response) }
        override fun onFailure(call: Call<T>, throwable: Throwable) { failure?.invoke(throwable) }
    }
}