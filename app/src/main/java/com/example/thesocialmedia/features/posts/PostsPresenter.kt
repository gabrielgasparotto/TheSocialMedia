package com.example.thesocialmedia.features.posts

import android.content.Context
import com.example.thesocialmedia.api.call.PostsCall
import com.example.thesocialmedia.api.events.PostsEvent
import com.example.thesocialmedia.util.UsuarioUtils
import org.greenrobot.eventbus.Subscribe

class PostsPresenter(postsUserView: PostsContract.PostsUserView, override var context: Context) :
    PostsContract.PostsBusiness(postsUserView) {

    override fun aoIniciar(context: Context, configurarEventBus: Boolean) {
        super.aoIniciar(context, configurarEventBus)
        consultaPosts()
        preencheItens()

    }

    override fun consultaPosts() {
        PostsCall.listaPosts(UsuarioUtils.usuario)
    }

    override fun aoFinalizar() {
        super.aoFinalizar()
        if (PostsCall.call.isExecuted) {
            PostsCall.call.cancel()
        }
    }

    fun preencheItens() {
        postsUserView.preencherToolbar(
            UsuarioUtils.usuario.name,
            UsuarioUtils.usuario.email,
            UsuarioUtils.usuario.phone,
            UsuarioUtils.usuario.company.name
        )
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