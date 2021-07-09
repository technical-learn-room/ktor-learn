package com.j.lms.domain.book.service

import com.j.lms.domain.book.dao.BookDataAccessor
import com.j.lms.domain.book.entity.Book

class BookCreationService(
    private val bookDataAccessor: BookDataAccessor<Book, Long>,
) {

    fun create(bookName: String, bookAuthor: String, bookPrice: Int, libraryId: Long) {
        val newBook = Book(
            name = bookName,
            author = bookAuthor,
            price = bookPrice,
            libraryId = libraryId,
        )
        bookDataAccessor.save(newBook)
    }
}