package com.biblioteca.api.application.usecase

import com.biblioteca.api.domain.model.Emprestimo
import com.biblioteca.api.domain.repository.BookRepository
import com.biblioteca.api.domain.repository.EmprestimoRepository
import com.biblioteca.api.domain.repository.UserRepository
import com.biblioteca.api.domain.service.EmprestimoService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.Optional

@Service
class EmprestimoServiceImpl(
    private val emprestimoRepository: EmprestimoRepository,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository
) : EmprestimoService {

    @Transactional
    override fun createEmprestimo(usuarioId: Int, livroId: Int): Emprestimo {
        val livro = bookRepository.findById(livroId)
            .orElseThrow { IllegalArgumentException("Livro não encontrado.") }

        val usuario = userRepository.findById(usuarioId)
            .orElseThrow { IllegalArgumentException("Usuário não encontrado.") }

        if (livro.rented) {
            throw IllegalStateException("Estoque indisponível.")
        }

        if (!usuario.podeAlugar) {
            throw IllegalStateException("O usuário não pode alugar um livro.")
        }

        livro.qttAlugados += 1
        if (livro.qttEstoque - livro.qttAlugados == 0) {
            livro.rented = true
        }
        usuario.podeAlugar = false

        bookRepository.save(livro)
        userRepository.save(usuario)

        val emprestimo = Emprestimo(usuario = usuario, livro = livro)
        return emprestimoRepository.save(emprestimo)
    }

    override fun deleteEmprestimo(id: Int) {
        emprestimoRepository.deleteById(id)
    }

    override fun listEmprestimos(): List<Emprestimo> {
        return emprestimoRepository.findAll()
    }

    @Transactional
    override fun devolverLivro(id: Int): Emprestimo {
        val emprestimo = emprestimoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Empréstimo não encontrado") }

        if (emprestimo.devolvido) {
            throw IllegalStateException("Este livro já foi devolvido.")
        }

        val livro = emprestimo.livro
        val usuario = emprestimo.usuario

        emprestimo.dataDevolucao = LocalDate.now()
        emprestimo.devolvido = true

        if (usuario != null) {
            usuario.podeAlugar = true
            userRepository.save(usuario)
        }

        if (livro != null) {
            if (livro.qttAlugados > 0) {
                livro.qttAlugados -= 1
            }
            livro.rented = false
            bookRepository.save(livro)
        }

        return emprestimoRepository.save(emprestimo)
    }

    @Transactional
    override fun adiarEmprestimo(id: Int): Emprestimo {
        val emprestimo = emprestimoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Empréstimo não encontrado") }

        if (emprestimo.devolvido) {
            throw IllegalStateException("O empréstimo já foi devolvido. Não é possível adiar.")
        }

        emprestimo.dataDevolucao = emprestimo.dataDevolucao?.plusDays(7)
        return emprestimoRepository.save(emprestimo)
    }

    override fun findById(id: Int): Optional<Emprestimo> {
        return emprestimoRepository.findById(id)
    }
}
