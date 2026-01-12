package ru.korobeynikov.githubapiapplication.data.database

import androidx.room.Database

@Database(entities = [UserRepositoryImpl::class], version = 1)
abstract class UserRepositoriesDatabase {
    abstract fun userRepositoriesDao(): UserRepositoriesDao
}