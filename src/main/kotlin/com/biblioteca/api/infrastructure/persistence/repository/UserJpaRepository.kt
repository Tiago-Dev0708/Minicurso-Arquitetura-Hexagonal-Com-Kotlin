package com.biblioteca.api.infrastructure.persistence.repository

import com.biblioteca.api.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserJpaRepository : JpaRepository<User, Int> {
    fun findByCpf(cpf: String): Optional<User>
    fun findByEmail(email: String): Optional<User>
}
