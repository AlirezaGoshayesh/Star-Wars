package com.example.starwars.data.remote.connection

import com.example.starwars.data.model.FilmDTO
import com.example.starwars.data.model.PlanetDTO
import com.example.starwars.data.model.SearchResponseDTO
import com.example.starwars.data.model.SpecieDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MService {

    /**
     * search characters
     */
    @GET("people/")
    suspend fun searchChars(
        @Query("search") query: String,
        @Query("page") page: Int
    ): SearchResponseDTO

    /**
     * get species
     */
    @GET("species/{id}")
    suspend fun getSpecie(
        @Path("id") id: String
    ): SpecieDTO

    /**
     * get planets
     */
    @GET("planets/{id}")
    suspend fun getPlanet(
        @Path("id") id: String
    ): PlanetDTO

    /**
     * get films
     */
    @GET("films/{id}")
    suspend fun getFilm(
        @Path("id") id: String
    ): FilmDTO

}