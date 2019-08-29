package com.example.thesocialmedia.features.album

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thesocialmedia.R
import com.example.thesocialmedia.features.galeria.GaleriaActivity
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.util.SnackbarUtils
import com.example.thesocialmedia.util.adapter.AlbumAdapter
import kotlinx.android.synthetic.main.fragment_album.*

class AlbumFragment : Fragment(), AlbumContract.AlbumUserView {

    override lateinit var business: AlbumContract.AlbumBusiness

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configurarBusiness(AlbumPresenter(this, context!!))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        business.aoFinalizar()
    }

    override fun configuraRecycler(albums: ArrayList<Album>) {
        recyclerAlbums.apply {
            layoutManager = LinearLayoutManager(activity)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = AlbumAdapter(albums, business)
        }
    }

    override fun preencherToolbar(nome: String, email: String, telefone: String, company: String) {
        configuraToolbar(nome)
        nomeCompletoToolbar.text = nome
        emailToolbar.text = email
        telefoneToolbar.text = telefone
        companyToolbar.text = company
    }


    override fun configuraToolbar(titulo: String) {
        val states = arrayOf(intArrayOf(android.R.attr.state_enabled))
        val colors = intArrayOf(Color.TRANSPARENT)
        val myList = ColorStateList(states, colors)

        collapsingAlbum.apply {
            title = titulo
            setCollapsedTitleTextColor(Color.WHITE)
            setExpandedTitleTextColor(myList)
        }
    }

    override fun exibeSnackbar(mensagem: String) {
        SnackbarUtils().showSnack(mensagem, recyclerAlbums, activity!!.applicationContext)
    }

    override fun irParaGaleria(bundle: Bundle) {
        val intent = Intent(context, GaleriaActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
