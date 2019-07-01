package com.example.thesocialmedia.model

import java.io.Serializable

data class Users (
    val id : Long,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company

) : Serializable