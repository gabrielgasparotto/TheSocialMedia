package com.example.thesocialmedia.model

import java.io.Serializable

data class Album (
    val userId: Long,
    val id: Long,
    val title: String
) : Serializable