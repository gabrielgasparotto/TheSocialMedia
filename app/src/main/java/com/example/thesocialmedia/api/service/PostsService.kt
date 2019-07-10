package com.example.thesocialmedia.api.service

import com.example.thesocialmedia.model.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {

    @GET("posts")
    fun allPosts(@Query("userId")userId: Long): Call<ArrayList<Posts>>

}