package com.example.thesocialmedia.features.photodetail

import android.os.Bundle
import android.widget.ImageView
import com.example.thesocialmedia.api.arch.BaseBusiness
import com.example.thesocialmedia.api.arch.BaseUserView

interface PhotoDetailContract {
    interface PhotoDetailUserView : BaseUserView<PhotoDetailBusiness> {
        fun preencherDetalhesFoto(titulo: String)
        fun obterImageView(): ImageView?
    }

    abstract class PhotoDetailBusiness(val photoDetailUserView: PhotoDetailUserView) : BaseBusiness {
        abstract fun configurarFoto(bundle: Bundle)
        abstract fun carregarFotoNaImagem()
    }
}