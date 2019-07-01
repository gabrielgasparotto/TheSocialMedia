package com.example.thesocialmedia.extension

import android.support.v7.widget.RecyclerView

fun <T : RecyclerView.ViewHolder> T.onClickItem(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}

