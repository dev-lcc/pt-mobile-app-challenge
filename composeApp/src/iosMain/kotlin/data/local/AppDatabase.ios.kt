package data.local

import androidx.annotation.VisibleForTesting
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration
import data.local.di.DB_NAME
import io.github.devlcc.ptmobileappchallenge.PTDatabase

actual class DriverFactory() {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = PTDatabase.Schema,
            name = DB_NAME,
        )
    }
}

// For Unit Testing ONLY
@VisibleForTesting
actual fun createInMemorySqlDriver(): SqlDriver {
    return NativeSqliteDriver(
        configuration = DatabaseConfiguration(
            name = DB_NAME,
            version = PTDatabase.Schema.version.toInt(),
            create = { connection ->
                wrapConnection(connection) { PTDatabase.Schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { PTDatabase.Schema.migrate(it, oldVersion.toLong(), newVersion.toLong()) }
            }
        ),
    )
}