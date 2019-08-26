package com.example.thesocialmedia.features.login

import com.example.thesocialmedia.api.arch.BaseBusiness
import com.example.thesocialmedia.api.arch.BaseUserView

class LoginContract {
    interface LoginUserView : BaseUserView<LoginBusiness> {
        fun exibeSnackbar(mensagem: String)
        fun irParaTelaInicial()
        fun obterEstadoCheckboxPermanecerLogado(): Boolean
    }

    abstract class LoginBusiness(val loginUserView: LoginUserView) : BaseBusiness {
        abstract fun aoConsultarUsuario(nomeUsuario: String)
    }
}