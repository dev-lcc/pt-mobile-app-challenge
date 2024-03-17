package di

import PlaceStore
import app.cash.sqldelight.db.SqlDriver
import data.local.PlaceDao
import data.local.createInMemorySqlDriver
import data.network.PlacesApiService
import data.store.ListPlacesStore
import io.github.devlcc.ptmobileappchallenge.PTDatabase
import io.ktor.client.*
import io.ktor.client.engine.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class KoinModuleTest : KoinTest {

    private lateinit var koinApplication: KoinApplication
    private val testMockModule = module {
        single<PTDatabase> {
            val driver: SqlDriver = createInMemorySqlDriver()
            PTDatabase.Schema.create(driver)
            PTDatabase(driver)
        }

    }

    @BeforeTest
    fun setup() {
        koinApplication = initKoin(isDebug = true)
        loadKoinModules(testMockModule)
    }

    @AfterTest
    fun tearDown() {
        unloadKoinModules(testMockModule)
        stopKoin()
    }

    @Test
    fun `Check Modules`() = runTest {
        val koin = koinApplication.koin

        assertNotNull(koin.get<Json>())
        
        assertNotNull(koin.get<PTDatabase>())
        assertNotNull(koin.get<PlaceDao>())

        assertNotNull(koin.get<HttpClientEngine>())
        assertNotNull(koin.get<HttpClient>())
        assertNotNull(koin.get<PlacesApiService>())

        assertNotNull(koin.get<ListPlacesStore>())
        assertNotNull(koin.get<PlaceStore>())

//        koinApplication.checkModules()
    }

}