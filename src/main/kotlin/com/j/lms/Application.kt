package com.j.lms

import com.j.lms.global.configuration.bindServices
import com.j.lms.global.exception.CommonException
import com.j.lms.global.exception.CommonExceptionResponse
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.response.*
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
        install(StatusPages) {
            exception<CommonException> { e ->
                call.respond(
                    status = e.httpStatusCode,
                    message = CommonExceptionResponse(
                        error = CommonExceptionResponse.ExceptionAttribute(
                            code = e.errorCode,
                            message = e.errorMessage,
                        )
                    )
                )
            }
        }

        di {
            bindServices()
        }

        routing {
            apiRoute()
        }
    }.start(wait = true)
}