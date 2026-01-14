package ru.korobeynikov.githubapiapplication.domain

data class UserRepository(
    val id: Int = 0,
    val user: String,
    val repository: GithubRepository
)
