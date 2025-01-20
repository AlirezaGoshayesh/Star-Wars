import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.starwars.data.remote.RemoteDataSource
import com.example.starwars.data.remote.StarWarsPagingSource
import com.example.starwars.domain.Mapper
import com.example.starwars.domain.Repository
import com.example.starwars.domain.model.CharacterDetailsModel
import com.example.starwars.domain.model.CharacterModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val pagingSourceFactory: StarWarsPagingSource.Factory,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun searchCharacters(query: String): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { pagingSourceFactory.create(query) }
        ).flow.map {
            it.map {
                CharacterModel(
                    birthYear = it.birthYear,
                    filmsIds = it.films.map { Mapper.mapUrlToId(it) },
                    height = Mapper.mapHeight(it.height),
                    homeWorldId = Mapper.mapUrlToId(it.homeWorld),
                    name = it.name,
                    specieID = if (it.species.isNotEmpty()) Mapper.mapUrlToId(it.species.first()) else null,
                )
            }
        }
    }

    override suspend fun getCharDetails(
        specieId: String?,
        planetId: String?,
        filmsIds: List<String>
    ): CharacterDetailsModel =
        coroutineScope {
            val specieDeferred = if (specieId != null) {
                async { remoteDataSource.getSpecie(specieId) }
            } else {
                null
            }
            val deferredFilms = filmsIds.map { filmId ->
                async { remoteDataSource.getFilm(filmId) }
            }

            val planetDeferred = if (planetId != null) {
                async { remoteDataSource.getPlant(planetId) }
            } else {
                null
            }
            val specie = specieDeferred?.await()
            val planet = planetDeferred?.await()
            val films = deferredFilms.awaitAll()

            return@coroutineScope CharacterDetailsModel(
                specie = specie,
                films = films,
                homeWorld = planet
            )
        }
}
