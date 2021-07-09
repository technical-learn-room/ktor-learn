package com.j.lms.domain.book.exception

import com.j.lms.global.exception.CommonException
import io.ktor.http.*

class BookNotFoundException(
    bookId: Long,
    libraryId: Long,
) : CommonException(
    errorCode = "BOOK_NOT_FOUND",
    errorMessage = "bookId가 ${bookId}이고 libraryId가 ${libraryId}인 책을 찾을 수 없습니다.",
    httpStatusCode = HttpStatusCode.NotFound,
)