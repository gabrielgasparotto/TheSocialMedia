package com.example.thesocialmedia.api.call

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.example.thesocialmedia.api.configuration.RetrofitInitializer
import com.example.thesocialmedia.api.events.PhotosEvent
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.model.Photos
import com.example.thesocialmedia.util.SnackbarUtils
import org.greenrobot.eventbus.EventBus
import retrofit2.Call

object GaleriaCall{

    lateinit var call: Call<ArrayList<Photos>>

    fun listaGaleria(album: Album, context: Context, recyclerView: RecyclerView){
        val call = RetrofitInitializer().galeriaService().allPhotosByAlbumId(album.id)
        call.enqueue(callback({ response ->

            val photos = response.body()
            if(photos.isNullOrEmpty()){
                SnackbarUtils()
                    .showSnack("Nothing to show"
                        , recyclerView, context)
            }else{
                EventBus.getDefault().post(PhotosEvent(photos))
            }

        },{ throwable ->
            EventBus.getDefault().post(PhotosEvent(erro = throwable))
        }))
    }
}