package data.network.di

import androidx.annotation.VisibleForTesting
import data.network.PlacesApiService
import data.network.PlacesApiServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

@VisibleForTesting
object TestNetworkModule {
    
    fun build(
        json: Json = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        },
        coroutineScope: CoroutineScope, // Test Coroutine Scope
    ): List<Module> =
        NetworkModule(
            isDebug = true,
        ).build() + module {

            single<PlacesApiService> {
                PlacesApiServiceImpl(
                    restApiEndpoint = "http://127.0.0.1:8080",
                    ktorClient = get(),
                )
            }

            single<CoroutineScope> { coroutineScope }
        }
    
}