package com.biblioteca.api.presentation.web.controller

import com.biblioteca.api.domain.service.BookService
import com.biblioteca.api.presentation.web.dto.BookRequestDTO
import com.biblioteca.api.presentation.web.dto.BookResponseDTO
import com.biblioteca.api.presentation.web.mapper.toDomain
import com.biblioteca.api.presentation.web.mapper.toResponseDTO
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {

    @PostMapping("/adicionar")
    fun createBook(@Valid @RequestBody bookRequestDTO: BookRequestDTO): ResponseEntity<BookResponseDTO> {
        val book = bookRequestDTO.toDomain()
        val createdBook = bookService.createBook(book)
        return ResponseEntity.created(URI.create("/books/${createdBook.id}"))
        .body(createdBook.toResponseDTO())
    }

    @DeleteMapping("/deletar/{id}")
    fun deleteBook(@PathVariable id: Int): ResponseEntity<Void> {
        bookService.deleteBook(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/listar")
    fun listBooks(): ResponseEntity<List<BookResponseDTO>> {
        val books = bookService.listBooks().map { it.toResponseDTO() }
        return ResponseEntity.ok(books)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Int): ResponseEntity<BookResponseDTO> {
        return bookService.findById(id)
            .map { ResponseEntity.ok(it.toResponseDTO()) }
            .orElse(ResponseEntity.notFound().build())
    }
}
