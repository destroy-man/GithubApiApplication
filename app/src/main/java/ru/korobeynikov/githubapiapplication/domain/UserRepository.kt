package ru.korobeynikov.githubapiapplication.domain

data class UserRepository(
    val user: String,
    val repository: GithubRepository
)
