package com.example.starwars.presentation.features.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.starwars.domain.base.Resource
import com.example.starwars.domain.model.CharacterDetailsModel
import com.example.starwars.domain.model.CharacterModel
import com.example.starwars.presentation.features.main.ErrorItem
import com.example.starwars.presentation.features.main.LoadingItem

@Composable
fun DetailScreen(
    characterModel: CharacterModel,
    viewModel: DetailVM = hiltViewModel()
) {
    val detailValue by viewModel.details
    LaunchedEffect(Unit) {
        viewModel.fetch(characterModel)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = characterModel.name,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "DOB: ${characterModel.birthYear}, Height: ${characterModel.height}",
            style = MaterialTheme.typography.bodyMedium
        )
        HorizontalDivider()
        when (detailValue) {
            is Resource.Error -> ErrorItem((detailValue as Resource.Error).errorModel.getErrorMessage())
            is Resource.Loading -> LoadingItem()
            is Resource.Success -> {
                val detail = (detailValue as Resource.Success<CharacterDetailsModel>).data
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Species: ${detail.specie?.name ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Language: ${detail.specie?.language ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Home World: ${detail.homeWorld?.name ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Population: ${detail.homeWorld?.population ?: "Unknown"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    HorizontalDivider()
                    Text(text = "Films", style = MaterialTheme.typography.headlineSmall)
                    LazyColumn(
                        contentPadding = PaddingValues(4.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(detail.films.size) {
                            FilmItem(
                                title = detail.films[it].title,
                                brief = detail.films[it].openingCrawl
                            )
                        }
                    }
                }
            }
        }
    }
}

