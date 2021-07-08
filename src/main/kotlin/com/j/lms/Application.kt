package com.j.lms

import com.j.lms.domain.apiRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 6180, host = "0.0.0.0") {

//        install(ContentNegotiation) {}
        install(CallLogging)
//        install(StatusPages) {
//
//        }



        routing {
            apiRoute()
        }
    }.start(wait = true)
}