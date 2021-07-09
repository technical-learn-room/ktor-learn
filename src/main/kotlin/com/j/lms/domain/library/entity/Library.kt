package com.j.lms.domain.library.entity

class Library(
    name: String,
    location: String,
) {
    var id: Long? = null

    var name = name
        private set

    var location = location
        private set

    fun modifyLibraryName(newName: String) {
        this.name = newName
    }

    fun modifyLibraryLocation(newLocation: String) {
        this.location = newLocation
    }
}