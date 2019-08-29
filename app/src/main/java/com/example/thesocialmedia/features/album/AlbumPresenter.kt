package com.example.thesocialmedia.features.album

import android.content.Context
import android.os.Bundle
import com.example.thesocialmedia.api.call.AlbumCall
import com.example.thesocialmedia.api.events.AlbumEvent
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.util.UsuarioUtils
import org.greenrobot.eventbus.Subscribe

class AlbumPresenter(albumUserView: AlbumContract.AlbumUserView, override var context: Context) :
    AlbumContract.AlbumBusiness(albumUserView) {

    override fun aoIniciar(context: Context, configurarEventBus: Boolean) {
        super.aoIniciar(context, configurarEventBus)
        consultaAlbums()
        preencheItens()
    }

    override fun aoFinalizar() {
        super.aoFinalizar()
        if (AlbumCall.call.isExecuted) {
            AlbumCall.call.cancel()
        }
    }

    override fun aoClicarNoAlbum(album: Album) {
        val bundle = Bundle()
        bundle.putSerializable("album", album)

        albumUserView.irParaGaleria(bundle)
    }

    fun consultaAlbums() {
        AlbumCall.listaAlbums(UsuarioUtils.usuario)
    }

    fun preencheItens() {
        albumUserView.preencherToolbar(
            UsuarioUtils.usuario.name,
            UsuarioUtils.usuario.email,
            UsuarioUtils.usuario.phone,
            UsuarioUtils.usuario.company.name
        )
    }

    @Subscribe
    fun onEvent(albumEvent: AlbumEvent) {
        if (albumEvent.erro != null) {
            albumUserView.exibeSnackbar(albumEvent.erro.message ?: "Erro desconhecido")
        } else {
            albumUserView.configuraRecycler(albumEvent.albums)
        }
    }
}