package com.j.lms.global.exception

import io.ktor.http.*

class BadRequestException(
    message: String,
) : CommonException(
    errorCode = "BAD_REQUEST",
    errorMessage = message,
    httpStatusCode = HttpStatusCode.BadRequest,
)