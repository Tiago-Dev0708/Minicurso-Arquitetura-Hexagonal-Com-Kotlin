package com.biblioteca.api.infrastructure.persistence.repository

import com.biblioteca.api.domain.model.Emprestimo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface EmprestimoJpaRepository : JpaRepository<Emprestimo, Int> {
    fun findByLivroId(livroId: Int): List<Emprestimo>
}
