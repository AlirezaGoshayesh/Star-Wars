package com.example.starwars.data.remote;

import androidx.paging.PagingSource
import androidx.paging.PagingState;
import com.example.starwars.data.model.CharacterDTO
import com.example.starwars.data.remote.connection.MService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException

class StarWarsPagingSource @AssistedInject constructor(
    private val api: MService,
    @Assisted private val query: String
) : PagingSource<Int, CharacterDTO>() {

    @AssistedFactory
    interface Factory {
        fun create(query: String): StarWarsPagingSource
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDTO> {
        val page = params.key ?: 1
        return try {
            // Fetch data from API
            val response = api.searchChars(query, page)
            val characters = response.results

            // Return the data and the keys for next/previous pages
            LoadResult.Page(
                data = characters,
                prevKey = if (page == 1) null else page - 1, // No previous page if it's the first page
                nextKey = response.next?.let { page + 1 }    // Increment page if 'next' is not null
            )
        } catch (e:IOException) {
            // Network or other IO error
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // Server error
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state:PagingState<Int, CharacterDTO>): Int? {
        // Try to find the anchor position for refresh
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}