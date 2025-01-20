package com.example.starwars.domain

import androidx.paging.PagingData
import com.example.starwars.domain.exceptions.IErrorHandler
import com.example.starwars.domain.model.CharacterModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchCharactersUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var errorHandler: IErrorHandler
    private lateinit var searchCharactersUseCase: SearchCharactersUseCase

    @Before
    fun setup() {
        repository = mockk()
        errorHandler = mockk(relaxed = true)
        searchCharactersUseCase = SearchCharactersUseCase(repository, errorHandler)
    }

    @Test
    fun `execute should return flow of PagingData when repository call is successful`() = runTest {
        // Arrange
        val query = "Luke"
        val expectedCharacters = listOf(
            CharacterModel(
                birthYear = "19BBY",
                filmsIds = listOf("https://swapi.dev/api/films/1/"),
                height = "172",
                homeWorldId = "https://swapi.dev/api/planets/1/",
                name = "Luke Skywalker",
                specieID = null
            ),
            CharacterModel(
                birthYear = "22BBY",
                filmsIds = listOf("https://swapi.dev/api/films/2/"),
                height = "180",
                homeWorldId = "https://swapi.dev/api/planets/2/",
                name = "Luke Starkiller",
                specieID = "https://swapi.dev/api/species/1/"
            )
        )
        val pagingData = PagingData.from(expectedCharacters)
        coEvery { repository.searchCharacters(query) } returns flowOf(pagingData)

        // Act
        val result = searchCharactersUseCase(query)

        // Assert
        val actualPagingData = result.single()
        assertEquals(pagingData, actualPagingData)
    }
}