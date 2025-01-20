package com.example.starwars.domain.base;

import androidx.paging.PagingData
import com.example.starwars.domain.exceptions.IErrorHandler;
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

abstract class PagingUseCase<in P : Any, R : Any>(
    private val errorHandler: IErrorHandler
) {
    suspend operator fun invoke(parameters: P): Flow<PagingData<R>> {
        return try {
            execute(parameters)
        } catch (e: Exception) {
            flow {
                emitAll(handleError(e))
            }
        }
    }

    protected abstract suspend fun execute(parameters: P): Flow<PagingData<R>>

    private fun handleError(exception: Exception): Flow<PagingData<R>> {
        println("PagingUseCase Error: ${errorHandler.handleException(exception)}")
        return flowOf()
    }
}
