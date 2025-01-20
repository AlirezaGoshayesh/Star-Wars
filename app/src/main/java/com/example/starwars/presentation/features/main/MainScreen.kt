package com.example.starwars.presentation.features.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwars.presentation.navigation.Screen
import com.google.gson.Gson

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainVM = hiltViewModel()
) {
    val characters = viewModel.charactersFlow.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.updateQuery(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search Characters") },
            singleLine = true
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(characters.itemCount) { index ->
                characters[index]?.let { char ->
                    CharacterItem(character = char, onClick = {
                        val characterJson = Gson().toJson(char)
                        navController.navigate("${Screen.DetailScreen.route}/${characterJson}")
                    })
                }
            }

            characters.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        if (searchQuery.isNotEmpty())
                            item { LoadingItem() }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = loadState.refresh as LoadState.Error
                        item { ErrorItem(message = e.error.localizedMessage ?: "Unknown Error") }
                    }
                }
            }
        }
    }
}

