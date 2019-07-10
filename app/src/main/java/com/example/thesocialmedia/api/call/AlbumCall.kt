package com.example.thesocialmedia.api.call

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.thesocialmedia.api.configuration.RetrofitInitializer
import com.example.thesocialmedia.api.events.AlbumEvent
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.SnackbarUtils
import org.greenrobot.eventbus.EventBus
import retrofit2.Call

object AlbumCall {

    lateinit var call: Call<ArrayList<Album>>

    fun listaAlbums(usuario: Users, context: Context, recyclerView: RecyclerView){
        call = RetrofitInitializer().albumService().allAlbumsByUserId(usuario.id)
        call.enqueue(callback({ response ->

            val albums = response.body()
            if(albums.isNullOrEmpty()){
                SnackbarUtils()
                    .showSnack("Nothing to show"
                        , recyclerView, context)
            }else{
                EventBus.getDefault().post(AlbumEvent(albums))
            }

        },{ throwable ->
            Log.v("recyclerCancel", throwable.message)
        }))
    }

}