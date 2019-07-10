package com.example.thesocialmedia.api.call

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.example.thesocialmedia.api.configuration.RetrofitInitializer
import com.example.thesocialmedia.api.events.PostsEvent
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.SnackbarUtils
import com.example.thesocialmedia.api.observable.ObservableExample
import com.example.thesocialmedia.model.Posts
import org.greenrobot.eventbus.EventBus
import retrofit2.Call

object PostsCall {

    lateinit var call : Call<ArrayList<Posts>>

    fun listaPosts(usuario: Users, context: Context, recycler: RecyclerView){
        call = RetrofitInitializer().postsService().allPosts(usuario.id)
        call.enqueue(callback({ response ->

            val posts = response.body()
            if (posts.isNullOrEmpty()) {
                SnackbarUtils()
                    .showSnack("Nothing to show"
                        , recycler, context
                    )
            } else {
                EventBus.getDefault().post(PostsEvent(posts = posts))
                ObservableExample.observableExample(posts)
            }

        }, { throwable -> EventBus.getDefault().post(PostsEvent(erro = throwable)) }))
    }

}