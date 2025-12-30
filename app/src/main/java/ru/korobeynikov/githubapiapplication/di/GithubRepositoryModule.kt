package ru.korobeynikov.githubapiapplication.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.korobeynikov.githubapiapplication.data.GithubApiRepositoryImpl
import ru.korobeynikov.githubapiapplication.domain.GithubApiRepository


@Module
@InstallIn(SingletonComponent::class)
class GithubRepositoryModule {

    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    @Provides
    fun providesGithubApiRepository(retrofit: Retrofit): GithubApiRepository {
        return GithubApiRepositoryImpl(retrofit)
    }
}