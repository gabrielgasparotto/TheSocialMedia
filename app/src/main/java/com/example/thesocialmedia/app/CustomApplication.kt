package com.example.thesocialmedia.app

import android.app.Application
import org.greenrobot.eventbus.EventBus

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        EventBus.builder().installDefaultEventBus()
    }
}