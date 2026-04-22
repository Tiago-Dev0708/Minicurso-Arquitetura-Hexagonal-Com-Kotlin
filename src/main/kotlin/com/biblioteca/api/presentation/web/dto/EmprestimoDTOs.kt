package com.biblioteca.api.presentation.web.dto

import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class EmprestimoRequestDTO(
    @field:NotNull(message = "ID do usuário não pode ser nulo")
    val usuarioId: Int,

    @field:NotNull(message = "ID do livro não pode ser nulo")
    val livroId: Int
)

data class EmprestimoResponseDTO(
    val id: Int,
    val usuarioId: Int?,
    val livroId: Int?,
    val dataEmprestimo: LocalDate,
    val dataDevolucao: LocalDate?,
    val devolvido: Boolean,
    val nomeUsuario: String?,
    val tituloLivro: String?
)
