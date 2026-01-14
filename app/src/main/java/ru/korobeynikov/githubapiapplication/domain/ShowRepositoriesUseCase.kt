package ru.korobeynikov.githubapiapplication.domain

import javax.inject.Inject

class ShowRepositoriesUseCase @Inject constructor(
    private val githubApiRepository: GithubApiRepository,
    private val databaseRepository: DatabaseRepository
) {

    suspend fun invoke(username: String): List<UserRepository> {
        runCatching {
            //Получение данных из сети
            githubApiRepository.getRepositories(username)
        }.onSuccess { repositories ->
            //Сохранение данных в БД
            val listUpdatedRepositories = mutableListOf<UserRepository>()
            val listAddedRepositories = mutableListOf<UserRepository>()
            repositories.forEach { repository ->
                if (databaseRepository.getRepositoriesByFullName(repository.fullName).isNotEmpty()) {
                    val id = databaseRepository.getRepositoriesByFullName(repository.fullName).first().id
                    listUpdatedRepositories.add(repository.toUserRepository(username, id))
                } else {
                    listAddedRepositories.add(repository.toUserRepository(username))
                }
            }
            databaseRepository.updateRepositories(listUpdatedRepositories)
            databaseRepository.addRepositories(listAddedRepositories)
            //Если данных в БД слишком много удаляем до нужного количества
            if (databaseRepository.getAllRepositories().size > MAX_COUNT_REPOSITORIES) {
                val repositoriesDb = databaseRepository.getAllRepositories()
                val countDeleted = repositoriesDb.size - MAX_COUNT_REPOSITORIES
                val listDeletedRepositories = repositoriesDb.take(countDeleted)
                databaseRepository.deleteRepositories(listDeletedRepositories)
            }
        }.onFailure { throwable ->
            //Если не удалось получить данные из сети, смотрим есть ли в бд данные по пользователю,
            //если нет то бросаем исключение
            if (databaseRepository.getRepositoriesByUser(username).isEmpty()) {
                throw throwable
            }
        }
        return databaseRepository.getRepositoriesByUser(username)
    }

    private fun GithubRepository.toUserRepository(username: String, id: Int = 0): UserRepository {
        return UserRepository(
            id = id,
            user = username,
            repository = this
        )
    }

    private companion object {
        const val MAX_COUNT_REPOSITORIES = 100
    }
}