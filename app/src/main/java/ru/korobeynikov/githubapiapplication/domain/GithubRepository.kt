package ru.korobeynikov.githubapiapplication.domain

import com.google.gson.annotations.SerializedName

data class GithubRepository(
    val name: String,
    val fullName: String,
    val url: String
)