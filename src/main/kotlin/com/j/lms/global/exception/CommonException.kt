package com.j.lms.global.exception

import io.ktor.http.*

open class CommonException(
    val errorCode: String,
    val errorMessage: String,
    val httpStatusCode: HttpStatusCode,
) : RuntimeException()