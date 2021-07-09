package com.j.lms.domain.library.service

import com.j.lms.domain.library.dao.LibraryDataAccessor
import com.j.lms.domain.library.entity.Library

class LibraryCreationService(
    private val libraryDataAccessor: LibraryDataAccessor<Library, Long>,
) {

    fun create(libraryName: String, libraryLocation: String) {
        val library = Library(
            name = libraryName,
            location = libraryLocation,
        )
        libraryDataAccessor.save(library)
    }
}