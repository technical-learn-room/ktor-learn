package com.j.lms.domain.library.router.response

data class LibraryAllSearchResponse(
    val libraries: List<LibraryAttribute>,
) {

    data class LibraryAttribute(
        val name: String,
        val location: String,
    )
}