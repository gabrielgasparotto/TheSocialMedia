package com.example.thesocialmedia.api

import com.example.thesocialmedia.api.service.AlbumService
import com.example.thesocialmedia.api.service.GaleriaService
import com.example.thesocialmedia.api.service.PostsService
import com.example.thesocialmedia.api.service.UsersService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer{

    private val build = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun usersService() = build.create(UsersService::class.java)

    fun postsService() = build.create(PostsService::class.java)

    fun albumService() = build.create(AlbumService::class.java)

    fun galeriaService() = build.create(GaleriaService::class.java)
}