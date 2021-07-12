package com.j.lms.domain.library.router

import com.j.lms.apiRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import org.junit.Test

class LibraryRouterTest {

    @Test
    fun ` `() = withTestApplication({
        install(ContentNegotiation) { jackson() }
        routing { apiRoute() }
    }) {
        val call = handleRequest(
            method = HttpMethod.Get,
            uri = "/api/v1/libraries",
        )

        println("status: ${call.response.status()}")
        println("content: ${call.response.content}")
    }
}