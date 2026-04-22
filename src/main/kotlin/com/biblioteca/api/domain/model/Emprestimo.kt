package com.biblioteca.api.domain.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.Objects

@Entity
@Table(name = "emprestimos")
class Emprestimo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    val usuario: User?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id")
    val livro: Book?,

    @Column(name = "data_emprestimo")
    val dataEmprestimo: LocalDate = LocalDate.now(),

    @Column(name = "data_devolucao")
    var dataDevolucao: LocalDate? = null,

    var devolvido: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val emprestimo = other as Emprestimo
        return id != null && id == emprestimo.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun toString(): String {
        return "Emprestimo(id=$id, usuarioId=${usuario?.id}, livroId=${livro?.id}, devolvido=$devolvido)"
    }
}
