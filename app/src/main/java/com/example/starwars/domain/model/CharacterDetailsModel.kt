package com.example.starwars.domain.model

import com.example.starwars.data.model.FilmDTO
import com.example.starwars.data.model.PlanetDTO
import com.example.starwars.data.model.SpecieDTO

data class CharacterDetailsModel(
    val films: List<FilmDTO>,
    val homeWorld: PlanetDTO?,
    val specie: SpecieDTO?
)