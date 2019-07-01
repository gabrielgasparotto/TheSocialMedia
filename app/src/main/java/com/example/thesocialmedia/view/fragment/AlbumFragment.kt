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
import android.util.Log
import com.example.thesocialmedia.dao.RetrofitInitializer
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.CustomSnackbar
import com.example.thesocialmedia.util.UsuarioUtils
import retrofit2.Call


class AlbumFragment : Fragment() {

    lateinit var call: Call<ArrayList<Album>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuario = UsuarioUtils.usuario
        listaAlbums(usuario)
        configuraToolbar(usuario)
    }

    private fun listaAlbums(usuario: Users){
        call = RetrofitInitializer().albumService().allAlbumsByUserId(usuario.id)
        call.enqueue(callback({ response ->

            val albums = response.body()
            if(albums.isNullOrEmpty()){
                CustomSnackbar()
                    .showSnack("Nothing to show"
                        , recyclerAlbums, activity!!.applicationContext)
            }else{
                configuraRecycler(albums)
            }

        },{ throwable ->
            Log.v("recyclerCancel", throwable.message)
        }))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(call.isExecuted){
           call.cancel()
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
}
