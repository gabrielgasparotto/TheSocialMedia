package com.example.thesocialmedia.api.events

import com.example.thesocialmedia.model.Photos

data class PhotosEvent(val photos: ArrayList<Photos> = ArrayList(), val erro: Throwable? = null)