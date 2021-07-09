package com.j.lms.domain.library.service

import com.j.lms.domain.library.dao.LibraryDataAccessor
import com.j.lms.domain.library.entity.Library

class LibraryDeletionService(
    private val libraryDataAccessor: LibraryDataAccessor<Library, Long>,
) {

    fun delete(libraryId: Long) {
        libraryDataAccessor.deleteById(libraryId)
    }
}