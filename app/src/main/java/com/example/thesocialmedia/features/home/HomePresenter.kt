package com.example.thesocialmedia.features.home

import android.content.Context
import android.support.design.widget.BottomNavigationView
import com.example.thesocialmedia.R
import com.example.thesocialmedia.features.album.AlbumFragment
import com.example.thesocialmedia.features.posts.PostsFragment
import com.example.thesocialmedia.view.fragment.MapsFragment

class HomePresenter(homeUserView: HomeContract.HomeUserView, override var context: Context)
    : HomeContract.HomeBusiness(homeUserView) {

    override fun aoIniciar(context: Context, configurarEventBus: Boolean) {
        super.aoIniciar(context, false)
    }

    override fun escolheAbasSelecionadas(): BottomNavigationView.OnNavigationItemSelectedListener {
        return BottomNavigationView
            .OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_posts -> {
                        homeUserView.reposicionaFragment(PostsFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_album -> {
                        homeUserView.reposicionaFragment(AlbumFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_maps -> {
                        homeUserView.reposicionaFragment(MapsFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }
    }
}