package ru.korobeynikov.githubapiapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserRepositoryImpl::class], version = 1)
abstract class UserRepositoriesDatabase : RoomDatabase() {
    abstract fun userRepositoriesDao(): UserRepositoriesDao
}