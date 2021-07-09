package com.j.lms.global.configuration

import com.j.lms.domain.book.dao.BookDataAccessor
import com.j.lms.domain.book.dao.InMemoryBookDataAccessor
import com.j.lms.domain.book.entity.Book
import com.j.lms.domain.book.service.BookCreationService
import com.j.lms.domain.book.service.BookDeletionService
import com.j.lms.domain.book.service.BookModificationService
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
    val libraryDataAccessor: LibraryDataAccessor<Library, Long> = InMemoryLibraryDataAccessor()
    val bookDataAccessor: BookDataAccessor<Book, Long> = InMemoryBookDataAccessor()

    bindLibraryServices(libraryDataAccessor)
    bindBookServices(libraryDataAccessor, bookDataAccessor)
}

private fun DI.MainBuilder.bindLibraryServices(
    libraryDataAccessor: LibraryDataAccessor<Library, Long>,
) {
    bind<LibraryCreationService>() with singleton { LibraryCreationService(libraryDataAccessor) }
    bind<LibrarySearchService>() with singleton { LibrarySearchService(libraryDataAccessor) }
    bind<LibraryModificationService>() with singleton { LibraryModificationService(libraryDataAccessor) }
    bind<LibraryDeletionService>() with singleton { LibraryDeletionService(libraryDataAccessor) }
}

private fun DI.MainBuilder.bindBookServices(
    libraryDataAccessor: LibraryDataAccessor<Library, Long>,
    bookDataAccessor: BookDataAccessor<Book, Long>,
) {
    bind<BookCreationService>() with singleton { BookCreationService(libraryDataAccessor, bookDataAccessor) }
    bind<BookSearchService>() with singleton { BookSearchService(bookDataAccessor) }
    bind<BookModificationService>() with singleton { BookModificationService(bookDataAccessor) }
    bind<BookDeletionService>() with singleton { BookDeletionService(bookDataAccessor) }
}