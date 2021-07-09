package com.j.lms.global.configuration

import com.j.lms.domain.book.dao.InMemoryBookDataAccessor
import com.j.lms.domain.book.service.BookCreationService
import com.j.lms.domain.book.service.BookSearchService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindServices() {
    bind<BookCreationService>() with singleton { BookCreationService(InMemoryBookDataAccessor()) }
    bind<BookSearchService>() with singleton { BookSearchService(InMemoryBookDataAccessor()) }
}