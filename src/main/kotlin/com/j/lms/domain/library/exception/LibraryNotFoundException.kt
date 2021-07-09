package com.j.lms.domain.library.exception

import com.j.lms.global.exception.CommonException
import io.ktor.http.*

class LibraryNotFoundException(
    libraryId: Long,
) : CommonException(
    errorCode = "LIBRARY_NOT_FOUND",
    errorMessage = "libraryId가 ${libraryId}인 도서관을 찾을 수 없습니다.",
    httpStatusCode = HttpStatusCode.NotFound,
)