package data.local

import kotlinx.coroutines.test.runTest
import org.koin.test.KoinTest
import kotlin.test.Test
import kotlin.test.assertTrue


class DatabaseMigrationTest: KoinTest {
    // TODO:: Do your DB migration tests here...
    // i.e. migrate from version 1 to 2
    // i.e. migrate from version 2 to 3
    // i.e. migrate from version 3 to 4
    // etc.
    
    @Test
    fun `Migrate from version 1 to 2`() = runTest {
        assertTrue { true }
    }
}