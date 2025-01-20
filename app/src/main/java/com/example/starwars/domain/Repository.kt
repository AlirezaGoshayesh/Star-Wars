package com.example.starwars.domain

import androidx.paging.PagingData
import com.example.starwars.data.model.CharacterDTO
import com.example.starwars.domain.model.CharacterDetailsModel
import com.example.starwars.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun searchCharacters(query: String): Flow<PagingData<CharacterModel>>

    suspend fun getCharDetails(
        specieId: String?,
        planetId: String?,
        filmsIds: List<String>
    ): CharacterDetailsModel
}
