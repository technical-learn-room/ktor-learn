package com.j.lms.domain.book.router.response

data class BookAllSearchResponse(
    val books: List<BookAttribute>
) {

    data class BookAttribute(
        val name: String,
        val author: String,
        val price: Int,
    )
}