package data.network.di

import data.network.PlacesApiService
import data.network.PlacesApiServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDataNetworkModule(
    // This can be different based on buildType OR buildFlavor
    restApiEndpoint: String = "https://private-7f9600-parceltrackermobileappchallenge.apiary-mock.com",
    isDebug: Boolean = true,
): Module = module {
    single<PlacesApiService> {
        PlacesApiServiceImpl(
            restApiEndpoint = restApiEndpoint,
            ktorClient = get(),
        )
    }

    /*single<HttpClientEngine> {
        provideHttpClientEngine()
    }*/

    single<HttpClient> {
        provideKtorClient(
            engine = get(),
            json = get(),
            isDebug = isDebug,
        )
    }

    includes(platformNetworkModule())
}

expect fun platformNetworkModule(): Module

/*private fun provideHttpClientEngine(): HttpClientEngine = CIO.create {
    maxConnectionsCount = 1000
    endpoint {
        // this: EndpointConfig
        maxConnectionsPerRoute = 100
        pipelineMaxSize = 20
        keepAliveTime = 20_000L
        connectTimeout = 20_000L
        connectAttempts = 3
    }
}*/

private fun provideKtorClient(
    engine: HttpClientEngine,
    json: Json,
    isDebug: Boolean,
): HttpClient =
    HttpClient(
        engine = engine,
    ) {
        expectSuccess = true
        developmentMode = isDebug

        defaultRequest {
            this.accept(ContentType.Application.Json)
            this.contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(json)
        }

        install(HttpTimeout) {
            val timeout = 20_000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }
