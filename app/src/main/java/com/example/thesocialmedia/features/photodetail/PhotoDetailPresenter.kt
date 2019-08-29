package com.example.thesocialmedia.features.photodetail

import android.content.Context
import android.os.Bundle
import com.example.thesocialmedia.app.Constants.Companion.ARGUMENT_PHOTO
import com.example.thesocialmedia.model.Photos
import com.squareup.picasso.Picasso

class PhotoDetailPresenter(
    photoDetailUserView: PhotoDetailContract.PhotoDetailUserView,
    override var context: Context
) :
    PhotoDetailContract.PhotoDetailBusiness(photoDetailUserView) {

    private lateinit var photo: Photos

    override fun configurarFoto(bundle: Bundle) {
        photo = bundle.getSerializable(ARGUMENT_PHOTO) as Photos
        photoDetailUserView.preencherDetalhesFoto(photo.title)
    }

    override fun carregarFotoNaImagem() {
        photoDetailUserView.obterImageView()?.apply {
            Picasso
                .get()
                .load(photo.url)
                .into(this)
        }
    }

    override fun aoIniciar(context: Context, configurarEventBus: Boolean) {
        super.aoIniciar(context, false)
    }
}