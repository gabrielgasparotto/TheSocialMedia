package com.example.thesocialmedia.model

import java.io.Serializable

data class Photos (
    val albumId: Long = 0,
    val id: Long = 0,
    val title: String = "x",
    val url: String = "x",
    val thumbnailUrl: String = "x"
) : Serializable