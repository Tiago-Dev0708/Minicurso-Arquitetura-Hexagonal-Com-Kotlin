package com.biblioteca.api.presentation.web.controller

import com.biblioteca.api.domain.model.User
import com.biblioteca.api.presentation.security.TokenService
import com.biblioteca.api.presentation.web.dto.LoginRequestDTO
import com.biblioteca.api.presentation.web.dto.TokenResponseDTO
import com.biblioteca.api.presentation.web.mapper.toResponseDTO
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val tokenService: TokenService
) {

    @SecurityRequirements
    @PostMapping("/login")
    fun login(@RequestBody data: LoginRequestDTO): ResponseEntity<TokenResponseDTO> {
        val usernamePassword = UsernamePasswordAuthenticationToken(data.email, data.password)
        val auth = authenticationManager.authenticate(usernamePassword)
        
        val user = auth.principal as User
        val token = tokenService.generateToken(user)
        
        return ResponseEntity.ok(TokenResponseDTO(token, user.toResponseDTO(), user.role.name))
    }
}
