package ru.korobeynikov.githubapiapplication.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GithubRepositoriesScreen(
    viewModel: GithubRepositoriesViewModel = viewModel(),
    username: String,
    onUsernameChange:(String)-> Unit
){
    Column {
        val listRepositories by viewModel.listRepositories.collectAsState(emptyList())
        OutlinedTextField(value = username, onValueChange = onUsernameChange)
        Button(onClick = {
            viewModel.updateInfoRepositories(username)
        }) {
            Text("Show repositories $username")
        }
        listRepositories.forEach { repository ->
            val sb= StringBuilder("Repository name: ${repository.name}\n")
            sb.appendLine("Repository full name: ${repository.fullName}")
            sb.appendLine("Repository url: ${repository.url}")
            Text(sb.toString())
        }
    }
}