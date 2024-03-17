package data.local

import data.local.di.testDataLocalModule
import io.github.devlcc.ptmobileappchallenge.PTDatabase
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import model.place.Amenity
import model.place.Place
import model.place.PlaceType
import io.github.devlcc.ptmobileappchallenge.Place as PlaceSOT
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.random.Random
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class PlaceQueriesTest : KoinTest {

    private lateinit var database: PTDatabase
    private lateinit var placeDao: PlaceDao

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                testDataLocalModule()
            )
        }

        database = get()
        placeDao = get()
    }

    @AfterTest
    fun tearDown() {
        placeDao.removeAll()
        stopKoin()
    }

    @Test
    fun `Get All Places`() = runTest {
        //
        // GIVEN
        //
        val sampleDataCount = 3
        val expected = PlaceMockData.places(sampleDataCount)

        placeDao.upsert(
            batchCount = sampleDataCount,
            *(expected).toTypedArray(),
        )

        //
        // WHEN
        //
        val actual = placeDao.getAllPlaces(
            offset = 0,
            limit = sampleDataCount,
        )

        //
        // THEN
        //
        assertTrue { actual.containsAll(expected) }
    }

    @Test
    fun `Get Place By Id`() = runTest {
        //
        // GIVEN
        //
        val sampleDataCount = 5
        val sampleData = PlaceMockData.places(sampleDataCount)
        val expected = sampleData.random()
        val inputPlaceId = expected.placeId

        placeDao.upsert(
            batchCount = sampleDataCount,
            *(sampleData).toTypedArray(),
        )

        //
        // WHEN
        //
        val actual = placeDao.getPlaceById(
            placeId = inputPlaceId,
        )

        //
        // THEN
        //
        assertTrue { actual == expected }
    }

    // TODO:: Cover all other test cases...

}

object PlaceMockData {

    fun places(count: Int): List<PlaceSOT> =
        mutableListOf<PlaceSOT>().apply {
            val placeId = Random.nextLong(999_999_999L)
            val number = Random.nextInt(999_999_999)
            val lat = Random.nextDouble(99.99999999)
            val lng = Random.nextDouble(99.99999999)
            val reviewsCount = Random.nextLong(999_999_999L)
            val rating = Random.nextDouble(5.0)
            val price = Random.nextDouble(99_999.0)
            add(
                PlaceSOT(
                    placeId = placeId,
                    name = "Some place somewhere $number",
                    description = "Some description $number",
                    hostThumbnail = null,
                    lat = lat,
                    lng = lng,
                    reviewsCount = reviewsCount,
                    rating = rating,
                    address = "Some address at $number",
                    amenities = Json.encodeToString(
                        serializer = ListSerializer(String.serializer()),
                        value = amenities.shuffled().subList(0, Random.nextInt(amenities.size - 1))
                    ),
                    price = Json.encodeToString(
                        String.serializer(),
                        """
                        {
                        "rate": $price,
                        "currency": "USD",
                        "total": $price,
                        "priceItems": []
                        }
                        """.trimIndent()
                    ),
                    type = placeTypes.random(),
                    tags = Json.encodeToString(
                        serializer = ListSerializer(String.serializer()),
                        value = tags.shuffled().subList(0, Random.nextInt(tags.size - 1))
                            .mapNotNull { tag -> tag.takeIf { it.isNotEmpty() } }
                    ),
                )
            )
        }

    val amenities = Amenity.entries.map { it.rawValue }
    val placeTypes = PlaceType.entries.map { it.rawValue }
    val tags = listOf(
        Place.Tags.Popular,
        Place.Tags.Recommended,
        "",
    )
}