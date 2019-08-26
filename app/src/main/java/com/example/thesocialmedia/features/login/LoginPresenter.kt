package com.example.thesocialmedia.features.login

import android.content.Context
import com.example.thesocialmedia.api.call.UsersCall
import com.example.thesocialmedia.api.events.UsersEvent
import com.example.thesocialmedia.app.Constants
import com.example.thesocialmedia.model.Users
import com.google.gson.Gson
import org.greenrobot.eventbus.Subscribe

class LoginPresenter(loginUserView: LoginContract.LoginUserView)
    : LoginContract.LoginBusiness(loginUserView) {
    override lateinit var context: Context

    override fun aoConsultarUsuario(nomeUsuario: String) {
        if (nomeUsuario.isBlank()) {
            loginUserView.exibeSnackbar("Null or Blank")
        } else {
            UsersCall.consultaUsuario(nomeUsuario)
        }
    }

    private fun checkBoxPermaneceLogado(permaneceLogado: Boolean) {
        val sharedPreference = context.getSharedPreferences(
            Constants.permaneceDB, Context.MODE_PRIVATE
        )
        val editor = sharedPreference.edit()
        if (permaneceLogado) {
            editor.putBoolean(Constants.userPermanece, true)
            editor.apply()
        } else {
            editor.putBoolean(Constants.userPermanece, false)
            editor.apply()
        }
    }

    private fun mantemDadosUsuario(usuario: Users) {
        val sharedPreference = context.getSharedPreferences(
            Constants.userDB, Context.MODE_PRIVATE
        )
        val editor = sharedPreference.edit()
        val json = Gson().toJson(usuario)
        editor.putString(Constants.userObjeto, json)
        editor.apply()
    }

    @Subscribe
    fun onEvent(usersEvent: UsersEvent) {
        if (usersEvent.erro != null) {
            loginUserView.exibeSnackbar(usersEvent.erro.message ?: "Erro desconhecido")
        } else {
            loginUserView.irParaTelaInicial()
            checkBoxPermaneceLogado(loginUserView.obterEstadoCheckboxPermanecerLogado())
            mantemDadosUsuario(usersEvent.users[0])
        }
    }
}