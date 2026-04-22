package com.biblioteca.api.presentation.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class LoginRequestDTO(
    @field:NotEmpty(message = "Email não pode ser vazio")
    @field:Email(message = "Email deve ser válido")
    val email: String,

    @field:NotEmpty(message = "Senha não pode ser vazia")
    val password: String
)

data class TokenResponseDTO(
    val token: String,
    val user: UserResponseDTO,
    val role: String
)
