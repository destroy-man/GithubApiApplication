package ru.korobeynikov.githubapiapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var username by remember { mutableStateOf("destroy-man") }
            GithubRepositoriesScreen(username = username){
                username=it
            }
//            //https://api.github.com/users/destroy-man/repos
//            val scope= CoroutineScope(Job()+Dispatchers.IO)
//            val retrofit= Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(
//                GsonConverterFactory.create()).build()
//            val api=retrofit.create(GithubAPI::class.java)
//            scope.launch {
//                val list=api.getRepositories("destroy-man")
//                list.forEach { repository ->
//                    Log.d("myLogs",repository.name)
//                }
//            }
        }
    }
}