package data.network.di

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

@VisibleForTesting
fun testDataNetworkModule(
    json: Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    },
    coroutineScope: CoroutineScope, // Test Coroutine Scope
): List<Module> =
    getDataNetworkModule(
        restApiEndpoint = "http://127.0.0.1:8080",
        isDebug = true,
    ) + module {

        single<Json> { json }

        single<CoroutineScope> { coroutineScope }
    }
