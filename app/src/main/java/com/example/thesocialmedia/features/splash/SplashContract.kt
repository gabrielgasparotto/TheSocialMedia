package com.example.thesocialmedia.features.splash

import android.support.v7.app.AppCompatActivity
import com.example.thesocialmedia.api.arch.BaseBusiness
import com.example.thesocialmedia.api.arch.BaseUserView

class SplashContract {

    interface SplashUserView : BaseUserView<SplashBusiness> {
        fun configuraSplash()
        fun mostrarActivity(destino : AppCompatActivity)
    }

    abstract class SplashBusiness(val albumUserView: SplashUserView): BaseBusiness {
        abstract fun consultaPreferenciaUsuario(): Boolean
    }
}