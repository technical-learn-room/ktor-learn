package com.j.lms.domain.library.route

import io.ktor.routing.*

fun Route.configureLibraryRoute() {
    searchLibrary()
    registerLibrary()
    modifyLibrary()
    unregisterLibrary()
}

private fun Route.searchLibrary() {
    get(path = "/libraries") {

    }

    get(path = "/libraries/{libraryId}") {

    }
}

private fun Route.registerLibrary() {
    post(path = "/libraries") {

    }
}

private fun Route.modifyLibrary() {
    patch(path = "/libraries/{libraryId}/name") {

    }

    patch(path = "/libraries/{libraryId}/location") {

    }
}

private fun Route.unregisterLibrary() {
    delete(path = "/libraries/{libraryId}") {

    }
}