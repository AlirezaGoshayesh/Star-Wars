package com.example.starwars.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val birthYear: String,
    val filmsIds: List<String>,
    val height: String,
    val homeWorldId: String?,
    val name: String,
    val specieID: String?
): Parcelable