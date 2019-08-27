package com.example.thesocialmedia.api.call

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.example.thesocialmedia.BuildConfig
import com.example.thesocialmedia.api.configuration.RetrofitInitializer
import com.example.thesocialmedia.api.events.PostsEvent
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.api.observable.ObservableExample
import com.example.thesocialmedia.api.service.PostsService
import com.example.thesocialmedia.app.Constants
import com.example.thesocialmedia.model.Posts
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object PostsCall {

    lateinit var call : Call<ArrayList<Posts>>
    var postsService : PostsService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        )
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        postsService = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PostsService::class.java)
    }

    fun listaPosts(usuario: Users, context: Context, recycler: RecyclerView){
        call = RetrofitInitializer().postsService().allPosts(usuario.id)
        call.enqueue(callback({ response ->

            val posts = response.body()
            if (posts.isNullOrEmpty()) {
                EventBus.getDefault().post(PostsEvent(erro = Exception("Nothing to show")))
            } else {
                EventBus.getDefault().post(PostsEvent(posts = posts))
                ObservableExample.observableExample(posts)
            }

        }, { throwable -> EventBus.getDefault().post(PostsEvent(erro = throwable)) }))
    }

}