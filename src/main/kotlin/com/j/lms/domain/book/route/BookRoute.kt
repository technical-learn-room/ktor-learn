package com.j.lms.domain.book.route

import com.j.lms.domain.book.route.request.BookCreationRequest
import com.j.lms.domain.book.service.BookCreationService
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
    get(path = "/books") {

    }

    get(path = "/books/{bookId}") {

    }
}

private fun Route.registerBook() {
    val bookCreationService by closestDI().instance<BookCreationService>()

    post(path = "/books") {
        val request = call.receive<BookCreationRequest>()

        bookCreationService.createBook(
            bookName = request.book.name,
            bookAuthor = request.book.author,
            bookPrice = request.book.price,
            libraryId = request.book.libraryId,
        )

        call.respond(HttpStatusCode.Created)
    }
}

private fun Route.modifyBook() {
    patch(path = "/books/{bookId}/name") {

    }
}

private fun Route.unregisterBook() {
    delete(path = "/books/{bookId}") {

    }
}