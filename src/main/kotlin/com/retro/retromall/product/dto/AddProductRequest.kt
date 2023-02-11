package com.retro.retromall.product.dto

data class AddProductRequest(
    val content: String?,
    val amount: Int,
    val category: String,
    val imageList: MutableList<String>?,
    val hashTagList: MutableList<String>?
)
