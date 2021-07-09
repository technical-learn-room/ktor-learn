package com.j.lms.domain.library.dao

interface LibraryDataAccessor<E : Any, ID : Any> {
    fun save(entity: E)
    fun findById(libraryId: ID): E?
    fun findAll(): Iterable<E>
    fun updateNameById(libraryId: ID, newName: String)
    fun updateLocationById(libraryId: ID, newLocation: String)
    fun deleteById(libraryId: ID)
    fun existsById(libraryId: ID): Boolean
}