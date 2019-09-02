package com.example.thesocialmedia.api.call

import com.example.thesocialmedia.api.configuration.RetrofitInitializer
import com.example.thesocialmedia.api.events.AlbumEvent
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.model.Users
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import java.lang.Exception

object AlbumCall {

    lateinit var call: Call<ArrayList<Album>>

    fun listaAlbums(usuario: Users){
        call = RetrofitInitializer().albumService().allAlbumsByUserId(usuario.id)
        call.enqueue(callback({ response ->

            val albums = response.body()
            if(albums.isNullOrEmpty()){
                EventBus.getDefault().post(AlbumEvent(erro = Exception("Nothing to show")))
            }else{
                EventBus.getDefault().post(AlbumEvent(albums))
            }

        },{ throwable ->
            EventBus.getDefault().post(AlbumEvent(erro = throwable))
        }))
    }

}