package com.j.lms.domain.library.service

import com.j.lms.domain.library.dao.LibraryDataAccessor
import com.j.lms.domain.library.entity.Library
import com.j.lms.domain.library.exception.LibraryNotFoundException
import com.j.lms.domain.library.route.response.LibraryAllSearchResponse
import com.j.lms.domain.library.route.response.LibrarySearchResponse

class LibrarySearchService(
    private val libraryDataAccessor: LibraryDataAccessor<Library, Long>,
) {

    fun searchAll() =
        libraryDataAccessor.findAll()
            .map {
                LibraryAllSearchResponse.LibraryAttribute(
                    name = it.name,
                    location = it.location,
                )
            }

    fun search(libraryId: Long): LibrarySearchResponse.LibraryAttribute {
        val library = libraryDataAccessor.findById(libraryId)
            ?: throw LibraryNotFoundException(libraryId)

        return LibrarySearchResponse.LibraryAttribute(
            name = library.name,
            location = library.location,
        )
    }
}