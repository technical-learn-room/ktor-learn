package com.j.lms.domain.library.route.request

data class LibraryCreationRequest(
    val library: LibraryAttribute,
) {

    data class LibraryAttribute(
        val name: String,
        val location: String,
    )
}