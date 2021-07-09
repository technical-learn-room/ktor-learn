package com.j.lms.domain.book.service

import com.j.lms.domain.book.dao.BookDataAccessor
import com.j.lms.domain.book.entity.Book

class BookSearchService(
    private val bookDataAccessor: BookDataAccessor<Book, Long>,
) {

    fun searchAll() {
        bookDataAccessor.findAll()
    }

    fun search(bookId: Long) {

    }
}