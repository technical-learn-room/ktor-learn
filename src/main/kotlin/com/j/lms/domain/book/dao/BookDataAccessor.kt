package com.j.lms.domain.book.dao

interface BookDataAccessor<E : Any, ID : Any> {
    fun save(entity: E): E
    fun findById(id: ID): E
    fun findAll(): List<E>
//    fun modify
}