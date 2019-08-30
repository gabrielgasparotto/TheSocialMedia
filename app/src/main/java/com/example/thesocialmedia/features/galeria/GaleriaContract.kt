package com.example.thesocialmedia.features.galeria

import android.content.Intent
import com.example.thesocialmedia.api.arch.BaseBusiness
import com.example.thesocialmedia.api.arch.BaseUserView
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.model.Photos

class GaleriaContract {

    interface GaleriaUserView : BaseUserView<GaleriaBusiness> {
        fun exibeSnackbar(mensagem: String)
        fun configuraRecycler(photos: ArrayList<Photos>)
        fun configuraToolbar(titulo: String)
        fun capturaIntent(): Intent
    }

    abstract class GaleriaBusiness(val galeriaUserView: GaleriaUserView) : BaseBusiness {
        abstract fun configuraAlbum(intent: Intent): Album
    }
}