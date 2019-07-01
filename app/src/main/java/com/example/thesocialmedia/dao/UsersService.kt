package com.example.thesocialmedia.dao

import com.example.thesocialmedia.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {

    @GET("users")
    fun loginByUsername(@Query("username")username: String): Call<ArrayList<Users>>

}