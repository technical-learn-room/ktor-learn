package com.j.lms.domain.library.service

import com.j.lms.domain.library.dao.LibraryDataAccessor
import com.j.lms.domain.library.entity.Library

class LibraryModificationService(
    private val libraryDataAccessor: LibraryDataAccessor<Library, Long>,
) {

    fun modifyName(libraryId: Long, newLibraryName: String) {
        libraryDataAccessor.updateNameById(
            libraryId = libraryId,
            newName = newLibraryName,
        )
    }

    fun modifyLocation(libraryId: Long, newLibraryLocation: String) {
        libraryDataAccessor.updateLocationById(
            libraryId = libraryId,
            newLocation = newLibraryLocation,
        )
    }
}