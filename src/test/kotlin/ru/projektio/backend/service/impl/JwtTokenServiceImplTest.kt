package ru.projektio.backend.service.impl

import io.jsonwebtoken.MalformedJwtException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import ru.projektio.backend.config.properties.JwtProperties
import ru.projektio.backend.database.entity.UserEntity
import java.util.*

class JwtTokenServiceImplTest {
    private lateinit var jwtTokenService: JwtTokenServiceImpl
    private lateinit var jwtProperties: JwtProperties
    private lateinit var user: UserEntity

    @BeforeEach
    fun setUp() {
        jwtProperties = JwtProperties(
            "test-secret-key-1234567890-1234567890-1234567890",
            3600000,
            86400000
        )

        jwtTokenService = JwtTokenServiceImpl(jwtProperties)

        user = mock(UserEntity::class.java)

        `when`(user.id).thenReturn(1)
        `when`(user.login).thenReturn("mostanin")
        `when`(user.email).thenReturn("okroxlonel7@gmail.com")
    }

    @Test
    fun `createAccessToken should generate valid token`() {
        val token = jwtTokenService.createAccessToken(user)
        assertNotNull(token)
        assertTrue(token.isNotBlank())
    }

    @Test
    fun `getLoginFromToken should return correct login`() {
        val token = jwtTokenService.createAccessToken(user)
        val login = jwtTokenService.getLoginFromToken(token)
        assertEquals("mostanin", login)
    }

    @Test
    fun `isTokenExpired should return false for fresh token`() {
        val token = jwtTokenService.createAccessToken(user)
        assertFalse(jwtTokenService.isTokenExpired(token))
    }

    @Test
    fun `isTokenExpired should return true for expired token`() {
        val expiredProperties = JwtProperties(
            key = jwtProperties.key,
            accessTokenExpirationDate = -3600000,
            refreshTokenExpirationDate = jwtProperties.refreshTokenExpirationDate
        )
        val expiredService = JwtTokenServiceImpl(expiredProperties)
        val expiredToken = expiredService.createAccessToken(user)

        assertTrue(expiredService.isTokenExpired(expiredToken))
    }

    @Test
    fun `validateToken should return false for wrong user`() {
        val token = jwtTokenService.createAccessToken(user)
        val otherUser = mock(UserEntity::class.java)
        `when`(otherUser.login).thenReturn("wrongUser")

        assertFalse(jwtTokenService.validateToken(token, otherUser))
    }

    @Test
    fun wrongTokenSignature() {
        val token = "eto.token.tipa"

        assertThrows<MalformedJwtException> {
            jwtTokenService.getLoginFromToken(token)
        }
    }

    @Test
    fun `getAccessTokenExpiration should be in future`() {
        val expiration = jwtTokenService.getAccessTokenExpirationDate()
        assertTrue(expiration.after(Date(System.currentTimeMillis())))
    }

//    @Test
//    fun `getRefreshTokenExpiration should be in future`() = TODO("Finish roles")
}