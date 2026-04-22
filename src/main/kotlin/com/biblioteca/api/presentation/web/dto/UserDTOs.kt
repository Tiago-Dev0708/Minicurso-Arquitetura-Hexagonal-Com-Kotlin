package com.biblioteca.api.presentation.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class UserRequestDTO(
    @field:NotEmpty(message = "CPF não pode ser vazio")
    @field:Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    val cpf: String,

    @field:NotEmpty(message = "Nome não pode ser vazio")
    val name: String,

    @field:NotEmpty(message = "Email não pode ser vazio")
    @field:Email(message = "Email deve ser válido")
    val email: String,

    @field:NotEmpty(message = "Senha não pode ser vazia")
    @field:Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    val password: String,

    val podeAlugar: Boolean = true
)

data class UserResponseDTO(
    val id: Int,
    val cpf: String,
    val name: String,
    val email: String,
    val podeAlugar: Boolean,
    val role: String
)
