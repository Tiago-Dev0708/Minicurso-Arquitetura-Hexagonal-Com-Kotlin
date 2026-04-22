package com.biblioteca.api.presentation.security

import com.biblioteca.api.infrastructure.persistence.repository.UserJpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private val repository: UserJpaRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return repository.findByEmail(username)
            .orElseThrow { UsernameNotFoundException("Usuário não encontrado") }
    }
}
