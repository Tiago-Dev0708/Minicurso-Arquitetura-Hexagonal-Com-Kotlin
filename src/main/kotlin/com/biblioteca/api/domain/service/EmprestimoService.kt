package com.biblioteca.api.domain.service

import com.biblioteca.api.domain.model.Emprestimo
import java.util.Optional

interface EmprestimoService {
    fun createEmprestimo(usuarioId: Int, livroId: Int): Emprestimo
    fun deleteEmprestimo(id: Int)
    fun listEmprestimos(): List<Emprestimo>
    fun devolverLivro(id: Int): Emprestimo
    fun adiarEmprestimo(id: Int): Emprestimo
    fun findById(id: Int): Optional<Emprestimo>
}
