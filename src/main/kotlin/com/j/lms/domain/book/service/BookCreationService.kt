package com.j.lms.domain.book.service

import com.j.lms.domain.book.dao.BookDataAccessor
import com.j.lms.domain.book.entity.Book
import com.j.lms.domain.library.dao.LibraryDataAccessor
import com.j.lms.domain.library.entity.Library
import com.j.lms.domain.library.exception.LibraryNotFoundException

class BookCreationService(
    private val libraryDataAccessor: LibraryDataAccessor<Library, Long>,
    private val bookDataAccessor: BookDataAccessor<Book, Long>,
) {

    fun create(bookName: String, bookAuthor: String, bookPrice: Int, libraryId: Long) {
        val existOfLibrary = libraryDataAccessor.existsById(libraryId)

        if (!existOfLibrary)
            throw LibraryNotFoundException(libraryId)

        val newBook = Book(
            name = bookName,
            author = bookAuthor,
            price = bookPrice,
            libraryId = libraryId,
        )
        bookDataAccessor.save(newBook)
    }
}