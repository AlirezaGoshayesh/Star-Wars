package com.example.starwars.presentation.features.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.domain.FetchDetailsUseCase
import com.example.starwars.domain.base.Resource
import com.example.starwars.domain.model.CharacterDetailsModel
import com.example.starwars.domain.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject
constructor(
    private val fetchDetailsUseCase: FetchDetailsUseCase
) : ViewModel() {

    private val _details = mutableStateOf<Resource<CharacterDetailsModel>>(Resource.Loading)
    val details: State<Resource<CharacterDetailsModel>> get() = _details

    fun fetch(character: CharacterModel) {
        viewModelScope.launch {
            _details.value =
                fetchDetailsUseCase(
                    Triple(
                        character.specieID,
                        character.homeWorldId,
                        character.filmsIds
                    )
                )
        }
    }
}
