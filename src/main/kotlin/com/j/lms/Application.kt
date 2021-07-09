package com.j.lms

import com.j.lms.global.configuration.bindDataAccessors
import com.j.lms.global.configuration.bindServices
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.kodein.di.ktor.di

fun main() {
    embeddedServer(Netty, port = 6180, host = "0.0.0.0") {

        install(ContentNegotiation) {
            jackson()
        }
        install(CallLogging)
//        install(StatusPages) {
//
//        }

        di {
            bindServices()
            bindDataAccessors()
        }

        routing {
            apiRoute()
        }
    }.start(wait = true)
}