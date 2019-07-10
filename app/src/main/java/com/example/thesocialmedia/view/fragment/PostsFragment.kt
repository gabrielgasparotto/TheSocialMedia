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
import com.example.thesocialmedia.api.events.PostsEvent
import com.example.thesocialmedia.api.call.PostsCall
import com.example.thesocialmedia.model.Posts
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.adapter.PostsAdapter
import com.example.thesocialmedia.util.UsuarioUtils
import kotlinx.android.synthetic.main.fragment_posts.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class PostsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        EventBus.getDefault().register(this)
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuario = UsuarioUtils.usuario
        PostsCall.listaPosts(usuario, activity!!.applicationContext, recyclerPosts)
        configuraToolbar(usuario)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
        if (PostsCall.call.isExecuted) {
            PostsCall.call.cancel()
        }
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

    private fun configuraRecycler(posts: ArrayList<Posts>) {
        recyclerPosts.apply {
            layoutManager = LinearLayoutManager(activity)
            layoutManager = GridLayoutManager(activity, 1)
            adapter = PostsAdapter(posts)
        }
    }

    private fun tratarErro(throwable: Throwable) {
        Log.v("recyclerCancel", throwable.message)
    }

    @Subscribe
    fun onEvent(evento: PostsEvent) {
        if (evento.erro != null) {
            tratarErro(evento.erro)
        } else {
            configuraRecycler(evento.posts)
        }
    }
}
