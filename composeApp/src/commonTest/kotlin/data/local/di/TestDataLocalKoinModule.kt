package data.local.di

import data.local.createInMemorySqlDriver
import io.github.devlcc.ptmobileappchallenge.PTDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

// For Testing, instantiate an in-memory PTDatabase instance
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
    }