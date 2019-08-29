package com.example.thesocialmedia.features.photodetail

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.thesocialmedia.R
import kotlinx.android.synthetic.main.dialog_fragment_photo.view.*

class PhotoDetailFragment : DialogFragment(), PhotoDetailContract.PhotoDetailUserView {

    override lateinit var business: PhotoDetailContract.PhotoDetailBusiness
    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        root = inflater.inflate(R.layout.dialog_fragment_photo, container, false)

        configurarBusiness(PhotoDetailPresenter(this, context!!))
        business.configurarFoto(arguments!!)
        business.carregarFotoNaImagem()

        return root
    }

    override fun preencherDetalhesFoto(titulo: String) {
        root.tituloDialogGaleria.text = titulo
    }

    override fun obterImageView(): ImageView? = root.imagemDialogGaleria
}
