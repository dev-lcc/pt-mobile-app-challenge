@file:JvmName("LocalKoinModuleAndroid")

package data.local.di

import data.local.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module


internal actual val platformDataLocalKoinModule: Module = module {
    single<DriverFactory> {
        DriverFactory(
            context = get()
        )
    }
}