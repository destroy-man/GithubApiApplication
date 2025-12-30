package ru.korobeynikov.githubapiapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.korobeynikov.githubapiapplication.domain.GithubApiRepository
import ru.korobeynikov.githubapiapplication.domain.GithubRepository
import javax.inject.Inject

@HiltViewModel
class GithubRepositoriesViewModel @Inject constructor(private val repository: GithubApiRepository): ViewModel() {

    private val _listRepositories = MutableStateFlow<List<GithubRepository>>(emptyList())
    val listRepositories: Flow<List<GithubRepository>> = _listRepositories

    fun updateInfoRepositories(username: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _listRepositories.value = repository.getRepositories(username)
            }
        }
    }
}