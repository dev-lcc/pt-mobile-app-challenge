package di

import data.local.di.getDataLocalModule
import data.network.di.getDataNetworkModule
import data.store.di.getDataStoreModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

internal fun initKoin(
    isDebug: Boolean,
): KoinApplication {

    val koinApplication = startKoin {
        modules(
            module {
                single<CoroutineDispatcher> { provideIOCoroutineDispatcher() }

                single<Json> {
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                }
            },
            getDataLocalModule(),
            getDataNetworkModule(),
            getDataStoreModule(),
        )
    }

    return koinApplication
}

fun provideIOCoroutineDispatcher(): CoroutineDispatcher =
    newFixedThreadPoolContext(8, "PTMobileAppChallenge-IOCoroutineDispatcher")