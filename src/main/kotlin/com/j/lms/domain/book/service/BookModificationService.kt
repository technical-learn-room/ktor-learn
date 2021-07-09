package com.j.lms.domain.book.service

import com.j.lms.domain.book.dao.BookDataAccessor
import com.j.lms.domain.book.entity.Book

class BookModificationService(
    private val bookDataAccessor: BookDataAccessor<Book, Long>,
) {

    fun modifyName(libraryId: Long, bookId: Long, newBookName: String) {
        bookDataAccessor.updateNameById(
            libraryId = libraryId,
            bookId = bookId,
            newBookName = newBookName,
        )
    }
}