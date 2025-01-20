package com.example.starwars.data.remote

import com.example.starwars.data.model.FilmDTO
import com.example.starwars.data.model.PlanetDTO
import com.example.starwars.data.model.SpecieDTO
import com.example.starwars.data.remote.connection.MService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val service: MService) : RemoteDataSource {

    override suspend fun getSpecie(id: String): SpecieDTO {
        return service.getSpecie(id)
    }

    override suspend fun getPlant(id: String): PlanetDTO {
        return service.getPlanet(id)
    }

    override suspend fun getFilm(id: String): FilmDTO {
        return service.getFilm(id)
    }

}