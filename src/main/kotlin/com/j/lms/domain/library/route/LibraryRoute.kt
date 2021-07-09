package com.j.lms.domain.library.route

import com.j.lms.domain.library.route.request.LibraryCreationRequest
import com.j.lms.domain.library.route.request.LibraryLocationModificationRequest
import com.j.lms.domain.library.route.request.LibraryNameModificationRequest
import com.j.lms.domain.library.route.response.LibraryAllSearchResponse
import com.j.lms.domain.library.service.LibraryCreationService
import com.j.lms.domain.library.service.LibraryDeletionService
import com.j.lms.domain.library.service.LibraryModificationService
import com.j.lms.domain.library.service.LibrarySearchService
import com.j.lms.global.exception.BadRequestException
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.configureLibraryRoute() {
    searchLibrary()
    registerLibrary()
    modifyLibrary()
    unregisterLibrary()
}

private fun Route.searchLibrary() {
    val librarySearchService by closestDI().instance<LibrarySearchService>()

    get(path = "/libraries") {
        call.respond(
            status = HttpStatusCode.OK,
            message = LibraryAllSearchResponse(
                libraries = librarySearchService.searchAll(),
            ),
        )
    }

    get(path = "/libraries/{libraryId}") {
        val libraryId = call.parameters["libraryId"].validateNumberString()

        call.respond(
            status = HttpStatusCode.OK,
            message = librarySearchService.search(libraryId)
        )
    }
}

private fun Route.registerLibrary() {
    val libraryCreationService by closestDI().instance<LibraryCreationService>()

    post(path = "/libraries") {
        val request = call.receive<LibraryCreationRequest>()

        libraryCreationService.create(
            libraryName = request.library.name,
            libraryLocation = request.library.location,
        )

        call.respond(HttpStatusCode.Created)
    }
}

private fun Route.modifyLibrary() {
    val libraryModificationService by closestDI().instance<LibraryModificationService>()

    patch(path = "/libraries/{libraryId}/name") {
        val libraryId = call.parameters["libraryId"].validateNumberString()
        val request = call.receive<LibraryNameModificationRequest>()

        libraryModificationService.modifyName(
            libraryId = libraryId,
            newLibraryName = request.newLibraryName,
        )

        call.respond(HttpStatusCode.OK)
    }

    patch(path = "/libraries/{libraryId}/location") {
        val libraryId = call.parameters["libraryId"].validateNumberString()
        val request = call.receive<LibraryLocationModificationRequest>()

        libraryModificationService.modifyLocation(
            libraryId = libraryId,
            newLibraryLocation = request.newLibraryLocation,
        )

        call.respond(HttpStatusCode.OK)
    }
}

private fun Route.unregisterLibrary() {
    val libraryDeletionService by closestDI().instance<LibraryDeletionService>()

    delete(path = "/libraries/{libraryId}") {
        val libraryId = call.parameters["libraryId"].validateNumberString()

        libraryDeletionService.delete(
            libraryId = libraryId,
        )

        call.respond(HttpStatusCode.OK)
    }
}

private fun String?.validateNumberString() = this?.toLongOrNull() ?: throw BadRequestException("libraryId가 ${this}일 수 없습니다.")