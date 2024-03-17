package data.store.converters

import data.network.dto.place.PlaceDTO
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import model.place.Place
import io.github.devlcc.ptmobileappchallenge.Place as PlaceSOT

fun PlaceDTO.mapToLocal(
    json: Json = Json
) =
    PlaceSOT(
        placeId = this.id!!,
        name = this.name,
        description = this.description,
        hostThumbnail = this.hostThumbnail,
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
        hostThumbnail = this.hostThumbnail,
        lat = this.lat?.toDouble(),
        lng = this.lng?.toDouble(),
        reviewsCount = this.reviewsCount?.toLong(),
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
