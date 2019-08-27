package com.example.thesocialmedia.api.arch

import android.content.Context
import com.example.thesocialmedia.api.call.PostsCall
import org.greenrobot.eventbus.EventBus

interface BaseBusiness {
    var context: Context

    fun aoIniciar(context: Context) {
        this.context = context
        registrarEventBus()
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