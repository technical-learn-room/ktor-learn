package com.j.lms.domain.book.route.request

data class BookCreationRequest(
    val book: BookAttribute,
) {

    data class BookAttribute(
        val name: String,
        val author: String,
        val price: Int,
        val libraryId: Long,
    )
}