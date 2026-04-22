package com.biblioteca.api.application.usecase

import com.biblioteca.api.domain.model.Role
import com.biblioteca.api.domain.model.User
import com.biblioteca.api.domain.repository.UserRepository
import com.biblioteca.api.domain.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun createUser(user: User): User {
        if (userRepository.findByCpf(user.cpf).isPresent) {
            throw IllegalArgumentException("CPF já cadastrado")
        }
        if (userRepository.findByEmail(user.email).isPresent) {
            throw IllegalArgumentException("Email já cadastrado")
        }
        
        user.setPassword(passwordEncoder.encode(user.password))
        
        user.role = if (user.email.endsWith("@admin.com")) Role.ADMIN else Role.USER
        
        return userRepository.save(user)
    }

    override fun deleteUser(id: Int) {
        userRepository.deleteById(id)
    }

    override fun listUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun findById(id: Int): Optional<User> {
        return userRepository.findById(id)
    }
}
