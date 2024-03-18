package presentation.main.home

import app.cash.turbine.test
import data.local.PlaceDao
import data.network.PlacesApiService
import data.network.dto.place.GetPlacesResponse
import data.network.dto.place.PlaceDTO
import data.store.converters.mapToLocal
import data.store.converters.mapToNetwork
import data.store.converters.mapToOutput
import data.store.di.getDataStoreModule
import di.getViewModelsModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import kotlinx.serialization.json.Json
import model.place.Place
import model.place.PlaceType
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import presentation.main.home.test.HomeScreenTestData
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail
import io.github.devlcc.ptmobileappchallenge.Place as PlaceSOT

class HomeViewModelTest: KoinTest {

    private lateinit var viewModel: HomeViewModel

    private val testCoroutineScope = TestScope()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testCoroutineDispatcher = /*UnconfinedTestDispatcher()*/StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setup() {
        FakeData.placesData = HomeScreenTestData.places.toMutableList()
        startKoin {
            modules(
                module {
                    single<Json> { Json }
                    single<PlaceDao> { FakeData.PlaceDaoImpl }
                    single<PlacesApiService> { FakeData.PlaceApiServiceImpl }
                },
                getDataStoreModule(),
                getViewModelsModule(),
            )
        }

        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = get()
    }

    @AfterTest
    fun tearDown() {
        FakeData.placesData.clear()
        stopKoin()

        Dispatchers.resetMain()
    }

    @Test
    fun `Select Place Type`() = runTest {

        //
        // GIVEN
        //
        val inputPlaceType = PlaceType.entries.random()
        val expected = FakeData.placesData.filter { it.type == inputPlaceType }

        //
        // WHEN
        //
        viewModel.handle(HomeEvent.SelectPlaceType(inputPlaceType))

        //
        // THEN - Expect place listings appear based on selected Place Type(i.e. `Location`, `Hotel`, etc.)
        //
        viewModel.uiState.test {
            var item: HomeUiState
            do {
                item = awaitItem()
            } while(item.isLoading)

            if(expected.isNotEmpty()) {
                val actual = mutableListOf<Place>()
                when(val content = item.listings) {
                    is HomeUiState.Listings.Empty -> { fail("") }
                    is HomeUiState.Listings.Content -> {
                        val popular = content.popular
                        when(popular) {
                            is HomeUiState.Listings.Content.Popular.Empty -> {}
                            is HomeUiState.Listings.Content.Popular.Content -> {
                                actual.addAll(popular.places)
                            }
                        }
                        val recommended = content.recommended
                        when(recommended) {
                            is HomeUiState.Listings.Content.Recommended.Empty -> {}
                            is HomeUiState.Listings.Content.Recommended.Content -> {
                                actual.addAll(recommended.places)
                            }
                        }
                    }
                }

                assertTrue { expected.containsAll(actual) }
            } else {
                assertTrue { item.listings is HomeUiState.Listings.Empty }
            }
        }

    }

    // TODO:: Add more HomeViewModel Unit Test Cases here...

}

object FakeData {
    var placesData = HomeScreenTestData.places.toMutableList()
    val json: Json = Json

    object PlaceDaoImpl: PlaceDao {
        override suspend fun getAllPlaces(offset: Int, count: Int): List<PlaceSOT> = placesData.map { it.mapToLocal(json) }
        override fun getAllPlacesStream(offset: Int, count: Int): Flow<List<PlaceSOT>> = flowOf(placesData.map { it.mapToLocal(json) })
        override suspend fun getPlaceById(placeId: Long): PlaceSOT {
            return placesData.find { it.id == placeId }?.mapToLocal(json) ?: throw IllegalArgumentException("")
        }
        override fun getPlaceByIdStream(placeId: Long): Flow<PlaceSOT> =
            flow {
                emit(placesData.find { it.id == placeId }?.mapToLocal(json) ?: throw IllegalArgumentException(""))
            }

        override suspend fun upsert(place: PlaceSOT) {
            val index = placesData.indexOfFirst { it.id == place.placeId }
            if(index >= 0) {
                placesData[index] = place.mapToOutput(json)
            } else {
                placesData.add(place.mapToOutput(json))
            }
        }

        override suspend fun upsert(batchCount: Int, vararg places: PlaceSOT) {
            places.forEach { place ->
                val index = placesData.indexOfFirst { it.id == place.placeId }
                if(index >= 0) {
                    placesData[index] = place.mapToOutput(json)
                } else {
                    placesData.add(place.mapToOutput(json))
                }
            }
        }

        override fun removeById(placeId: Long) {
            val index = placesData.indexOfFirst { it.id == placeId }
            if(index >= 0) {
                placesData.removeAt(index)
            }
        }

        override fun removeRange(offset: Int, count: Int) {
            placesData.removeAll(
                placesData.subList(
                    offset,
                    run {
                        val endIndex = offset + count
                        if(endIndex < placesData.size) endIndex else placesData.size
                    }
                )
            )
        }

        override fun removeAll() {
            placesData.clear()
        }
    }

    object PlaceApiServiceImpl: PlacesApiService {
        override suspend fun getAllPlaces(offset: Int, limit: Int): GetPlacesResponse =
            GetPlacesResponse(
                offset = offset.toLong(),
                limit = limit.toLong(),
                qty = placesData.size.toLong(),
                totalQty = placesData.size.toLong(),
                data = placesData.map { it.mapToNetwork(json) },
            )

        override suspend fun getPlaceById(id: Long): PlaceDTO {
            return placesData.find { it.id == id }?.mapToNetwork(json) ?: throw IllegalArgumentException("")
        }
    }
}