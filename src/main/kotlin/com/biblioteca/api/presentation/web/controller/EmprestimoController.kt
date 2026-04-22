package com.biblioteca.api.presentation.web.controller

import com.biblioteca.api.domain.service.EmprestimoService
import com.biblioteca.api.presentation.web.dto.EmprestimoRequestDTO
import com.biblioteca.api.presentation.web.dto.EmprestimoResponseDTO
import com.biblioteca.api.presentation.web.mapper.toDomain
import com.biblioteca.api.presentation.web.mapper.toResponseDTO
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/emprestimos")
class EmprestimoController(private val emprestimoService: EmprestimoService) {

    @PostMapping("/criar")
    fun createEmprestimo(@Valid @RequestBody emprestimoRequestDTO: EmprestimoRequestDTO): ResponseEntity<EmprestimoResponseDTO> {
        val createdEmprestimo = emprestimoService.createEmprestimo(emprestimoRequestDTO.usuarioId, emprestimoRequestDTO.livroId)
        return ResponseEntity.created(URI.create("/emprestimos/${createdEmprestimo.id}"))
        .body(createdEmprestimo.toResponseDTO())
    }

    @DeleteMapping("/deletar/{id}")
    fun deleteEmprestimo(@PathVariable id: Int): ResponseEntity<Void> {
        emprestimoService.deleteEmprestimo(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/listar")
    fun listEmprestimos(): ResponseEntity<List<EmprestimoResponseDTO>> {
        val emprestimos = emprestimoService.listEmprestimos().map { it.toResponseDTO() }
        return ResponseEntity.ok(emprestimos)
    }

    @PutMapping("/devolver/{id}")
    fun devolverLivro(@PathVariable id: Int): ResponseEntity<EmprestimoResponseDTO> {
        val emprestimo = emprestimoService.devolverLivro(id)
        return ResponseEntity.ok(emprestimo.toResponseDTO())
    }

    @PutMapping("/adiar/{id}")
    fun adiarEmprestimo(@PathVariable id: Int): ResponseEntity<EmprestimoResponseDTO> {
        val emprestimo = emprestimoService.adiarEmprestimo(id)
        return ResponseEntity.ok(emprestimo.toResponseDTO())
    }

    @GetMapping("/{id}")
    fun getEmprestimoById(@PathVariable id: Int): ResponseEntity<EmprestimoResponseDTO> {
        return emprestimoService.findById(id)
            .map { ResponseEntity.ok(it.toResponseDTO()) }
            .orElse(ResponseEntity.notFound().build())
    }
}
