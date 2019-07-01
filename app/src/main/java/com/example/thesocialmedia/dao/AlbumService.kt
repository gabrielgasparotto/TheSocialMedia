package com.example.thesocialmedia.dao

import com.example.thesocialmedia.model.Album
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {

    @GET("albums")
    fun allAlbumsByUserId(@Query("userId")userId: Long): Call<ArrayList<Album>>

}