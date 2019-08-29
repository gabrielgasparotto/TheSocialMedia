package com.example.thesocialmedia.features.album

import android.support.v7.widget.RecyclerView
import com.example.thesocialmedia.api.arch.BaseBusiness
import com.example.thesocialmedia.api.arch.BaseUserView
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.model.Users

class AlbumContract {

    interface AlbumUserView : BaseUserView<AlbumBusiness> {
        fun exibeSnackbar(mensagem: String)
        fun configuraRecycler(albums: ArrayList<Album>)
    }

    abstract class AlbumBusiness(val albumUserView: AlbumUserView): BaseBusiness{
        abstract fun consultaAlbums(usuario: Users)
    }

}