package data.local.di

import androidx.annotation.VisibleForTesting
import data.local.createInMemorySqlDriver
import io.github.devlcc.ptmobileappchallenge.PTDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

// For Testing, instantiate an in-memory PTDatabase instance
@VisibleForTesting
fun testDataLocalModule(): List<Module> =
    getDataLocalModule() + module {
        
        // Replace PTDatabase Instance using in-memory variant
        single<PTDatabase> {
            val driver = createInMemorySqlDriver()
            PTDatabase.Schema.create(driver)
            PTDatabase(
                driver = driver,
            )
        }

        // Override IO Dispatcher
        single<CoroutineDispatcher> { Dispatchers.Default }
    }