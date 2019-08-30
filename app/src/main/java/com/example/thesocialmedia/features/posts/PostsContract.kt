package com.example.thesocialmedia.features.posts

import com.example.thesocialmedia.api.arch.BaseBusiness
import com.example.thesocialmedia.api.arch.BaseUserView
import com.example.thesocialmedia.model.Posts

class PostsContract {

    interface PostsUserView : BaseUserView<PostsBusiness>{
        fun exibeSnackbar(mensagem: String)
        fun configuraRecycler(posts: ArrayList<Posts>)
        fun configuraToolbar(titulo: String)
        fun preencherToolbar(nome: String, email: String, telefone: String, company: String)
    }

    abstract class PostsBusiness(val postsUserView: PostsUserView) : BaseBusiness {
        abstract fun consultaPosts()
    }
}