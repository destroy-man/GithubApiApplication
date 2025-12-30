package ru.korobeynikov.githubapiapplication.domain

interface GithubApiRepository {
    suspend fun getRepositories(username: String): List<GithubRepository>
}