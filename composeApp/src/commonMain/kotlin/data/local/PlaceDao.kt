package data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import io.github.devlcc.ptmobileappchallenge.PTDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import io.github.devlcc.ptmobileappchallenge.Place as PlaceSOT

interface PlaceDao {

    suspend fun getAllPlaces(
        offset: Int,
        count: Int,
    ): List<PlaceSOT>

    fun getAllPlacesStream(
        offset: Int,
        count: Int,
    ): Flow<List<PlaceSOT>>

    suspend fun getPlaceById(placeId: Long): PlaceSOT
    fun getPlaceByIdStream(placeId: Long): Flow<PlaceSOT>

    suspend fun upsert(place: PlaceSOT)
    suspend fun upsert(batchCount: Int, vararg places: PlaceSOT)
    
    fun removeById(placeId: Long)

    fun removeRange(offset: Int, count: Int)

    fun removeAll()

}

class PlaceDaoImpl(
    private val database: PTDatabase,
) : PlaceDao {
    override suspend fun getAllPlaces(
        offset: Int,
        count: Int,
    ): List<PlaceSOT> {
        return database.placeQueries.getAllPlaces(
            offset = offset.toLong(),
            count = count.toLong(),
        )
            .executeAsList()
    }

    override fun getAllPlacesStream(
        offset: Int,
        count: Int,
    ): Flow<List<PlaceSOT>> {
        return database.placeQueries.getAllPlaces(
            offset = offset.toLong(),
            count = count.toLong(),
        )
            .asFlow()
            .mapToList(Dispatchers.Default)
    }

    override suspend fun getPlaceById(placeId: Long): PlaceSOT {
        return database.placeQueries.getPlaceById(placeId)
            .executeAsOne()
    }

    override fun getPlaceByIdStream(placeId: Long): Flow<PlaceSOT> {
        return database.placeQueries.getPlaceById(placeId)
            .asFlow()
            .mapToOne(Dispatchers.Default)
    }

    override suspend fun upsert(place: PlaceSOT) {
        database.transaction {
            internalUpsert(place)
        }
    }

    override suspend fun upsert(batchCount: Int, vararg places: PlaceSOT) {
        places.toList().chunked(batchCount).forEach { batchList ->
            database.transaction {
                batchList.forEach(::internalUpsert)
            }
        }
    }

    private fun internalUpsert(place: PlaceSOT) {
        database.placeQueries.upsertPlace(
            placeId = place.placeId,
            name = place.name,
            description = place.description,
            hostThumbnail = place.hostThumbnail,
            lat = place.lat,
            lng = place.lng,
            reviewsCount = place.reviewsCount,
            rating = place.rating,
            address = place.address,
            amenities = place.amenities,
            price = place.price,
            type = place.type,
            tags = place.tags,
            isFavorite = place.isFavorite,
        )
    }
    
    override fun removeById(placeId: Long) {
        database.transaction {
            database.placeQueries.removeById(placeId)
        }
    }

    override fun removeRange(offset: Int, count: Int) {
        database.transaction {
            database.placeQueries.removeRange(
                offset = offset.toLong(),
                count = count.toLong(),
                )
        }
    }

    override fun removeAll() {
        database.transaction {
            database.placeQueries.removeAll()
        }
    }

}