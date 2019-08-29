package com.example.thesocialmedia.features.posts

import com.example.thesocialmedia.api.arch.BaseBusiness
import com.example.thesocialmedia.api.arch.BaseUserView
import com.example.thesocialmedia.model.Posts
import com.example.thesocialmedia.model.Users

class PostsContract {

    interface PostsUserView : BaseUserView<PostsBusiness>{
        fun exibeSnackbar(mensagem: String)
        fun configuraRecycler(posts: ArrayList<Posts>)
    }

    abstract class PostsBusiness(val postsUserView: PostsUserView) : BaseBusiness {
        abstract fun consultaPosts(usuario: Users)
    }
}