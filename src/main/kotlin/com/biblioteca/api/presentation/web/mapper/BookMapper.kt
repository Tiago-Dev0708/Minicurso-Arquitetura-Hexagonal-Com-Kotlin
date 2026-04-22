package com.biblioteca.api.presentation.web.mapper

import com.biblioteca.api.domain.model.Book
import com.biblioteca.api.presentation.web.dto.BookRequestDTO
import com.biblioteca.api.presentation.web.dto.BookResponseDTO
import java.util.Base64

fun BookRequestDTO.toDomain(): Book {
    return Book(
        title = this.title,
        author = this.author,
        publicationYear = this.publicationYear,
        gender = this.gender,
        qttEstoque = this.qttEstoque,
        sobre = this.sobre,
        capaImagem = this.capaImagemBase64?.let { 
            Base64.getDecoder().decode(it) 
        }
    )
}

fun Book.toResponseDTO(): BookResponseDTO {
    return BookResponseDTO(
        id = this.id!!,
        title = this.title,
        author = this.author,
        publicationYear = this.publicationYear,
        gender = this.gender,
        qttEstoque = this.qttEstoque,
        qttAlugados = this.qttAlugados,
        rented = this.rented,
        sobre = this.sobre,
        capaImagemBase64 = this.capaImagem?.let { 
            Base64.getEncoder().encodeToString(it) 
        }
    )
}
