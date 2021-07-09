package com.j.lms

import com.j.lms.domain.book.route.configureBookRoute
import com.j.lms.domain.library.route.configureLibraryRoute
import io.ktor.routing.*

fun Routing.apiRoute() {
    route("/api/v1") {
        configureLibraryRoute()
        configureBookRoute()
    }
}