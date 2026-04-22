package com.biblioteca.api.domain.repository

import com.biblioteca.api.domain.model.Book
import java.util.Optional

interface BookRepository {
    fun save(book: Book): Book
    fun findById(id: Int): Optional<Book>
    fun findAll(): List<Book>
    fun deleteById(id: Int)
}
