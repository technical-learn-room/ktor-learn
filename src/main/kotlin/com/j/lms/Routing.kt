package com.j.lms

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/") {
        }
        a()
    }
}

fun Route.a() {
    get("/a") {
        call.respondText("a")
    }
}
