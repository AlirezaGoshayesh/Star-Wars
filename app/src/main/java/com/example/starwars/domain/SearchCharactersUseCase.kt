package com.example.starwars.domain;

import androidx.paging.PagingData
import com.example.starwars.data.model.CharacterDTO
import com.example.starwars.domain.base.PagingUseCase
import com.example.starwars.domain.exceptions.IErrorHandler
import com.example.starwars.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SearchCharactersUseCase @Inject constructor(
    private val repository: Repository,
    errorHandler: IErrorHandler
) : PagingUseCase<String, CharacterModel>(errorHandler) {
    override suspend fun execute(parameters: String): Flow<PagingData<CharacterModel>> {
        return repository.searchCharacters(parameters)
    }

}