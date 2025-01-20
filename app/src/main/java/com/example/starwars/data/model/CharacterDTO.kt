package com.example.starwars.data.model

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("birth_year")
    val birthYear: String,
    val films: List<String>,
    val height: String,
    @SerializedName("homeworld")
    val homeWorld: String,
    val name: String,
    val species: List<String>
)