package com.example.starwars.data.model

import com.google.gson.annotations.SerializedName

data class FilmDTO(
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    val title: String
)