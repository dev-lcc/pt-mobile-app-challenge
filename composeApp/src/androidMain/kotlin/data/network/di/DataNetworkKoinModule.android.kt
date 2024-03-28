package data.network.di

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun platformNetworkModule(): Module = module {
    single<HttpClientEngine> {
        OkHttp.create {}
    }
}