package com.biblioteca.api.infrastructure.persistence.repository

import com.biblioteca.api.domain.model.Emprestimo
import com.biblioteca.api.domain.repository.EmprestimoRepository
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import java.util.Optional

@Component
@Primary
class EmprestimoRepositoryAdapter(private val emprestimoJpaRepository: EmprestimoJpaRepository) : EmprestimoRepository {

    override fun save(emprestimo: Emprestimo): Emprestimo {
        return emprestimoJpaRepository.save(emprestimo)
    }

    override fun findById(id: Int): Optional<Emprestimo> {
        return emprestimoJpaRepository.findById(id)
    }

    override fun findAll(): List<Emprestimo> {
        return emprestimoJpaRepository.findAll()
    }

    override fun deleteById(id: Int) {
        emprestimoJpaRepository.deleteById(id)
    }

    override fun findByLivroId(livroId: Int): List<Emprestimo> {
        return emprestimoJpaRepository.findByLivroId(livroId)
    }
}
