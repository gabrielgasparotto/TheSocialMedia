package com.example.thesocialmedia.view.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.thesocialmedia.R
import com.example.thesocialmedia.dao.RetrofitInitializer
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.CustomSnackbar
import com.example.thesocialmedia.util.StringKeys
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener {
            if(usernameLogin.text.toString().isNullOrBlank()){
                CustomSnackbar().showSnack("Null or Blank", buttonLogin, applicationContext)
            }else{
                consultaUsuario(usernameLogin.text.toString())
            }
        }
    }

    private fun consultaUsuario(username: String){
        val call = RetrofitInitializer().usersService().loginByUsername(username)
        call.enqueue(callback({ response ->

            if(response.body().isNullOrEmpty()){
                CustomSnackbar().showSnack("User not found", buttonLogin, applicationContext)
            }else{
                val usuario = response.body()!!.get(0)
                checkBoxPermaneceLogado()
                efetuaLogin()
                mantemDadosUsuario(usuario)
            }

        },{ throwable ->

            CustomSnackbar().showSnack("Connection error!", buttonLogin, applicationContext)
            Log.v("retrofitResponse", "${throwable.message}")

        }))
    }

    private fun efetuaLogin() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkBoxPermaneceLogado() {
        val sharedPreference = getSharedPreferences(StringKeys.permaneceDB
            , Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        if (checkboxLogin.isChecked) {
            editor.putBoolean(StringKeys.userPermanece, true)
            editor.apply()
        } else {
            editor.putBoolean(StringKeys.userPermanece, false)
            editor.apply()
        }
    }

    private fun mantemDadosUsuario(usuario: Users) {
        val sharedPreference = getSharedPreferences(StringKeys.userDB
            , Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        val json = Gson().toJson(usuario)
        editor.putString(StringKeys.userObjeto, json)
        editor.apply()
    }
}
