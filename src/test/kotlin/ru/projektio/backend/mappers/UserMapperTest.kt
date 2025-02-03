package ru.projektio.backend.mappers

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import ru.projektio.backend.database.entity.UserEntity
import java.time.LocalDateTime

class UserMapperTest {
    @Test
    fun `entityToUserRegisterResponse should map UserEntity to RegisterUserResponse`() {
        val userMapper = UserMapper()
        val userEntity = mock(UserEntity::class.java)
        val id = 1L
        val login = "testLogin"
        val email = "testEmail"
        val createdAt = LocalDateTime.now()

        `when`(userEntity.id).thenReturn(1L)
        `when`(userEntity.login).thenReturn("testLogin")
        `when`(userEntity.email).thenReturn("testEmail")
        `when`(userEntity.createdAt).thenReturn(createdAt)

        val result = userMapper.entityToUserRegisterResponse(userEntity)

        assertEquals(id, result.id)
        assertEquals(login, result.login)
        assertEquals(email, result.email)
        assertEquals(createdAt, result.createdAt)
    }
}