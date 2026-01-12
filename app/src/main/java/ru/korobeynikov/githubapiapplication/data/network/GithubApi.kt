package ru.korobeynikov.githubapiapplication.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{username}/repos")
    suspend fun getRepositories(@Path("username") username: String): List<GithubRepositoryImpl>
}