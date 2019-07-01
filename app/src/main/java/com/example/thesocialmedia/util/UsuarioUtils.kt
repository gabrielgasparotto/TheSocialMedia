package com.example.thesocialmedia.util

import android.content.Context
import com.example.thesocialmedia.model.Users

class UsuarioUtils {

    companion object {
        lateinit var usuario: Users

        fun populaUsuario(context: Context){
            usuario = SharedPreferencesUtils
                .retornaUsuario(context, StringKeys.userDB, StringKeys.userObjeto, Users::class.java)
        }
    }

}