package com.biblioteca.api.infrastructure.config

import com.biblioteca.api.presentation.security.SecurityFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val securityFilter: SecurityFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.configurationSource(corsConfigurationSource()) }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users/adicionar").permitAll()
                    
                    .requestMatchers(HttpMethod.POST, "/books/adicionar").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/books/deletar/**").hasRole("ADMIN")
                    
                    .requestMatchers(HttpMethod.PUT, "/emprestimos/devolver/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/emprestimos/deletar/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/emprestimos/listar").hasRole("ADMIN")
                    
                    .requestMatchers(HttpMethod.GET, "/users/listar").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/users/deletar/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/users/{id}").hasRole("ADMIN")
                    
                    .requestMatchers(HttpMethod.GET, "/books/listar").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/books/**").hasRole("USER")
                    
                    .requestMatchers(HttpMethod.POST, "/emprestimos/criar").hasRole("USER")
                    .requestMatchers(HttpMethod.PUT, "/emprestimos/adiar/**").hasRole("USER")
                    .requestMatchers(HttpMethod.GET, "/emprestimos/**").hasRole("USER")
                    
                    .anyRequest().authenticated()
            }
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOriginPatterns = listOf(
            "http://localhost:*",
            "http://10.0.2.2:*",
            "http://192.168.*.*:*",
            "http://127.0.0.1:*"
        )
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true
        configuration.maxAge = 3600L

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
