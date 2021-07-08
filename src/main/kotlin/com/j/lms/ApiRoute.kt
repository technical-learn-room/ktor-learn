package com.j.lms

import com.j.lms.domain.book.configureBookRoute
import com.j.lms.domain.library.configureLibraryRoute
import io.ktor.routing.*

fun Routing.apiRoute() {
    route("/api/v1") {
        configureLibraryRoute()
        configureBookRoute()
    }
}