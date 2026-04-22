package com.biblioteca.api.domain.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Objects

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(unique = true)
    val cpf: String,

    val name: String,

    @Column(unique = true)
    val email: String,

    @Column(name = "password")
    private var passwordHash: String = "",

    var podeAlugar: Boolean = true,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: Role = Role.USER,

    @OneToMany(mappedBy = "usuario", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val emprestimos: List<Emprestimo> = emptyList()
) : UserDetails {

    fun setPassword(password: String) {
        this.passwordHash = password
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return when (role) {
            Role.ADMIN -> listOf(
                SimpleGrantedAuthority("ROLE_ADMIN"),
                SimpleGrantedAuthority("ROLE_USER")
            )
            Role.USER -> listOf(SimpleGrantedAuthority("ROLE_USER"))
        }
    }

    override fun getPassword(): String = passwordHash

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val user = other as User
        return id != null && id == user.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', email='$email', podeAlugar=$podeAlugar)"
    }
}
