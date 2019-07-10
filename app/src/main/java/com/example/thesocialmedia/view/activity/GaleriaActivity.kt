package com.example.thesocialmedia.view.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.thesocialmedia.R
import com.example.thesocialmedia.api.RetrofitInitializer
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.model.Photos
import com.example.thesocialmedia.util.SnackbarUtils
import com.example.thesocialmedia.util.adapter.GaleriaAdapter
import kotlinx.android.synthetic.main.activity_galeria.*

class GaleriaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)
        setSupportActionBar(toolbarGaleria)

        val album = intent.getSerializableExtra("album") as Album
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = album.title
        }
        toolbarGaleria.setTitleTextColor(Color.WHITE)

        listaGaleria(album)
    }

    private fun configuraRecycler(fotos: ArrayList<Photos>) {
        recyclerGaleria.apply {
            layoutManager = LinearLayoutManager(context)
            layoutManager = GridLayoutManager(context, 3)
            adapter = GaleriaAdapter(fotos)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun listaGaleria(album: Album){
        val call = RetrofitInitializer().galeriaService().allPhotosByAlbumId(album.id)
        call.enqueue(callback({ response ->

            val photos = response.body()
            if(photos.isNullOrEmpty()){
                SnackbarUtils()
                    .showSnack("Nothing to show"
                        , recyclerGaleria, applicationContext)
            }else{
                configuraRecycler(photos)
            }

        },{ throwable ->

            SnackbarUtils()
                .showSnack("${throwable.message}"
                    , recyclerGaleria, applicationContext)

        }))
    }
}
