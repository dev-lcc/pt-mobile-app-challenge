package data.network

import data.network.di.TestNetworkModule
import io.ktor.client.engine.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import kotlinx.coroutines.withContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.random.Random
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class PlacesApiServiceTest : KoinTest {

    private lateinit var placesApiService: PlacesApiService

    private val testCoroutineScope = TestScope()
    private val testCoroutineDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                TestNetworkModule.build(
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
        val inputPlaceId = Random.nextLong(999_999_999L)
        val engineModule = module {
            single<HttpClientEngine> { PlacesApiServiceMock.GetPlaceById.mockEngineStatusSuccess(inputPlaceId) }
        }
        loadKoinModules(engineModule)
        placesApiService = get()

        //
        // WHEN
        //
        withContext(Dispatchers.Default) {
            placesApiService.getPlaceById(id = inputPlaceId)
        }

        //
        // THEN
        //
        assertTrue { true }

        unloadKoinModules(engineModule)
    }

    @Test
    fun `Get Place By Id - Error`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //
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
        } catch (err: Throwable) {
            error = err
        }

        //
        // THEN
        //
        assertTrue { error != null }    // operation above must have thrown an error

        unloadKoinModules(engineModule)
    }

    @Test
    fun `Get All Places - Success`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //
        val engineModule = module {
            single<HttpClientEngine> { PlacesApiServiceMock.GetAllPlaces.mockEngineStatusSuccess() }
        }
        loadKoinModules(engineModule)
        placesApiService = get()

        //
        // WHEN
        //
        withContext(Dispatchers.Default) {
            placesApiService.getAllPlaces()
        }

        //
        // THEN
        //
        assertTrue { true }

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
        } catch (err: Throwable) {
            error = err
        }

        //
        // THEN
        //
        assertTrue { error != null }    // operation above must have thrown an error

        unloadKoinModules(engineModule)
    }
    
    // TODO:: Implement other network-related test cases here

}

