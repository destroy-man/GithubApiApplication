package ru.korobeynikov.githubapiapplication.data

import com.google.gson.annotations.SerializedName

data class GithubRepositoryImpl(
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val url: String
)