package com.j.lms.domain.book.service

import com.j.lms.domain.book.dao.BookDataAccessor
import com.j.lms.domain.book.entity.Book
import com.j.lms.domain.book.exception.BookNotFoundException
import com.j.lms.domain.book.router.response.BookAllSearchResponse
import com.j.lms.domain.book.router.response.BookSearchResponse

class BookSearchService(
    private val bookDataAccessor: BookDataAccessor<Book, Long>,
) {

    fun searchAll(libraryId: Long) =
        bookDataAccessor.findAllByLibraryId(libraryId)
            .map {
                BookAllSearchResponse.BookAttribute(
                    name = it.name,
                    author = it.author,
                    price = it.price,
                )
            }

    fun search(libraryId: Long, bookId: Long): BookSearchResponse.BookAttribute {
        val book = bookDataAccessor.findById(libraryId, bookId)
            ?: throw BookNotFoundException(libraryId, bookId)

        return BookSearchResponse.BookAttribute(
            name = book.name,
            author = book.author,
            price = book.price,
        )
    }
}