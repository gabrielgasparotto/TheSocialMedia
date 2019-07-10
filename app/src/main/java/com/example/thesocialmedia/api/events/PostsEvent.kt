package com.example.thesocialmedia.api.events

import com.example.thesocialmedia.model.Posts

data class PostsEvent(val posts: ArrayList<Posts> = ArrayList(), val erro: Throwable? = null)