package com.example.thesocialmedia.features.album

import android.content.Context
import android.widget.Toast
import com.example.thesocialmedia.api.call.AlbumCall
import com.example.thesocialmedia.api.events.AlbumEvent
import com.example.thesocialmedia.model.Users
import org.greenrobot.eventbus.Subscribe

class AlbumPresenter(albumUserView: AlbumContract.AlbumUserView, override var context: Context)
    : AlbumContract.AlbumBusiness(albumUserView){

    override fun aoFinalizar() {
        super.aoFinalizar()
        if (AlbumCall.call.isExecuted) {
            AlbumCall.call.cancel()
        }
    }

    override fun consultaAlbums(usuario: Users) {
        AlbumCall.listaAlbums(usuario)
    }

    @Subscribe
    fun onEvent(albumEvent: AlbumEvent){
        if(albumEvent.erro != null){
            albumUserView.exibeSnackbar(albumEvent.erro.message ?: "Erro desconhecido")
        }else{
            albumUserView.configuraRecycler(albumEvent.albums)
        }
    }

    private fun tratarErro(erro: Throwable) {
        Toast.makeText(context, erro.message, Toast.LENGTH_LONG).show()
    }

}