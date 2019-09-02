package com.example.thesocialmedia.features.galeria

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.thesocialmedia.R
import com.example.thesocialmedia.model.Photos
import com.example.thesocialmedia.util.SnackbarUtils
import com.example.thesocialmedia.util.adapter.GaleriaAdapter
import kotlinx.android.synthetic.main.activity_galeria.*

class GaleriaActivity : AppCompatActivity(), GaleriaContract.GaleriaUserView {

    override lateinit var business: GaleriaContract.GaleriaBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria)
        setSupportActionBar(toolbarGaleria)

        configurarBusiness(GaleriaPresenter(this, applicationContext, intent))
    }

    override fun configuraToolbar(titulo: String) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = titulo
        }
        toolbarGaleria.setTitleTextColor(Color.WHITE)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        business.aoFinalizar()
    }

    override fun configuraRecycler(photos: ArrayList<Photos>) {
        recyclerGaleria.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = GaleriaAdapter(photos)
        }
    }

    override fun exibeSnackbar(mensagem: String) {
        SnackbarUtils().showSnack(mensagem, recyclerGaleria, applicationContext)
    }
}
