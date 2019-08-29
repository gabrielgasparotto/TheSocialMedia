package com.example.thesocialmedia.features.splash

import android.content.Context
import com.example.thesocialmedia.app.Constants.Companion.PERMANECE_DATABASE
import com.example.thesocialmedia.app.Constants.Companion.USER_PERMANECE

class SplashPresenter(albumUserView: SplashContract.SplashUserView, override var context: Context)
    : SplashContract.SplashBusiness(albumUserView) {

    override fun aoIniciar(context: Context, configurarEventBus: Boolean) {
        super.aoIniciar(context, false)
    }

    override fun consultaPreferenciaUsuario(): Boolean {
        val sharedPreference = context.getSharedPreferences(PERMANECE_DATABASE, Context.MODE_PRIVATE)
        return sharedPreference.getBoolean(USER_PERMANECE, false)
    }
}