package com.j.lms.domain.library.dao

import com.j.lms.domain.library.entity.Library

class InMemoryLibraryDataAccessor : LibraryDataAccessor<Library, Long> {
    private val database = mutableMapOf<Long, Library>()

    override fun save(entity: Library) {
        entity.id = generateLibraryId()
        database[entity.id!!] = entity
    }

    private fun generateLibraryId() = (database.keys + 0).maxOf { it } + 1

    override fun findById(libraryId: Long) = database[libraryId]

    override fun findAll() = database.values

    override fun updateNameById(libraryId: Long, newName: String) {
        database[libraryId]?.modifyLibraryName(newName)
    }

    override fun updateLocationById(libraryId: Long, newLocation: String) {
        database[libraryId]?.modifyLibraryLocation(newLocation)
    }

    override fun deleteById(libraryId: Long) {
        database.remove(libraryId)
    }
}