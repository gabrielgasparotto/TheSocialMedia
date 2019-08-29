package com.example.thesocialmedia.api.arch

import android.content.Context
import org.greenrobot.eventbus.EventBus

interface BaseBusiness {
    var context: Context

    fun aoIniciar(context: Context, configurarEventBus: Boolean = true) {
        this.context = context

        if (configurarEventBus) registrarEventBus()
    }

    fun aoFinalizar() {
        desregistrarEventBus()
    }

    private fun registrarEventBus() {
        EventBus.getDefault().register(this)
    }

    private fun desregistrarEventBus() {
        EventBus.getDefault().unregister(this)
    }
}