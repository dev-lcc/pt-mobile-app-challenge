package data.store

import data.local.PlaceDao
import data.network.PlacesApiService
import data.network.dto.place.PlaceDTO
import data.store.converters.mapToLocal
import data.store.converters.mapToOutput
import io.github.devlcc.ptmobileappchallenge.Place as PlaceSOT
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import model.place.Place
import org.mobilenativefoundation.store.store5.*
import kotlin.time.Duration.Companion.minutes

typealias KeyListPlaces = Pair<Int, Int>    // offset | count

class ListPlacesStore(
    private val placesApiService: PlacesApiService,
    private val placesLocalDao: PlaceDao,
    private val json: Json,
) {

    private val converter = provideConverter()

    operator fun invoke(): Store<KeyListPlaces, List<Place>> =
        StoreBuilder
            .from<KeyListPlaces, List<PlaceDTO>, List<Place>>(
                fetcher = Fetcher.ofFlow {
                    flow {
                        emit(
                            placesApiService.getAllPlaces(
                                offset = it.first,
                                limit = it.second,
                            ).data
                        )
                    }
                },
                sourceOfTruth = SourceOfTruth.of(
                    reader = { key ->
                        placesLocalDao.getAllPlacesStream(offset = key.first, count = key.second)
                            .map { places: List<PlaceSOT> ->
                                places.map { place: PlaceSOT -> place.mapToOutput(json) }
                            }
                     },
                    writer = { key, local ->
                        placesLocalDao.upsert(
                            10,
                            *(converter.fromNetworkToLocal(local)).toTypedArray()
                        )
                    },
                    delete = { key -> placesLocalDao.removeRange(offset = key.first, count = key.second) },
                    deleteAll = { placesLocalDao.removeAll() }
                ),
            )
            .cachePolicy(
                MemoryPolicy.builder<Any, Any>()
                    .setMaxSize(10)
                    .setExpireAfterAccess(10.minutes)
                    .build()
            )
            .build()

    private fun provideConverter(): Converter<List<PlaceDTO>, List<PlaceSOT>, List<Place>> {
        return Converter.Builder<List<PlaceDTO>, List<PlaceSOT>, List<Place>>()
            .fromNetworkToLocal { places ->
                places.map { place ->
                    place.mapToLocal(json)
                }
            }
            .fromOutputToLocal { places ->
                places.map { place ->
                    place.mapToLocal(json)
                }
            }
            .build()
    }
}