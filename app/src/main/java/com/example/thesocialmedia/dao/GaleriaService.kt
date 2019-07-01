package com.example.thesocialmedia.dao

import com.example.thesocialmedia.model.Photos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GaleriaService {

    @GET("photos")
    fun allPhotosByAlbumId(@Query("albumId")albumId: Long): Call<ArrayList<Photos>>


}