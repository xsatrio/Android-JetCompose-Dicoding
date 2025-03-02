package com.xsat.bocchitherock.model

data class Bocchi(
    val id: String,
    val name: String,
    val photoUrl: String,
    val detail: String,
    var isFavorite: Boolean
)