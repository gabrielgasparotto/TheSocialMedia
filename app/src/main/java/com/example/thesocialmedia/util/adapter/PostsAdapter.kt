package com.example.thesocialmedia.util.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thesocialmedia.R
import com.example.thesocialmedia.model.Posts
import kotlinx.android.synthetic.main.item_posts.view.*

class PostsAdapter (val posts: ArrayList<Posts>): RecyclerView.Adapter<ViewHolderPost>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPost {
        val viewHolder = ViewHolderPost(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_posts, parent, false)
        )
        return viewHolder
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolderPost, position: Int) {
        var post = posts.get(position)
        holder.apply {
            tituloItem.text = post.title
            textoItem.text = post.body
        }
    }
}

class ViewHolderPost (view: View) : RecyclerView.ViewHolder(view) {
    val tituloItem = view.tituloPost
    val textoItem = view.textoPost
}