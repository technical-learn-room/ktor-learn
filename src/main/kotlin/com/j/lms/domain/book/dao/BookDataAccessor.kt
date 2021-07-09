package com.j.lms.domain.book.dao

interface BookDataAccessor<E : Any, ID : Any> {
    fun save(entity: E)
    fun findById(libraryId: Long, bookId: ID): E?
    fun findAll(): Iterable<E>
    fun findAllByLibraryId(libraryId: Long): Iterable<E>
    fun updateNameById(libraryId: Long, bookId: ID, newBookName: String)
    fun deleteById(libraryId: Long, bookId: ID)
}