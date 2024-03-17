import data.local.PlaceDao
import data.network.PlacesApiService
import data.network.dto.place.PlaceDTO
import data.store.converters.mapToLocal
import io.github.devlcc.ptmobileappchallenge.Place as PlaceSOT
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import model.place.Place
import org.mobilenativefoundation.store.store5.*
import kotlin.time.Duration.Companion.minutes


typealias KeyPlace = Long

class PlaceStore(
    private val placesApiService: PlacesApiService,
    private val placesLocalDao: PlaceDao,
    private val json: Json,
) {

    private val converter = provideConverter()

    operator fun invoke() =
        StoreBuilder
            .from<KeyPlace, PlaceDTO, PlaceSOT>(
                fetcher = Fetcher.ofFlow {
                    flow {
                        emit(
                            placesApiService.getPlaceById(it)
                        )
                    }
                },
                sourceOfTruth = SourceOfTruth.of(
                    reader = { key -> placesLocalDao.getPlaceByIdStream(placeId = key) },
                    writer = { key, local ->
                        placesLocalDao.upsert(
                            converter.fromNetworkToLocal(local)
                        )
                    },
                    delete = { key -> placesLocalDao.removeById(placeId = key) },
                    deleteAll = { placesLocalDao.removeAll() }
                )
            )
            .cachePolicy(
                MemoryPolicy.builder<Any, Any>()
                    .setMaxSize(10)
                    .setExpireAfterAccess(10.minutes)
                    .build()
            )
            .build()

    private fun provideConverter(): Converter<PlaceDTO, PlaceSOT, Place> {
        return Converter.Builder<PlaceDTO, PlaceSOT, Place>()
            .fromNetworkToLocal { place ->
                place.mapToLocal(json)
            }
            .fromOutputToLocal { place ->
                place.mapToLocal(json)
            }
            .build()
    }
}