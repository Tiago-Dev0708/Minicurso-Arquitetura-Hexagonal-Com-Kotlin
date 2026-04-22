package com.biblioteca.api.domain.repository

import com.biblioteca.api.domain.model.User
import java.util.Optional

interface UserRepository {
    fun save(user: User): User
    fun findById(id: Int): Optional<User>
    fun findAll(): List<User>
    fun deleteById(id: Int)
    fun findByCpf(cpf: String): Optional<User>
    fun findByEmail(email: String): Optional<User>
}
