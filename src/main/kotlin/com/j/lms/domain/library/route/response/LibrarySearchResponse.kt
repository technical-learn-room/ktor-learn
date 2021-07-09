package com.j.lms.domain.library.route.response

data class LibrarySearchResponse(
    val library: LibraryAttribute,
) {

    data class LibraryAttribute(
        val name: String,
        val location: String,
    )
}