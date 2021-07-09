package com.j.lms.domain.book.entity

class Book(
    name: String,
    val author: String,
    val price: Int,
    val libraryId: Long,
) {
    var id: Long? = null

    var name = name
        private set

    fun modifyBookName(newName: String) {
        this.name = newName
    }
}