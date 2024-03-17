package di

import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.KoinApplication
import org.koin.dsl.module


@OptIn(ExperimentalForeignApi::class)
fun initKoinIos(
    launchOptions: Map<Any?, Any?>,
    // TODO:: Add platform-specific params
    isDebug: Boolean,
): KoinApplication =
    initKoin(isDebug)
        .modules(
            module {
                // TODO:: Add here iOS-specific Dependencies module declaration...
            }
        )