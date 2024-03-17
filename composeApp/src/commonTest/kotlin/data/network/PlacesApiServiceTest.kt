package data.network

import data.network.di.testDataNetworkModule
import data.network.dto.place.GetPlacesResponse
import data.network.dto.place.PlaceDTO
import io.ktor.client.engine.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.random.Random
import kotlin.test.*

class PlacesApiServiceTest : KoinTest {

    private lateinit var placesApiService: PlacesApiService

    private val testCoroutineScope = TestScope()
    private val testCoroutineDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                testDataNetworkModule(
                    coroutineScope = testCoroutineScope,
                )
            )
        }

        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `Get Place By Id - Success`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //
        val json: Json = get()

        val inputPlaceId = Random.nextLong(999_999_999L)
        val expected: PlaceDTO =
            json.decodeFromString(PlaceDTO.serializer(), PlacesApiServiceMock.GetPlaceById.success(inputPlaceId))
        val engineModule = module {
            single<HttpClientEngine> { PlacesApiServiceMock.GetPlaceById.mockEngineStatusSuccess(inputPlaceId) }
        }
        loadKoinModules(engineModule)
        placesApiService = get()

        //
        // WHEN
        //
        val actual = withContext(Dispatchers.Default) {
            placesApiService.getPlaceById(id = inputPlaceId)
        }

        //
        // THEN
        //
        println("Get Place By Id - Success() -> actual = \n${json.encodeToString(PlaceDTO.serializer(), actual)}")
        assertTrue { actual == expected }

        unloadKoinModules(engineModule)
    }

    @Test
    fun `Get Place By Id - Error`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //
        val json: Json = get()

        val inputPlaceId = Random.nextLong(999_999_999L)
        val engineModule = module {
            single<HttpClientEngine> { PlacesApiServiceMock.GetPlaceById.mockEngineStatusError() }
        }
        loadKoinModules(engineModule)
        placesApiService = get()

        //
        // WHEN
        //
        var error: Throwable? = null
        try {
            withContext(Dispatchers.Default) {
                placesApiService.getPlaceById(inputPlaceId)
            }
            fail("Get Place By Id - Error() -> MUST fail...")
        } catch (err: Throwable) {
            error = err
        }

        //
        // THEN
        //
        println("Get Place By Id - Error() -> error = \n${error?.message}")
        assertTrue { error != null }    // operation above must have thrown an error

        unloadKoinModules(engineModule)
    }

    @Test
    fun `Get All Places - Success`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //
        val json: Json = get()

        val expected = json.decodeFromString(GetPlacesResponse.serializer(), PlacesApiServiceMock.GetAllPlaces.Success)
        val engineModule = module {
            single<HttpClientEngine> { PlacesApiServiceMock.GetAllPlaces.mockEngineStatusSuccess() }
        }
        loadKoinModules(engineModule)
        placesApiService = get()

        //
        // WHEN
        //
        val actual = withContext(Dispatchers.Default) {
            placesApiService.getAllPlaces()
        }

        //
        // THEN
        //
        println("Get All Places - Success() -> actual =\n${json.encodeToString(GetPlacesResponse.serializer(), actual)}")
        assertTrue { actual == expected }

        unloadKoinModules(engineModule)
    }

    @Test
    fun `Get All Places - Error`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //
        val engineModule = module {
            single<HttpClientEngine> { PlacesApiServiceMock.GetAllPlaces.mockEngineStatusError() }
        }
        loadKoinModules(engineModule)
        placesApiService = get()

        //
        // WHEN
        //
        var error: Throwable? = null
        try {
            withContext(Dispatchers.Default) {
                placesApiService.getAllPlaces()
            }
            fail("Get All Places - Error() -> MUST fail...")
        } catch (err: Throwable) {
            error = err
        }

        //
        // THEN
        //
        println("Get All Places - Error() -> error =\n${error?.message}")
        assertTrue { error != null }    // operation above must have thrown an error

        unloadKoinModules(engineModule)
    }

    // TODO:: Implement other network-related test cases here

}

