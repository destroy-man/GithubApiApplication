package ru.korobeynikov.githubapiapplication.domain

class ShowRepositoriesUseCase(
    private val githubApiRepository: GithubApiRepository,
    private val databaseRepository: DatabaseRepository,
) {
    suspend fun invoke(username: String): List<UserRepository> {
        //Получение данных из сети
        val repositories = githubApiRepository.getRepositories(username)
        //Сохранение данных в БД
        val listUpdatedRepositories = mutableListOf<UserRepository>()
        val listAddedRepositories = mutableListOf<UserRepository>()
        repositories.forEach { repository ->
            if (databaseRepository.getRepositoriesByFullName(repository.fullName).isNotEmpty()) {
                listUpdatedRepositories.add(repository.toUserRepository(username))
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
        //
        return databaseRepository.getRepositoriesByUser(username)
    }

    fun GithubRepository.toUserRepository(username: String): UserRepository {
        return UserRepository(
            user = username,
            repository = this
        )
    }

    private companion object {
        const val MAX_COUNT_REPOSITORIES = 100
    }
}