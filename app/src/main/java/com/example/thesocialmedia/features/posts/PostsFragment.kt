package com.example.thesocialmedia.features.posts

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thesocialmedia.R
import com.example.thesocialmedia.model.Posts
import com.example.thesocialmedia.model.Users
import com.example.thesocialmedia.util.SnackbarUtils
import com.example.thesocialmedia.util.adapter.PostsAdapter
import com.example.thesocialmedia.util.UsuarioUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_posts.*

class PostsFragment : Fragment(), PostsContract.PostsUserView {

    override lateinit var business: PostsContract.PostsBusiness

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val usuario = UsuarioUtils.usuario
        configurarBusiness(PostsPresenter(this), activity!!.applicationContext)
        business.consultaPosts(usuario)
        configuraToolbar(usuario)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        business.aoFinalizar()
    }

    override fun exibeSnackbar(mensagem: String) {
        SnackbarUtils().showSnack(mensagem, recyclerPosts, activity!!.applicationContext)
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

    override fun configuraRecycler(posts: ArrayList<Posts>) {
        recyclerPosts.apply {
            layoutManager = LinearLayoutManager(activity)
            layoutManager = GridLayoutManager(activity, 1)
            adapter = PostsAdapter(posts)
        }
    }
}
