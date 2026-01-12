package ru.korobeynikov.githubapiapplication.domain

interface DatabaseRepository {

    suspend fun getRepositoriesByUser(user: String): List<UserRepository>

    suspend fun getRepositoriesByFullName(fullName: String): List<UserRepository>

    suspend fun getAllRepositories(): List<UserRepository>

    suspend fun addRepositories(repositories: List<UserRepository>)

    suspend fun updateRepositories(repositories: List<UserRepository>)

    suspend fun deleteRepositories(repositories: List<UserRepository>)
}