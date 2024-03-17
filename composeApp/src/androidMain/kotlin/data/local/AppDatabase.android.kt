@file:JvmName("AppDatabaseAndroid")

package data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import data.local.di.DB_NAME
import io.github.devlcc.ptmobileappchallenge.PTDatabase
import org.jetbrains.annotations.VisibleForTesting

actual class DriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(PTDatabase.Schema, context, DB_NAME)
    }

}

@VisibleForTesting
actual fun createInMemorySqlDriver(): SqlDriver {
    return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
}