package com.biblioteca.api.presentation.security

import com.biblioteca.api.infrastructure.persistence.repository.UserJpaRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(
    private val tokenService: TokenService,
    private val userRepository: UserJpaRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = recoverToken(request)
        if (token != null) {
            val email = tokenService.validateToken(token)
            if (email != null) {
                val user = userRepository.findByEmail(email).orElse(null)
                if (user != null) {
                    val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null) return null
        return authHeader.replace("Bearer ", "")
    }
}
