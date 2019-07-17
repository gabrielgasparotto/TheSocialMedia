package com.example.thesocialmedia.view.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.thesocialmedia.R
import com.example.thesocialmedia.model.Photos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_fragment_photo.view.*


class DialogPhotoFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_photo, container, false)


        val foto = arguments!!.getSerializable("foto") as Photos
        view.tituloDialogGaleria.text = foto.title
        Picasso
            .get()
            .load(foto.url)
            .into(view.imagemDialogGaleria)

        return view
    }
}
