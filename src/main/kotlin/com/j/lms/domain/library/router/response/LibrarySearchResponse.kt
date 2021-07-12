package com.j.lms.domain.library.router.response

data class LibrarySearchResponse(
    val library: LibraryAttribute,
) {

    data class LibraryAttribute(
        val name: String,
        val location: String,
    )
}