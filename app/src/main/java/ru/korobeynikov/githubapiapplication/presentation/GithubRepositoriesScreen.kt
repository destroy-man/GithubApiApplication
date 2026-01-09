package ru.korobeynikov.githubapiapplication.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GithubRepositoriesScreen(
    viewModel: GithubRepositoriesViewModel = viewModel(),
    username: String,
    onUsernameChange: (String) -> Unit,
) {
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        val listRepositories by viewModel.listRepositories.collectAsState(emptyList())
        val error by viewModel.errorState.collectAsState(Error.noError(""))
        Text("Укажите имя пользователя, репозитории которого нужно отобразить")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Имя пользователя: ")
            OutlinedTextField(
                value = username,
                onValueChange = onUsernameChange,
                modifier = Modifier.weight(1f)
            )
        }
        Button(onClick = {
            viewModel.updateInfoRepositories(username)
        }) {
            Text("Show repositories $username")
        }
        if (error is Error.noError) {
            LazyColumn {
                items(listRepositories.size) { index ->
                    val repository = listRepositories[index]
                    val sb = StringBuilder("Repository name: ${repository.name}\n")
                    sb.appendLine("Repository full name: ${repository.fullName}")
                    sb.appendLine("Repository url: ${repository.url}")
                    Text(sb.toString())
                }
            }
        } else {
            Text(error.message)
        }
    }
}