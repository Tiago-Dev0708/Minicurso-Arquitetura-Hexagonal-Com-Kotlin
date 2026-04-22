package com.biblioteca.api.domain.service

import com.biblioteca.api.domain.model.Book
import java.util.Optional

interface BookService {
    fun createBook(book: Book): Book
    fun deleteBook(id: Int)
    fun listBooks(): List<Book>
    fun findById(id: Int): Optional<Book>
}
