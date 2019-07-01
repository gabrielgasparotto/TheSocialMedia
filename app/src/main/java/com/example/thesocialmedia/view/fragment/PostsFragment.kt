package com.example.thesocialmedia.view.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thesocialmedia.R
import com.example.thesocialmedia.dao.RetrofitInitializer
import com.example.thesocialmedia.extension.callback
import com.example.thesocialmedia.model.Posts
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.*
import com.example.thesocialmedia.util.adapter.PostsAdapter
import com.example.thesocialmedia.util.UsuarioUtils
import kotlinx.android.synthetic.main.fragment_posts.*
import retrofit2.Call


class PostsFragment : Fragment() {

    lateinit var call: Call<ArrayList<Posts>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuario = UsuarioUtils.usuario
        listaPosts(usuario)
        configuraToolbar(usuario)
    }

    private fun configuraToolbar(usuario: Users) {
        val states = arrayOf(intArrayOf(android.R.attr.state_enabled))
        val colors = intArrayOf(Color.TRANSPARENT)
        val myList = ColorStateList(states, colors)

        collapsingPosts.apply {
            title = usuario.name
            setCollapsedTitleTextColor(Color.WHITE)
            setExpandedTitleTextColor(myList)
        }

        nomeCompletoToolbar.text = usuario.name
        emailToolbar.text = usuario.email
        telefoneToolbar.text = usuario.phone
        companyToolbar.text = usuario.company.name
    }

    fun listaPosts(usuario: Users){
        call = RetrofitInitializer().postsService().allPosts(usuario.id)
        call.enqueue(callback({ response ->

            val posts = response.body()
            if(posts.isNullOrEmpty()){
                CustomSnackbar()
                    .showSnack("Nothing to show"
                        , recyclerPosts, activity!!.applicationContext)
            }else{
                configuraRecycler(posts)
                ObservableExample.observableExample(posts)
            }

        },{ throwable ->
            Log.v("recyclerCancel", throwable.message)
        }))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(call.isExecuted){
            call.cancel()
        }
    }

    private fun configuraRecycler(posts: ArrayList<Posts>) {
        recyclerPosts.apply {
            layoutManager = LinearLayoutManager(activity)
            layoutManager = GridLayoutManager(activity, 1)
            adapter = PostsAdapter(posts)
        }
    }
}
