package ru.korobeynikov.githubapiapplication.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.korobeynikov.githubapiapplication.data.network.GithubRepositoryImpl

@Entity
data class UserRepositoryImpl(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val user: String,
    @Embedded
    val repository: GithubRepositoryImpl
)
