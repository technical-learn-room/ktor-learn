package com.j.lms.domain.book.dao

import com.j.lms.domain.book.entity.Book

class InMemoryBookDataAccessor : BookDataAccessor<Book, Long> {
    private val database: MutableMap<Long, Book> = mutableMapOf()

    override fun save(entity: Book) {
        entity.id = generateBookId()
        database[entity.id!!] = entity
    }

    private fun generateBookId() = database.keys.maxOf { it } + 1

    override fun findById(id: Long) = database[id]

    override fun findAll() = database.values

    override fun updateNameById(id: Long, newName: String) {
        database[id]?.modifyBookName(newName)
    }
}