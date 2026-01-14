package ru.korobeynikov.githubapiapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.korobeynikov.githubapiapplication.domain.ShowRepositoriesUseCase
import ru.korobeynikov.githubapiapplication.domain.UserRepository
import javax.inject.Inject

@HiltViewModel
class GithubRepositoriesViewModel @Inject constructor(
    private val showRepositoriesUseCase: ShowRepositoriesUseCase
) : ViewModel() {

    private val _listRepositories = MutableStateFlow<List<UserRepository>>(emptyList())
    val listRepositories: Flow<List<UserRepository>> = _listRepositories

    private val _errorState = MutableStateFlow<Error>(Error.noError(""))
    val errorState: Flow<Error> = _errorState

    private var viewModelJob: Job? = null

    private val handler = CoroutineExceptionHandler { _, throwable ->
        throwable.message?.let { error ->
            _listRepositories.value = emptyList<UserRepository>()
            when {
                error.contains("HTTP 404") -> {
                    _errorState.value = Error.Http404(
                        "Не удалось получить доступ к " +
                                "ресурсу, скорее всего некорректно введено имя пользователя"
                    )
                }

                error.contains("HTTP 403") -> {
                    _errorState.value =
                        Error.Http403("Превышен лимит запросов к ресурсу на сегодняшний день")
                }

                else -> {
                    _errorState.value = Error.Other("Не удалось отобразить репозитории")
                }
            }
        }
    }

    fun updateInfoRepositories(username: String) {
        viewModelJob?.cancel()
        viewModelJob = viewModelScope.launch(Dispatchers.IO + handler) {
            _errorState.value = Error.noError("")
            _listRepositories.value = showRepositoriesUseCase.invoke(username)
        }
    }
}