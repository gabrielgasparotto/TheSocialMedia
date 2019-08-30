package com.example.thesocialmedia.features.galeria

import android.content.Context
import android.content.Intent
import com.example.thesocialmedia.api.call.GaleriaCall
import com.example.thesocialmedia.api.events.PhotosEvent
import com.example.thesocialmedia.model.Album
import org.greenrobot.eventbus.Subscribe

class GaleriaPresenter(galeriaUserView: GaleriaContract.GaleriaUserView, override var context: Context, val intent: Intent)
    : GaleriaContract.GaleriaBusiness(galeriaUserView) {

    override fun aoIniciar(context: Context, configurarEventBus: Boolean) {
        super.aoIniciar(context, configurarEventBus)
        val album = configuraAlbum(galeriaUserView.capturaIntent())
        galeriaUserView.configuraToolbar(album.title)
    }

    override fun configuraAlbum(intent: Intent): Album {
        val album = intent.getSerializableExtra("album") as Album
        GaleriaCall.listaGaleria(album)
        return album
    }

    @Subscribe
    fun onEvent(photosEvent: PhotosEvent){
        if(photosEvent.erro != null){
            galeriaUserView.exibeSnackbar(photosEvent.erro.message ?: "Erro Desconheciudo")
        }else{
            galeriaUserView.configuraRecycler(photosEvent.photos)
        }
    }
}