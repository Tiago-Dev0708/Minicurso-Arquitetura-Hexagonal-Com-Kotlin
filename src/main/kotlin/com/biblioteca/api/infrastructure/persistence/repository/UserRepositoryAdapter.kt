package com.biblioteca.api.infrastructure.persistence.repository

import com.biblioteca.api.domain.model.User
import com.biblioteca.api.domain.repository.UserRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import java.util.Optional

@Component
@Primary
class UserRepositoryAdapter(private val userJpaRepository: UserJpaRepository) : UserRepository {

    override fun save(user: User): User {
        return userJpaRepository.save(user)
    }

    override fun findById(id: Int): Optional<User> {
        return userJpaRepository.findById(id)
    }

    override fun findAll(): List<User> {
        return userJpaRepository.findAll()
    }

    override fun deleteById(id: Int) {
        userJpaRepository.deleteById(id)
    }

    override fun findByCpf(cpf: String): Optional<User> {
        return userJpaRepository.findByCpf(cpf)
    }

    override fun findByEmail(email: String): Optional<User> {
        return userJpaRepository.findByEmail(email)
    }
}
