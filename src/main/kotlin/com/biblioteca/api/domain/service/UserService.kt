package com.biblioteca.api.domain.service

import com.biblioteca.api.domain.model.User
import java.util.Optional

interface UserService {
    fun createUser(user: User): User
    fun deleteUser(id: Int)
    fun listUsers(): List<User>
    fun findById(id: Int): Optional<User>
}
