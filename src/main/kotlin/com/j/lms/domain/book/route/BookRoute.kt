package com.j.lms.domain.book.route

import io.ktor.routing.*

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
    post(path = "/books") {

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