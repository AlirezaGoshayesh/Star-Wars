package com.example.starwars.data.remote

import com.example.starwars.data.model.FilmDTO
import com.example.starwars.data.model.PlanetDTO
import com.example.starwars.data.model.SpecieDTO

interface RemoteDataSource {

    suspend fun getSpecie(id: String): SpecieDTO

    suspend fun getPlant(id: String): PlanetDTO

    suspend fun getFilm(id: String): FilmDTO
}