package com.example.thesocialmedia.api.arch

import android.content.Context

interface BaseUserView<BUSINESS : BaseBusiness> {
    var business: BUSINESS

    fun configurarBusiness(business: BUSINESS, context: Context) {
        this.business = business
        this.business.aoIniciar(context)
    }

    fun finalizar() {
        this.business.aoFinalizar()
    }
}