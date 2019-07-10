package com.example.thesocialmedia.util

import android.content.Context
import com.example.thesocialmedia.app.Constants
import com.example.thesocialmedia.model.Users

class UsuarioUtils {

    companion object {
        lateinit var usuario: Users

        fun populaUsuario(context: Context){
            usuario = SharedPreferencesUtils
                .buscaObjeto(context, Constants.userDB, Constants.userObjeto, Users::class.java)
        }
    }

}