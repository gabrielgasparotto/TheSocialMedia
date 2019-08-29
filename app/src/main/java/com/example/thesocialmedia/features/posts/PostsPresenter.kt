package com.example.thesocialmedia.features.posts

import android.content.Context
import com.example.thesocialmedia.api.call.PostsCall
import com.example.thesocialmedia.api.events.PostsEvent
import com.example.thesocialmedia.model.Users
import org.greenrobot.eventbus.Subscribe

class PostsPresenter(postsUserView: PostsContract.PostsUserView)
    : PostsContract.PostsBusiness(postsUserView){
    override lateinit var context: Context

    override fun consultaPosts(usuario: Users) {
        PostsCall.listaPosts(usuario)
    }

    override fun aoFinalizar() {
        super.aoFinalizar()
        if (PostsCall.call.isExecuted) {
            PostsCall.call.cancel()
        }
    }

    @Subscribe
    fun onEvent(evento: PostsEvent) {
        if (evento.erro != null) {
            postsUserView.exibeSnackbar(evento.erro.message ?: "Erro desconhecido")
        } else {
            postsUserView.configuraRecycler(evento.posts)
        }
    }
}