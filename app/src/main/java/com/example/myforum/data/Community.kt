package com.example.myforum.data

data class Community(
    val id: Int? = null,
    val communityName: String,
    val communityDescription: String,
    val author: String,
    val publishDate: String? = null
)