package com.example.myforum.data

data class Comment(
    val id: Int? = null,
    var parentId: Int = -1,
    val author: String,
    val title: String,
    val content: String,
    val community: String,
    val publishTime: String? = null,
    val thumbCount: Int = 0
)