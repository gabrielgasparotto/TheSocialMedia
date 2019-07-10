package com.example.thesocialmedia.view.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.thesocialmedia.R
import android.content.Intent
import android.os.Handler
import com.example.thesocialmedia.app.Constants
import com.example.thesocialmedia.util.UsuarioUtils


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handle = Handler()
        val sharedPreference =  getSharedPreferences(Constants.permaneceDB, Context.MODE_PRIVATE)
        val teste = sharedPreference.getBoolean(Constants.userPermanece, false)

        handle.postDelayed(Runnable {
            if(teste){
                mostrar(MainActivity())
                UsuarioUtils.populaUsuario(applicationContext)
            }else{
                mostrar(LoginActivity())
            }
        }, 1400)
    }

    private fun mostrar(destino : AppCompatActivity) {
        val intent = Intent(this@SplashActivity, destino::class.java)
        startActivity(intent)
        finish()
    }
}
