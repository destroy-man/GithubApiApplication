package ru.korobeynikov.githubapiapplication.data.network

import retrofit2.Retrofit
import ru.korobeynikov.githubapiapplication.domain.GithubApiRepository
import ru.korobeynikov.githubapiapplication.domain.GithubRepository
import javax.inject.Inject

class GithubApiRepositoryImpl @Inject constructor(private val retrofit: Retrofit) :
    GithubApiRepository {
    override suspend fun getRepositories(username: String): List<GithubRepository> {
        val githubApi = retrofit.create(GithubApi::class.java)
        return githubApi.getRepositories(username).map { repositoryImpl ->
            GithubRepository(
                name = repositoryImpl.name,
                fullName = repositoryImpl.fullName,
                url = repositoryImpl.url
            )
        }
    }
}