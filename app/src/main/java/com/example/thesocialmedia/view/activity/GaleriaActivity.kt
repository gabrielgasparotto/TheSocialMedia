package com.example.thesocialmedia.view.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.thesocialmedia.R
import com.example.thesocialmedia.api.call.GaleriaCall
import com.example.thesocialmedia.api.configuration.RetrofitInitializer
import com.example.thesocialmedia.api.events.PhotosEvent
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.model.Photos
import com.example.thesocialmedia.util.SnackbarUtils
import com.example.thesocialmedia.util.adapter.GaleriaAdapter
import kotlinx.android.synthetic.main.activity_galeria.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class GaleriaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)
        setSupportActionBar(toolbarGaleria)

        EventBus.getDefault().register(this)

        val album = intent.getSerializableExtra("album") as Album
        GaleriaCall.listaGaleria(album, this, recyclerGaleria)
        configuraToolbar(album)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private fun configuraRecycler(fotos: ArrayList<Photos>) {
        recyclerGaleria.apply {
            layoutManager = LinearLayoutManager(context)
            layoutManager = GridLayoutManager(context, 3)
            adapter = GaleriaAdapter(fotos)
        }
    }

    private fun configuraToolbar(album: Album) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = album.title
        }
        toolbarGaleria.setTitleTextColor(Color.WHITE)
    }

    @Subscribe
    fun onEvent(photosEvent: PhotosEvent){
        if(photosEvent.erro != null){
            tratarErro(photosEvent.erro)
        }else{
            configuraRecycler(photosEvent.photos)
        }
    }

    private fun tratarErro(throwable: Throwable) {
        Toast.makeText(this, throwable.message , Toast.LENGTH_LONG).show()
    }
}
