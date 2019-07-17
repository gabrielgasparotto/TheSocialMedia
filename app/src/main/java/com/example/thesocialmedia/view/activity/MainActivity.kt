package com.example.thesocialmedia.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.thesocialmedia.R
import com.example.thesocialmedia.util.UsuarioUtils
import com.example.thesocialmedia.view.fragment.AlbumFragment
import com.example.thesocialmedia.view.fragment.MapsFragment
import com.example.thesocialmedia.view.fragment.PostsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView
        .OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_posts -> {
                loadFragment(PostsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_album -> {
                loadFragment(AlbumFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_maps -> {
                loadFragment(MapsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment , javaClass.simpleName)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_posts
        UsuarioUtils.populaUsuario(applicationContext)
    }

}
