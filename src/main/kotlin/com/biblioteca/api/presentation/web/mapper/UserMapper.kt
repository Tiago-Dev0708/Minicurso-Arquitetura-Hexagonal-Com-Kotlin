package com.biblioteca.api.presentation.web.mapper

import com.biblioteca.api.domain.model.User
import com.biblioteca.api.presentation.web.dto.UserRequestDTO
import com.biblioteca.api.presentation.web.dto.UserResponseDTO

fun UserRequestDTO.toDomain(): User {
    val user = User(
        cpf = this.cpf,
        name = this.name,
        email = this.email,
        podeAlugar = this.podeAlugar
    )
    user.setPassword(this.password)
    return user
}

fun User.toResponseDTO(): UserResponseDTO {
    return UserResponseDTO(
        id = this.id!!,
        cpf = this.cpf,
        name = this.name,
        email = this.email,
        podeAlugar = this.podeAlugar,
        role = this.role.name
    )
}
