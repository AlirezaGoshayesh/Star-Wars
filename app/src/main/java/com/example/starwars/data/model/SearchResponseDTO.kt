package com.example.starwars.data.model

data class SearchResponseDTO(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<CharacterDTO>
)