package com.example.thesocialmedia.model

import java.io.Serializable

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
) : Serializable
