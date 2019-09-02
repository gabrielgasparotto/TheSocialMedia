package com.example.thesocialmedia.features.home

import android.content.Context
import android.support.design.widget.BottomNavigationView
import com.example.thesocialmedia.R
import com.example.thesocialmedia.features.album.AlbumFragment
import com.example.thesocialmedia.features.maps.MapsFragment
import com.example.thesocialmedia.features.posts.PostsFragment

class HomePresenter(homeUserView: HomeContract.HomeUserView, override var context: Context) :
    HomeContract.HomeBusiness(homeUserView) {

    override fun aoIniciar(context: Context, configurarEventBus: Boolean) {
        super.aoIniciar(context, false)
    }

    override fun escolheAbasSelecionadas(): BottomNavigationView.OnNavigationItemSelectedListener {
        return BottomNavigationView
            .OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_posts -> {
                        //homeUserView.irParaPosts() // Opção mais "verbosa"
                        homeUserView.reposicionaFragment(PostsFragment()) // Opção mais "genérica"
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_album -> {
                        //homeUserView.irParaAlbum() // Opção mais "verbosa"
                        homeUserView.reposicionaFragment(AlbumFragment()) // Opção mais "genérica"
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_maps -> {
                        //homeUserView.irParaMaps() // Opção mais "verbosa"
                        homeUserView.reposicionaFragment(MapsFragment()) // Opção mais "genérica"
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }
    }
}