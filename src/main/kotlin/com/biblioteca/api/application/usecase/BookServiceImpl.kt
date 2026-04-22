package com.biblioteca.api.application.usecase

import com.biblioteca.api.domain.model.Book
import com.biblioteca.api.domain.repository.BookRepository
import com.biblioteca.api.domain.repository.EmprestimoRepository
import com.biblioteca.api.domain.service.BookService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class BookServiceImpl(
    private val bookRepository: BookRepository,
    private val emprestimoRepository: EmprestimoRepository
) : BookService {

    override fun createBook(book: Book): Book {
        return bookRepository.save(book)
    }

    @Transactional
    override fun deleteBook(id: Int) {
        val emprestimos = emprestimoRepository.findByLivroId(id)
        if (emprestimos.isNotEmpty()) {
            throw IllegalStateException("Não é possível deletar um livro que possui empréstimos vinculados.")
        }
        bookRepository.deleteById(id)
    }

    override fun listBooks(): List<Book> {
        return bookRepository.findAll()
    }

    override fun findById(id: Int): Optional<Book> {
        return bookRepository.findById(id)
    }
}
