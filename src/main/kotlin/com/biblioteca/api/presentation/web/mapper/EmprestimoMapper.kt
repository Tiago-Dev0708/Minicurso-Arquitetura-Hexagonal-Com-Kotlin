package com.biblioteca.api.presentation.web.mapper

import com.biblioteca.api.domain.model.Emprestimo
import com.biblioteca.api.presentation.web.dto.EmprestimoRequestDTO
import com.biblioteca.api.presentation.web.dto.EmprestimoResponseDTO

fun EmprestimoRequestDTO.toDomain(): Emprestimo {
    return Emprestimo(
        usuario = null,
        livro = null
    )
}

fun Emprestimo.toResponseDTO(): EmprestimoResponseDTO {
    return EmprestimoResponseDTO(
        id = this.id!!,
        usuarioId = this.usuario?.id,
        livroId = this.livro?.id,
        dataEmprestimo = this.dataEmprestimo,
        dataDevolucao = this.dataDevolucao,
        devolvido = this.devolvido,
        nomeUsuario = this.usuario?.name,
        tituloLivro = this.livro?.title
    )
}
