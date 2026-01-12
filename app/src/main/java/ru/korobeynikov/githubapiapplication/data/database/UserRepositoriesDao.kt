package ru.korobeynikov.githubapiapplication.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserRepositoriesDao {

    @Query("SELECT * FROM userrepositoryimpl WHERE user == :user")
    suspend fun getRepositoriesByUser(user: String): List<UserRepositoryImpl>

    @Query("SELECT * FROM userrepositoryimpl WHERE fullName == :fullName")
    suspend fun getRepositoriesByFullName(fullName: String): List<UserRepositoryImpl>

    @Query("SELECT * FROM userrepositoryimpl ORDER BY id")
    suspend fun getAllRepositories(): List<UserRepositoryImpl>

    @Insert
    suspend fun insert(repositories: List<UserRepositoryImpl>)

    @Update
    suspend fun update(repositories: List<UserRepositoryImpl>)

    @Delete
    suspend fun delete(repositories: List<UserRepositoryImpl>)
}