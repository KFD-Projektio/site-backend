package ru.projektio.backend.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.projektio.backend.database.repository.UserDao
import ru.projektio.backend.service.JwtTokenService
import java.lang.Exception
import org.springframework.security.core.authority.SimpleGrantedAuthority

@Component
class JwtAuthFilter(
    private val jwtTokenService: JwtTokenService,
    private val userDao: UserDao
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substring(7)
        try {
            val username = jwtTokenService.getLoginFromToken(jwt) ?: run {
                filterChain.doFilter(request, response)
                return
            }
            val user = userDao.findUserByLogin(username) ?: run {
                filterChain.doFilter(request, response)
                return
            }
            val authorities = user.roles.map{ SimpleGrantedAuthority(it.name) }
            val auth = UsernamePasswordAuthenticationToken(
                user,
                null,
                authorities
            )

            SecurityContextHolder.getContext().authentication = auth
        } catch (e: Exception) {
            println("TODO logger ")
            println(e.message)
        }

        filterChain.doFilter(request, response)
    }
}