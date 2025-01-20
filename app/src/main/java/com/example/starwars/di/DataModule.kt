package com.example.starwars.di

import RepositoryImpl
import com.google.gson.Gson
import com.example.starwars.data.ErrorHandler
import com.example.starwars.data.remote.RemoteDataSource
import com.example.starwars.data.remote.RemoteDataSourceImpl
import com.example.starwars.data.remote.StarWarsPagingSource
import com.example.starwars.data.remote.connection.MService
import com.example.starwars.domain.Repository
import com.example.starwars.domain.exceptions.IErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providePagingSourceFactory(
        factory: StarWarsPagingSource.Factory
    ): (String) -> StarWarsPagingSource {
        return { query: String -> factory.create(query) }
    }

    @Provides
    @Singleton
    fun provideRepository(
        pagingSourceFactory: StarWarsPagingSource.Factory,
        remoteDataSource: RemoteDataSource
    ): Repository {
        return RepositoryImpl(pagingSourceFactory, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        service: MService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(service)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideErrorHandler(): IErrorHandler {
        return ErrorHandler()
    }
}