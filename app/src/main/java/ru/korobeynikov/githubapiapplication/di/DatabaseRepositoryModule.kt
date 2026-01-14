package ru.korobeynikov.githubapiapplication.di

import android.app.Application
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.korobeynikov.githubapiapplication.data.database.DatabaseRepositoryImpl
import ru.korobeynikov.githubapiapplication.data.database.UserRepositoriesDatabase
import ru.korobeynikov.githubapiapplication.domain.DatabaseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseRepositoryModule {

    @Binds
    fun bindDatabaseRepository(repository: DatabaseRepositoryImpl): DatabaseRepository

    companion object {

        @Provides
        @Singleton
        fun providesUserRepositoriesDatabase(application: Application): UserRepositoriesDatabase {
            return Room.databaseBuilder(
                application.applicationContext,
                UserRepositoriesDatabase::class.java,
                "UserRepositories"
            ).build()
        }

        @Provides
        fun providesDatabaseRepositoryImpl(database: UserRepositoriesDatabase): DatabaseRepositoryImpl {
            return DatabaseRepositoryImpl(database)
        }
    }
}