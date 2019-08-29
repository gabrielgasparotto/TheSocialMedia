package com.example.thesocialmedia.api.arch

interface BaseUserView<BUSINESS : BaseBusiness> {
    var business: BUSINESS

    fun configurarBusiness(business: BUSINESS) {
        this.business = business
        this.business.aoIniciar(this.business.context)
    }

    fun finalizar() {
        this.business.aoFinalizar()
    }
}