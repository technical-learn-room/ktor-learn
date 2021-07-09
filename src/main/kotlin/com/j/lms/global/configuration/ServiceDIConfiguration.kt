package com.j.lms.global.configuration

import com.j.lms.domain.book.dao.InMemoryBookDataAccessor
import com.j.lms.domain.book.service.BookCreationService
import com.j.lms.domain.book.service.BookSearchService
import com.j.lms.domain.library.dao.InMemoryLibraryDataAccessor
import com.j.lms.domain.library.dao.LibraryDataAccessor
import com.j.lms.domain.library.entity.Library
import com.j.lms.domain.library.service.LibraryCreationService
import com.j.lms.domain.library.service.LibraryDeletionService
import com.j.lms.domain.library.service.LibraryModificationService
import com.j.lms.domain.library.service.LibrarySearchService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindServices() {
    bind<BookCreationService>() with singleton { BookCreationService(InMemoryBookDataAccessor()) }
    bind<BookSearchService>() with singleton { BookSearchService(InMemoryBookDataAccessor()) }

    bindLibraryServices()
}

private fun DI.MainBuilder.bindLibraryServices() {
    val libraryDataAccessor: LibraryDataAccessor<Library, Long> = InMemoryLibraryDataAccessor()

    bind<LibraryCreationService>() with singleton { LibraryCreationService(libraryDataAccessor) }
    bind<LibrarySearchService>() with singleton { LibrarySearchService(libraryDataAccessor) }
    bind<LibraryModificationService>() with singleton { LibraryModificationService(libraryDataAccessor) }
    bind<LibraryDeletionService>() with singleton { LibraryDeletionService(libraryDataAccessor) }
}