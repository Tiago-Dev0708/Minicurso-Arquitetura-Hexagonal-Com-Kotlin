package com.biblioteca.api.presentation.security

import com.biblioteca.api.domain.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.Date

@Service
class TokenService {

    @Value("\${api.security.token.secret:MySecretKeyMySecretKeyMySecretKeyMySecretKey}")
    private lateinit var secret: String

    @Value("\${api.security.token.expiration:86400000}")
    private var expiration: Long = 86400000

    private fun getSigningKey(): Key {
        return Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.email)
            .claim("id", user.id)
            .claim("role", user.role.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): String? {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .body
                .subject
        } catch (e: Exception) {
            null
        }
    }
}
