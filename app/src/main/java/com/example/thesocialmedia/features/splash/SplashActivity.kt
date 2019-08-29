package com.example.thesocialmedia.features.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.thesocialmedia.R
import android.content.Intent
import android.os.Handler
import com.example.thesocialmedia.features.home.HomeActivity
import com.example.thesocialmedia.features.login.LoginActivity
import com.example.thesocialmedia.util.UsuarioUtils

class SplashActivity : AppCompatActivity(), SplashContract.SplashUserView {

    override lateinit var business: SplashContract.SplashBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        configurarBusiness(SplashPresenter(this, applicationContext))
        configuraSplash()
    }

    override fun configuraSplash() {
        Handler().postDelayed(Runnable {
            if (business.consultaPreferenciaUsuario()) {
                mostrarActivity(HomeActivity())
                UsuarioUtils.populaUsuario(applicationContext)
            } else {
                mostrarActivity(LoginActivity())
            }
        }, 1400)
    }

    override fun mostrarActivity(destino : AppCompatActivity) {
        val intent = Intent(this@SplashActivity, destino::class.java)
        startActivity(intent)
        finish()
    }
}
