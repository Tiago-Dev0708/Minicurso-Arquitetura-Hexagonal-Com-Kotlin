package com.biblioteca.api.domain.repository

import com.biblioteca.api.domain.model.Emprestimo
import java.util.Optional

interface EmprestimoRepository {
    fun save(emprestimo: Emprestimo): Emprestimo
    fun findById(id: Int): Optional<Emprestimo>
    fun findAll(): List<Emprestimo>
    fun deleteById(id: Int)
    fun findByLivroId(livroId: Int): List<Emprestimo>
}
