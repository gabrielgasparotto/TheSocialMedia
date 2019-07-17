package com.example.thesocialmedia.view.fragment

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
import android.widget.Toast
import com.example.thesocialmedia.api.call.AlbumCall
import com.example.thesocialmedia.api.events.AlbumEvent
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.UsuarioUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class AlbumFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        EventBus.getDefault().register(this)
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuario = UsuarioUtils.usuario
        AlbumCall.listaAlbums(usuario, activity!!.applicationContext, recyclerAlbums)
        configuraToolbar(usuario)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
        if(AlbumCall.call.isExecuted){
           AlbumCall.call.cancel()
        }
    }


    private fun configuraRecycler(albums: ArrayList<Album>) {
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

    @Subscribe
    fun onEvent(albumEvent: AlbumEvent){
        if(albumEvent.erro != null){
            tratarErro(albumEvent.erro)
        }else{
            configuraRecycler(albumEvent.albums)
        }
    }

    private fun tratarErro(erro: Throwable) {
        Toast.makeText(context, erro.message, Toast.LENGTH_LONG).show()
    }
}
