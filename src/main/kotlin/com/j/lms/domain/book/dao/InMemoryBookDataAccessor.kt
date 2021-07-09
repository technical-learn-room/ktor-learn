package com.j.lms.domain.book.dao

import com.j.lms.domain.book.entity.Book

class InMemoryBookDataAccessor : BookDataAccessor<Book, Long> {
    private val database: MutableMap<Long, Book> = mutableMapOf()

    override fun save(entity: Book) {
        entity.id = generateBookId()
        database[entity.id!!] = entity
    }

    private fun generateBookId() = database.keys.maxOf { it } + 1

    override fun findById(libraryId: Long, bookId: Long): Book? {
        val book = database[bookId]
        if (book?.libraryId == libraryId) {
            return book
        }
        return null
    }

    override fun findAll() = database.values

    override fun findAllByLibraryId(libraryId: Long) =
        findAll().filter { it.libraryId == libraryId }

    override fun updateNameById(libraryId: Long, bookId: Long, newBookName: String) {
        val book = findById(libraryId, bookId)
        book?.modifyBookName(newBookName)
    }

    override fun deleteById(libraryId: Long, bookId: Long) {
        database.remove(bookId)
    }
}