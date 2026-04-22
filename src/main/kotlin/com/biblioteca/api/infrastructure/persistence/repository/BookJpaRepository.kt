package com.biblioteca.api.infrastructure.persistence.repository

import com.biblioteca.api.domain.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookJpaRepository : JpaRepository<Book, Int>
