package com.example.starwars.presentation.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwars.data.model.CharacterDTO
import com.example.starwars.domain.SearchCharactersUseCase
import com.example.starwars.domain.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject
constructor(
    private val searchCharactersUseCase: SearchCharactersUseCase
) : ViewModel() {

    private val _query = MutableStateFlow<String>("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val charactersFlow: Flow<PagingData<CharacterModel>> = _query
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            searchCharactersUseCase(query)
        }
        .cachedIn(viewModelScope)

    fun updateQuery(query: String) {
        _query.value = query
    }
}
