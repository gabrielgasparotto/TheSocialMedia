package com.example.thesocialmedia.api.events

import com.example.thesocialmedia.model.Album

data class AlbumEvent (val albums: ArrayList<Album> = ArrayList(), val erro: Throwable? = null)