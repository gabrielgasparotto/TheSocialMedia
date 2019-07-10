package com.example.thesocialmedia.view.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.thesocialmedia.R
import com.example.thesocialmedia.api.call.UsersCall
import com.example.thesocialmedia.api.events.UsersEvent
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.SnackbarUtils
import com.example.thesocialmedia.app.Constants
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        EventBus.getDefault().register(this)

        buttonLogin.setOnClickListener {
            if(usernameLogin.text.toString().isNullOrBlank()){
                SnackbarUtils().showSnack("Null or Blank", buttonLogin, applicationContext)
            }else{
                UsersCall.consultaUsuario(usernameLogin.text.toString(), this, buttonLogin)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onEvent(usersEvent: UsersEvent){
        if(usersEvent.erro != null){
            tratarErro(usersEvent.erro)
        }else{
            efetuaLogin()
            checkBoxPermaneceLogado()
            mantemDadosUsuario(usersEvent.users[0])
        }
    }

    fun tratarErro(throwable: Throwable){
        SnackbarUtils().showSnack("Error", buttonLogin, applicationContext)
    }

    private fun efetuaLogin() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkBoxPermaneceLogado() {
        val sharedPreference = getSharedPreferences(
            Constants.permaneceDB
            , Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        if (checkboxLogin.isChecked) {
            editor.putBoolean(Constants.userPermanece, true)
            editor.apply()
        } else {
            editor.putBoolean(Constants.userPermanece, false)
            editor.apply()
        }
    }

    private fun mantemDadosUsuario(usuario: Users) {
        val sharedPreference = getSharedPreferences(
            Constants.userDB
            , Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        val json = Gson().toJson(usuario)
        editor.putString(Constants.userObjeto, json)
        editor.apply()
    }
}
