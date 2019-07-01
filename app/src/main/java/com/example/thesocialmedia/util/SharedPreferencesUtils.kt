package com.example.thesocialmedia.util

import android.content.Context
import com.example.thesocialmedia.model.Users
import com.google.gson.Gson

object SharedPreferencesUtils {

    fun <T> retornaUsuario(context: Context, sharedKey: String,objetoKey: String ,clazz: Class<T>): T {
        val sharedPreference = context
            .getSharedPreferences(sharedKey, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreference.getString(objetoKey, "")
        val objeto = gson.fromJson<T>(json, clazz)
        return objeto
    }
}