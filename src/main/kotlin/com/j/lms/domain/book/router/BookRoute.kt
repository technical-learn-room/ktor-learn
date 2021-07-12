package com.j.lms.domain.book.router

import com.j.lms.domain.book.router.request.BookCreationRequest
import com.j.lms.domain.book.router.request.BookNameModificationRequest
import com.j.lms.domain.book.router.response.BookAllSearchResponse
import com.j.lms.domain.book.router.response.BookSearchResponse
import com.j.lms.domain.book.service.BookCreationService
import com.j.lms.domain.book.service.BookDeletionService
import com.j.lms.domain.book.service.BookModificationService
import com.j.lms.domain.book.service.BookSearchService
import com.j.lms.global.exception.BadRequestException
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.configureBookRoute() {
    searchBook()
    registerBook()
    modifyBook()
    unregisterBook()
}

private fun Route.searchBook() {
    val bookSearchService by closestDI().instance<BookSearchService>()

    get(path = "/libraries/{libraryId}/books") {
        val libraryId = call.parameters["libraryId"].validateNumberString()

        call.respond(
            status = HttpStatusCode.OK,
            message = BookAllSearchResponse(
                books = bookSearchService.searchAll(libraryId),
            ),
        )
    }

    get(path = "/libraries/{libraryId}/books/{bookId}") {
        val libraryId = call.parameters["libraryId"].validateNumberString()
        val bookId = call.parameters["bookId"].validateNumberString()

        call.respond(
            status = HttpStatusCode.OK,
            message = BookSearchResponse(
                book = bookSearchService.search(libraryId, bookId),
            ),
        )
    }
}

private fun Route.registerBook() {
    val bookCreationService by closestDI().instance<BookCreationService>()

    post(path = "/libraries/{libraryId}/books") {
        val request = call.receive<BookCreationRequest>()

        bookCreationService.create(
            bookName = request.book.name,
            bookAuthor = request.book.author,
            bookPrice = request.book.price,
            libraryId = request.book.libraryId,
        )

        call.respond(HttpStatusCode.Created)
    }
}

private fun Route.modifyBook() {
    val bookModificationService by closestDI().instance<BookModificationService>()

    patch(path = "/libraries/{libraryId}/books/{bookId}/name") {
        val libraryId = call.parameters["libraryId"].validateNumberString()
        val bookId = call.parameters["bookId"].validateNumberString()

        val request = call.receive<BookNameModificationRequest>()

        bookModificationService.modifyName(
            libraryId = libraryId,
            bookId = bookId,
            newBookName = request.newBookName,
        )

        call.respond(HttpStatusCode.OK)
    }
}

private fun Route.unregisterBook() {
    val bookDeletionService by closestDI().instance<BookDeletionService>()

    delete(path = "/libraries/{libraryId}/books/{bookId}") {
        val libraryId = call.parameters["libraryId"].validateNumberString()
        val bookId = call.parameters["bookId"].validateNumberString()

        bookDeletionService.delete(
            libraryId = libraryId,
            bookId = bookId,
        )

        call.respond(HttpStatusCode.OK)
    }
}

private fun String?.validateNumberString() = this?.toLongOrNull() ?: throw BadRequestException("libraryId가 ${this}일 수 없습니다.")