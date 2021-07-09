package com.j.lms.domain.book.route.response

data class BookSearchResponse(
    val book: BookAttribute,
) {

    data class BookAttribute(
        val name: String,
        val author: String,
        val price: Int,
    )
}