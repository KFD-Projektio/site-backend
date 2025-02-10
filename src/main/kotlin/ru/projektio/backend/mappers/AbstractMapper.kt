package ru.projektio.backend.mappers

interface AbstractMapper<in E, out R> {
    fun entityToResponse(entity: E): R
}