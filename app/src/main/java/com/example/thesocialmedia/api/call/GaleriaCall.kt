package com.example.thesocialmedia.api.call

import com.example.thesocialmedia.api.configuration.RetrofitInitializer
import com.example.thesocialmedia.api.events.PhotosEvent
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.model.Photos
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import java.lang.Exception

object GaleriaCall{

    lateinit var call: Call<ArrayList<Photos>>

    fun listaGaleria(album: Album){
        val call = RetrofitInitializer().galeriaService().allPhotosByAlbumId(album.id)
        call.enqueue(callback({ response ->

            val photos = response.body()
            if(photos.isNullOrEmpty()){
                EventBus.getDefault().post(PhotosEvent(erro = Exception("Null or Empty")))
            }else{
                EventBus.getDefault().post(PhotosEvent(photos))
            }

        },{ throwable ->
            EventBus.getDefault().post(PhotosEvent(erro = throwable))
        }))
    }
}