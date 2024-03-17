@file:JvmName("InitKoinAndroid")

package di

import android.content.Context
import org.koin.core.KoinApplication
import org.koin.dsl.module

fun initKoinAndroid(
    context: Context,
    isDebug: Boolean,
): KoinApplication =
    initKoin(
        isDebug = isDebug,
    )
        .modules(
            module {
                single { context }
                // TODO:: Add here Android-specific Dependencies module declaration...
            }
        )