package com.example.thesocialmedia.features.album

import android.os.Bundle
import com.example.thesocialmedia.api.arch.BaseBusiness
import com.example.thesocialmedia.api.arch.BaseUserView
import com.example.thesocialmedia.model.Album

class AlbumContract {

    interface AlbumUserView : BaseUserView<AlbumBusiness> {
        fun exibeSnackbar(mensagem: String)
        fun configuraRecycler(albums: ArrayList<Album>)
        fun configuraToolbar(titulo: String)
        fun preencherToolbar(nome: String, email: String, telefone: String, company: String)
        fun irParaGaleria(bundle: Bundle)
    }

    abstract class AlbumBusiness(val albumUserView: AlbumUserView) : BaseBusiness {
        abstract fun aoClicarNoAlbum(album: Album)
    }
}