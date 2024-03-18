package data.store.converters

import data.network.dto.place.PlaceDTO
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import model.place.Amenity
import model.place.Place
import model.place.PlaceType
import io.github.devlcc.ptmobileappchallenge.Place as PlaceSOT

fun PlaceDTO.mapToLocal(
    json: Json = Json
) =
    PlaceSOT(
        placeId = this.id!!,
        name = this.name,
        description = this.description,
        coverImage = this.images.firstOrNull(),
        lat = this.lat?.toDouble(),
        lng = this.lng?.toDouble(),
        reviewsCount = this.reviewsCount,
        rating = this.rating?.toDouble(),
        address = this.address,
        amenities = json.encodeToString(ListSerializer(String.serializer()), this.amenities),
        price = this.price?.let { price ->
            json.encodeToString(
                PlaceDTO.PriceDTO.serializer(),
                price
            )
        },
        type = this.type,
        tags = json.encodeToString(ListSerializer(String.serializer()), this.tags),
        isFavorite = if (this.isFavorite == true) 1 else 0,
    )

fun Place.mapToLocal(
    json: Json = Json
) =
    PlaceSOT(
        placeId = this.id!!,
        name = this.name,
        description = this.description,
        coverImage = this.coverImage,
        lat = this.lat?.toDouble(),
        lng = this.lng?.toDouble(),
        reviewsCount = this.reviewsCount,
        rating = this.rating?.toDouble(),
        address = this.address,
        amenities = json.encodeToString(ListSerializer(String.serializer()), this.amenities.map { it.rawValue }),
        price = this.price?.let { price ->
            json.encodeToString(
                Place.Price.serializer(),
                price
            )
        },
        type = this.type?.rawValue,
        tags = json.encodeToString(ListSerializer(String.serializer()), this.tags),
        isFavorite = if (this.isFavorite == true) 1 else 0,
    )

fun PlaceSOT.mapToOutput(
    json: Json = Json,
) =
    Place(
        id = this.placeId,
        name = this.name,
        description = this.description,
        coverImage = this.coverImage,
        lat = this.lat?.toFloat(),
        lng = this.lng?.toFloat(),
        reviewsCount = this.reviewsCount,
        rating = this.rating?.toFloat(),
        address = this.address,
        amenities = this.amenities?.let {
            try {
                json.decodeFromString(ListSerializer(Amenity.serializer()), it)
            } catch (err: SerializationException) {
                null
            }
        } ?: listOf(),
        price = this.price?.let { price ->
            json.decodeFromString(
                Place.Price.serializer(),
                price
            )
        },
        type = this.type?.let { type -> PlaceType.entries.find { it.rawValue == type } },
        tags = this.tags?.let { json.decodeFromString(ListSerializer(String.serializer()), it) } ?: listOf(),
        isFavorite = this.isFavorite == 1L,
    )