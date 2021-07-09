package com.j.lms.domain.book.service

import com.j.lms.domain.book.dao.BookDataAccessor
import com.j.lms.domain.book.entity.Book

class BookDeletionService(
    private val bookDataAccessor: BookDataAccessor<Book, Long>,
) {

    fun delete(libraryId: Long, bookId: Long) {
        bookDataAccessor.deleteById(libraryId, bookId)
    }
}