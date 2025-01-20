package com.example.starwars.domain;

import com.example.starwars.domain.base.UseCase
import com.example.starwars.domain.exceptions.IErrorHandler
import com.example.starwars.domain.model.CharacterDetailsModel
import com.example.starwars.domain.model.CharacterModel
import javax.inject.Inject

class FetchDetailsUseCase @Inject constructor(
    private val repository: Repository,
    errorHandler: IErrorHandler
) : UseCase<Triple<String?, String?, List<String>>, CharacterDetailsModel>(errorHandler) {
    override suspend fun execute(parameters: Triple<String?, String?, List<String>>): CharacterDetailsModel {
        return repository.getCharDetails(parameters.first, parameters.second, parameters.third)
    }
}