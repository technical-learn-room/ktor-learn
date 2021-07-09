package com.j.lms.global.exception

data class CommonExceptionResponse(
    val error: ExceptionAttribute,
) {

    data class ExceptionAttribute(
        val code: String,
        val message: String,
    )
}