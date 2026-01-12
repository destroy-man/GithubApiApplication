package ru.korobeynikov.githubapiapplication.data.network

import com.google.gson.annotations.SerializedName

data class GithubRepositoryImpl(
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val url: String
)