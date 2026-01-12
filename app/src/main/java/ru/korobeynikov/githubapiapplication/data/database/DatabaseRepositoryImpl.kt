package ru.korobeynikov.githubapiapplication.data.database

import ru.korobeynikov.githubapiapplication.data.network.GithubRepositoryImpl
import ru.korobeynikov.githubapiapplication.domain.DatabaseRepository
import ru.korobeynikov.githubapiapplication.domain.GithubRepository
import ru.korobeynikov.githubapiapplication.domain.UserRepository

class DatabaseRepositoryImpl(private val userRepositoriesDao: UserRepositoriesDao) :
    DatabaseRepository {

    override suspend fun getRepositoriesByUser(user: String): List<UserRepository> {
        return userRepositoriesDao.getRepositoriesByUser(user).map { repositoryImpl ->
            repositoryImpl.toUserRepository()
        }
    }

    override suspend fun getRepositoriesByFullName(fullName: String): List<UserRepository> {
        return userRepositoriesDao.getRepositoriesByFullName(fullName).map { repositoryImpl ->
            repositoryImpl.toUserRepository()
        }
    }

    override suspend fun getAllRepositories(): List<UserRepository> {
        return userRepositoriesDao.getAllRepositories().map { repositoryImpl ->
            repositoryImpl.toUserRepository()
        }
    }

    override suspend fun addRepositories(repositories: List<UserRepository>) {
        userRepositoriesDao.insert(
            repositories.map { repository ->
                repository.toUserRepositoryImpl()
            }
        )
    }

    override suspend fun updateRepositories(repositories: List<UserRepository>) {
        userRepositoriesDao.update(
            repositories.map { repository ->
                repository.toUserRepositoryImpl()
            }
        )
    }

    override suspend fun deleteRepositories(repositories: List<UserRepository>) {
        userRepositoriesDao.delete(
            repositories.map { repository ->
                repository.toUserRepositoryImpl()
            }
        )
    }

    private fun UserRepositoryImpl.toUserRepository(): UserRepository {
        return UserRepository(
            user = user,
            repository = GithubRepository(
                repository.name,
                repository.fullName,
                repository.url
            )
        )
    }

    private fun UserRepository.toUserRepositoryImpl(): UserRepositoryImpl {
        return UserRepositoryImpl(
            user = user,
            repository = GithubRepositoryImpl(
                repository.name,
                repository.fullName,
                repository.url
            )
        )
    }
}