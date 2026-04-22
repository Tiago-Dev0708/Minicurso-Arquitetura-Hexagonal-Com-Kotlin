package com.biblioteca.api.domain.model

import jakarta.persistence.*
import java.util.Objects

@Entity
@Table(name = "books")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    val title: String,
    val author: String,

    @Column(name = "publication_year")
    val publicationYear: Int,

    val gender: String,

    @Column(name = "qtt_estoque")
    val qttEstoque: Int,

    @Column(name = "qtt_alugados")
    var qttAlugados: Int = 0,

    var rented: Boolean = false,

    var sobre: String,

    @Column(name = "capa_imagem", columnDefinition = "BYTEA")
    var capaImagem: ByteArray? = null,

    @OneToMany(mappedBy = "livro", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val emprestimos: List<Emprestimo> = emptyList()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val book = other as Book
        return id != null && id == book.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun toString(): String {
        return "Book(id=$id, title='$title', author='$author', rented=$rented, sobre='$sobre')"
    }
}
