package data.local

import androidx.annotation.VisibleForTesting
import app.cash.sqldelight.db.SqlDriver
import io.github.devlcc.ptmobileappchallenge.PTDatabase

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): PTDatabase {
    val driver = driverFactory.createDriver()
    val database = PTDatabase(driver)

    // Do more work with the database (see below).
    
    return database
}


@VisibleForTesting
expect fun createInMemorySqlDriver(): SqlDriver