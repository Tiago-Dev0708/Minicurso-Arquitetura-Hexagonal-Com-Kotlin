package com.biblioteca.api.infrastructure.persistence.repository

import com.biblioteca.api.domain.model.Book
import com.biblioteca.api.domain.repository.BookRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import java.util.Optional

@Component
@Primary
class BookRepositoryAdapter(private val bookJpaRepository: BookJpaRepository) : BookRepository {

    override fun save(book: Book): Book {
        return bookJpaRepository.save(book)
    }

    override fun findById(id: Int): Optional<Book> {
        return bookJpaRepository.findById(id)
    }

    override fun findAll(): List<Book> {
        return bookJpaRepository.findAll()
    }

    override fun deleteById(id: Int) {
        bookJpaRepository.deleteById(id)
    }
}
