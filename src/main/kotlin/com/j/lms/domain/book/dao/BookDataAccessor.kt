package com.j.lms.domain.book.dao

interface BookDataAccessor<E : Any, ID : Any> {
    fun save(entity: E)
    fun findById(id: ID): E?
    fun findAll(): Iterable<E>
    fun updateNameById(id: ID, newName: String)
}