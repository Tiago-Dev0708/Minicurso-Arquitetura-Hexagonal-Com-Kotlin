package com.biblioteca.api.presentation.web.controller

import com.biblioteca.api.domain.service.UserService
import com.biblioteca.api.presentation.web.dto.UserRequestDTO
import com.biblioteca.api.presentation.web.dto.UserResponseDTO
import com.biblioteca.api.presentation.web.mapper.toDomain
import com.biblioteca.api.presentation.web.mapper.toResponseDTO
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @SecurityRequirements
    @PostMapping("/adicionar")
    fun createUser(@Valid @RequestBody userRequestDTO: UserRequestDTO): ResponseEntity<UserResponseDTO> {
        val user = userRequestDTO.toDomain()
        val createdUser = userService.createUser(user)
        return ResponseEntity.created(URI.create("/users/${createdUser.id}"))
        .body(createdUser.toResponseDTO())
    }

    @DeleteMapping("/deletar/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/listar")
    fun listUsers(): ResponseEntity<List<UserResponseDTO>> {
        val users = userService.listUsers().map { it.toResponseDTO() }
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): ResponseEntity<UserResponseDTO> {
        return userService.findById(id)
            .map { ResponseEntity.ok(it.toResponseDTO()) }
            .orElse(ResponseEntity.notFound().build())
    }
}
