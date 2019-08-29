package com.example.thesocialmedia.util.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thesocialmedia.R
import com.example.thesocialmedia.extension.onClickItem
import com.example.thesocialmedia.model.Album
import com.example.thesocialmedia.features.galeria.GaleriaActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album.view.*


class AlbumAdapter (val albums: ArrayList<Album>): RecyclerView.Adapter<ViewHolderAlbum>(){

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAlbum {
        val viewHolder = ViewHolderAlbum(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_album, parent, false)
        )

        viewHolder.onClickItem { position, _ ->
            val intent = Intent(parent.context, GaleriaActivity::class.java)
            intent.putExtra("album", albums.get(position))
            parent.context.startActivity(intent)
        }

        context = parent.context

        return viewHolder
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: ViewHolderAlbum, position: Int) {
        var album = albums.get(position)
        holder.apply {
            tituloItem.text = album.title
        }

        val arrayListString = arrayListOf(
            "https://via.placeholder.com/150/771796",
            "https://via.placeholder.com/150/e9603b",
            "https://via.placeholder.com/150/e9b68",
            "https://via.placeholder.com/150/1adbcb",
            "https://via.placeholder.com/150/d28152",
            "https://via.placeholder.com/150/3c2446",
            "https://via.placeholder.com/150/c01edd",
            "https://via.placeholder.com/150/ab5be6",
            "https://via.placeholder.com/150/549689",
            "https://via.placeholder.com/150/6b1cf4",
            "https://via.placeholder.com/150/5cc01d",
            "https://via.placeholder.com/150/776d09"
        )

        Picasso.get()
            .load(arrayListString.get(position))
            .fit()
            .centerCrop()
            .into(holder.imagemItem)

        //viewPager(holder)    No caso de fazer albums com slider, seria dessa forma
    }

    private fun viewPager(holder: ViewHolderAlbum, arrayListString: ArrayList<String>) {
        val adapterViewPager = ViewPagerAdapter(context, arrayListString)
        /*holder.imagemItem.apply {
            startAutoScroll()
            adapter = adapterViewPager
            interval = 3000
        }*/
    }

}

class ViewHolderAlbum (view: View) : RecyclerView.ViewHolder(view) {
    val imagemItem = view.imagemAlbum
    val tituloItem = view.tituloAlbum
}