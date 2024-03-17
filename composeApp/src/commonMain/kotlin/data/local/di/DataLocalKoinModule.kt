package data.local.di

import app.cash.sqldelight.db.SqlDriver
import data.local.PlaceDao
import data.local.PlaceDaoImpl
import data.local.createDatabase
import io.github.devlcc.ptmobileappchallenge.PTDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

const val DB_NAME = "ptmobileappchallenge.db"
internal expect val platformDataLocalKoinModule: Module

fun getDataLocalModule() = module {
    
    single<PTDatabase> {
        createDatabase(
            driverFactory = get(),
        )
    }
    
    single<PlaceDao> {
        PlaceDaoImpl(
            database = get(),
            ioCoroutineDispatcher = get(),
        )
    }
    
}
    .apply { includes(platformDataLocalKoinModule) }

