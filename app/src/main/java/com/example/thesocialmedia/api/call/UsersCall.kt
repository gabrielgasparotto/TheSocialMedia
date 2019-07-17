package com.example.thesocialmedia.api.call

import android.content.Context
import android.view.View
import com.example.thesocialmedia.api.configuration.RetrofitInitializer
import com.example.thesocialmedia.api.events.UsersEvent
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.SnackbarUtils
import org.greenrobot.eventbus.EventBus
import retrofit2.Call

object UsersCall {

    lateinit var call: Call<ArrayList<Users>>

    fun consultaUsuario(username: String, context: Context, view: View){
        val call = RetrofitInitializer().usersService().loginByUsername(username)
        call.enqueue(callback({ response ->

            val users = response.body()
            if(users.isNullOrEmpty()){
                SnackbarUtils().showSnack("User not found", view, context)
            }else{
                EventBus.getDefault().post(UsersEvent(users))
            }

        },{ throwable ->
            EventBus.getDefault().post(UsersEvent(erro= throwable))
        }))
    }


}
