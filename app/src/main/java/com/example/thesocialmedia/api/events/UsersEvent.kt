package com.example.thesocialmedia.api.events

import com.example.thesocialmedia.model.Users

data class UsersEvent(val users: ArrayList<Users> = ArrayList(), val erro: Throwable? = null)
