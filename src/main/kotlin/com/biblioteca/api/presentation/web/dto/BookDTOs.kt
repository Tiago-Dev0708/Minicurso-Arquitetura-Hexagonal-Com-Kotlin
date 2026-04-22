package com.biblioteca.api.presentation.web.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class BookRequestDTO(
    @field:NotEmpty(message = "Título não pode ser vazio")
    val title: String,

    @field:NotEmpty(message = "Autor não pode ser vazio")
    val author: String,

    @field:NotNull(message = "Ano de publicação não pode ser nulo")
    val publicationYear: Int,

    @field:NotEmpty(message = "Gênero não pode ser vazio")
    val gender: String,

    @field:NotNull(message = "Quantidade em estoque não pode ser nula")
    @field:Min(value = 1, message = "Estoque deve ser de no mínimo 1")
    val qttEstoque: Int,

    @field:NotEmpty(message = "O campo sobre não pode ser vazio, por favor, adicione uma descrição do livro")
    val sobre: String,

    val capaImagemBase64: String? = null
)

data class BookResponseDTO(
    val id: Int,
    val title: String,
    val author: String,
    val publicationYear: Int,
    val gender: String,
    val qttEstoque: Int,
    val qttAlugados: Int,
    val rented: Boolean,
    val sobre: String,
    val capaImagemBase64: String? = null
)
