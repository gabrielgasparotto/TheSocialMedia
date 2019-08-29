package com.example.thesocialmedia.features.album

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.thesocialmedia.R
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.util.adapter.AlbumAdapter
import kotlinx.android.synthetic.main.fragment_album.*
import android.content.res.ColorStateList
import android.support.v7.widget.RecyclerView
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.SnackbarUtils
import com.example.thesocialmedia.util.UsuarioUtils

class AlbumFragment : Fragment(), AlbumContract.AlbumUserView {

    override lateinit var business: AlbumContract.AlbumBusiness

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuario = UsuarioUtils.usuario
        configurarBusiness(AlbumPresenter(this))
        business.consultaAlbums(usuario)
        configuraToolbar(usuario)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        business.aoFinalizar()
    }

    override fun configuraRecycler(albums: ArrayList<Album>) {
        recyclerAlbums.apply {
            layoutManager = LinearLayoutManager(activity)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = AlbumAdapter(albums)
        }
    }

    private fun configuraToolbar(usuario: Users) {
        val states = arrayOf(intArrayOf(android.R.attr.state_enabled))
        val colors = intArrayOf(Color.TRANSPARENT)
        val myList = ColorStateList(states, colors)

        collapsingAlbum.apply {
            title = usuario.name
            setCollapsedTitleTextColor(Color.WHITE)
            setExpandedTitleTextColor(myList)
        }

        nomeCompletoToolbar.text = usuario.name
        emailToolbar.text = usuario.email
        telefoneToolbar.text = usuario.phone
        companyToolbar.text = usuario.company.name
    }

    override fun exibeSnackbar(mensagem: String) {
        SnackbarUtils().showSnack(mensagem, recyclerAlbums, activity!!.applicationContext)
    }
}
