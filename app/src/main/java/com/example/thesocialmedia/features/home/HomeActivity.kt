package com.example.thesocialmedia.features.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.thesocialmedia.R
import com.example.thesocialmedia.features.album.AlbumFragment
import com.example.thesocialmedia.features.maps.MapsFragment
import com.example.thesocialmedia.features.posts.PostsFragment
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), HomeContract.HomeUserView {

    override lateinit var business: HomeContract.HomeBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarBusiness(HomePresenter(this, applicationContext))
        configuraNavigation()
    }

    private fun configuraNavigation() {
        navigation.apply {
            setOnNavigationItemSelectedListener(business.escolheAbasSelecionadas())
            selectedItemId = R.id.navigation_posts
        }
    }

    override fun reposicionaFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment, javaClass.simpleName)
            .commit()
    }

    override fun irParaPosts() {
        reposicionaFragment(PostsFragment())
    }

    override fun irParaAlbum() {
        reposicionaFragment(AlbumFragment())
    }

    override fun irParaMaps() {
        reposicionaFragment(MapsFragment())
    }
}
