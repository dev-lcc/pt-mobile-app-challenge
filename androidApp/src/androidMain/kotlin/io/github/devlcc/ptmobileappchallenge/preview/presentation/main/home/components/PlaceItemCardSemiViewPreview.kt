package io.github.devlcc.ptmobileappchallenge.preview.presentation.main.home.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import designsystem.theme.AppTheme
import model.place.Amenity
import model.place.Place
import model.place.PlaceType
import presentation.main.home.components.PlaceItemCardSemiView

@Preview
@Composable
fun Preview_PlaceItemCardSemiView_FavoriteTrue() {
    AppTheme {
        Surface(
            color = Color.Transparent,
        ) {
            PlaceItemCardSemiView(
                modifier = Modifier.width(194.dp),
                item = TestDataPlaceItemCardSemiView.places.filter { it.isFavorite == true }.random(),
            )
        }
    }
}

@Preview
@Composable
fun Preview_PlaceItemCardSemiView_FavoriteFalse() {
    PlaceItemCardSemiView(
        modifier = Modifier.width(194.dp),
        item = TestDataPlaceItemCardSemiView.places.filter { it.isFavorite != true }.random(),
    )
}

object TestDataPlaceItemCardSemiView {
    val places = listOf(
        Place(
            id = 43449093L,
            name = "Room in the heart of Paris",
            description = "Comfortable and quiet apartment at the heart of Paris! Perfect for 1 to 4 people. Parisian cocooning apartment sublimated by an elegant decoration,ultra modern and design ,all equipped ideal for families, couples but also for business trips. It is located in an ideal area to enjoy day and night entertainment between the Montorgueil /Les Halles /Opera/Lafayette district. The apartment is on the bright ground floor in a secure residence.",
            coverImage = "https://a0.muscache.com/im/pictures/miso/Hosting-43449093/original/49a62f19-7135-45ae-9811-441891d5279d.jpeg?im_w=720",
            lat = 48.86339f,
            lng = 2.35232f,
            reviewsCount = 77,
            rating = 4.74f,
            address = "Paris, Île-de-France, France",
            amenities = listOf(
                Amenity.Wifi,
                Amenity.Heating,
            ),
            price = Place.Price(
                rate = 76,
                currency = "USD",
                total = 76f,
                priceItems = listOf(
                    Place.Price.Item(
                        title = "\$62 x 1 night",
                        amount = 62f,
                    ),
                    Place.Price.Item(
                        title = "Service fee",
                        amount = 10f,
                    ),
                    Place.Price.Item(
                        title = "Occupancy taxes and fees",
                        amount = 4f,
                    ),
                ),
            ),
            type = PlaceType.Location,
            tags = listOf(),
            isFavorite = true,
        ),
        Place(
            id = 29331544L,
            name = "Hôtel Boronali *** - Room w/ Balcony in Montmartre",
            description = "Book this room and indulge in a typical Parisian experience. A balcony set up with two chairs and a table will allow you to relax after a long day in the city while gazing at the Montmartre roofs. The perfect choice for a romantic getaway!",
            coverImage = "https://a0.muscache.com/im/pictures/045134fc-ee8d-49ec-8132-28cfa0823a7b.jpg?im_w=720",
            lat = 48.88869f,
            lng = 2.34793f,
            reviewsCount = 283,
            rating = 4.52f,
            address = "Paris, Île-de-France, France",
            amenities = listOf(
                Amenity.Wifi,
                Amenity.Heating,
            ),
            price = Place.Price(
                rate = 137,
                currency = "USD",
                total = 137f,
                priceItems = listOf(
                    Place.Price.Item(
                        title = "\$135 x 1 night",
                        amount = 135f,
                    ),
                    Place.Price.Item(
                        title = "Occupancy taxes and fees",
                        amount = 2f,
                    ),
                ),
            ),
            type = PlaceType.Location,
            tags = listOf(Place.Tags.Popular),
        ),
        Place(
            id = 54368898L,
            name = "Studio apartment, quiet and bright",
            description = "Come and stay in this nice 20m2 fully equipped and refurbished studio. The apartment is conveniently located and will allow you to reach the center of Paris in just 15 minutes. You will stay both in a quiet area and close to all necessary shops (Carrefour Express, bakery, pharmacy...)",
            coverImage = "https://a0.muscache.com/im/pictures/bfb3bdd5-22a6-4d02-8369-21d56660f34b.jpg?im_w=720",
            lat = 48.90118f,
            lng = 2.29792f,
            reviewsCount = 6,
            rating = 4.83f,
            address = "Clichy, Île-de-France, France",
            amenities = listOf(
                Amenity.Wifi,
                Amenity.Kitchen,
                Amenity.Washer,
                Amenity.Heating,
            ),
            price = Place.Price(
                rate = 77,
                currency = "USD",
                total = 77f,
                priceItems = listOf(
                    Place.Price.Item(
                        title = "\$63 x 1 night",
                        amount = 63f,
                    ),
                    Place.Price.Item(
                        title = "Service fee",
                        amount = 11f,
                    ),
                    Place.Price.Item(
                        title = "Occupancy taxes and fees",
                        amount = 3f,
                    ),
                ),
            ),
            type = PlaceType.Location,
            tags = listOf(Place.Tags.Popular),
        ),
        Place(
            id = 53700221L,
            name = "Chambre privée dans appartement parisien",
            description = "1st floor apartment in the center of the small peaceful spot in Paris. This fully equipped accommodation has two double bedrooms. The new premium bedding (Dolux) offers significant comfort. Through light and unobstructed view. Close to all amenities: small supermarket, bakery, butcher shop, chocolate shop, cider shop, bar, restaurant, takeaway pizzeria, creperie, press house are within walking distance within a radius of less than 200 meters.",
            coverImage = "https://a0.muscache.com/im/pictures/miso/Hosting-53700221/original/a7799717-0751-4b7c-a6f1-59baff77a2ff.jpeg?im_w=720",
            lat = 48.87892f,
            lng = 2.29638f,
            reviewsCount = 0,
            rating = null,
            address = " ",
            amenities = listOf(
                Amenity.Wifi,
                Amenity.Heating,
            ),
            price = Place.Price(
                rate = 35,
                currency = "USD",
                total = 35f,
                priceItems = listOf(
                    Place.Price.Item(
                        title = "\$28 x 1 night",
                        amount = 28f,
                    ),
                    Place.Price.Item(
                        title = "Service fee",
                        amount = 5f,
                    ),
                    Place.Price.Item(
                        title = "Occupancy taxes and fees",
                        amount = 2f,
                    ),
                ),
            ),
            tags = listOf(Place.Tags.Popular),
        ),
        Place(
            id = 54088138L,
            name = "Paris is a 20-minute walk to the Eiffel Tower",
            description = "Very calm independent apartment with panoramic view of Paris. Very bright and large bedroom with a wide glass wall, balcony/terrace. Equiped kitchenette, bathroom with shower and independent toilets. Metro at the foot of the building!",
            coverImage = "://a0.muscache.com/im/pictures/92cfa47a-d90a-4281-b8d1-a7dee1b556ef.jpg?im_w=720",
            lat = 48.84317f,
            lng = 2.28331f,
            reviewsCount = 3,
            rating = 4.67f,
            address = "Paris, Île-de-France, France",
            amenities = listOf(
                Amenity.Kitchen,
                Amenity.Heating,
            ),
            price = Place.Price(
                rate = 67,
                currency = "USD",
                total = 67f,
                priceItems = listOf(
                    Place.Price.Item(
                        title = "\$55 x 1 night",
                        amount = 55f,
                    ),
                    Place.Price.Item(
                        title = "Service fee",
                        amount = 9f,
                    ),
                    Place.Price.Item(
                        title = "Occupancy taxes and fees",
                        amount = 3f,
                    ),
                ),
            ),
            type = PlaceType.Location,
            tags = listOf(Place.Tags.Popular),
        ),
        Place(
            id = 31868079L,
            name = "Room & Pool",
            description = "Located in a quiet street in the heart of the 15th district of Paris, only 2km from Eiffel Tower and 20 min from Montparnasse train station and Beaugrenelle Mall, Hotel Eiffel Blomet welcomes you in an amazing 30s building entirely refurbished in an art deco style.",
            coverImage = "https://a0.muscache.com/im/pictures/c027a6cf-1a06-4228-9cdd-b39f95b0b4dc.jpg?im_w=720",
            lat = 48.84222f,
            lng = 2.3013f,
            reviewsCount = 367,
            rating = 4.85f,
            address = "Paris, Île-de-France Region, France",
            amenities = listOf(
                Amenity.Wifi,
                Amenity.Heating,
            ),
            price = Place.Price(
                rate = 176,
                currency = "USD",
                total = 176f,
                priceItems = listOf(
                    Place.Price.Item(
                        title = "\$170 x 1 night",
                        amount = 170f,
                    ),
                    Place.Price.Item(
                        title = "Occupancy taxes and fees",
                        amount = 6f,
                    ),
                ),
            ),
            type = PlaceType.Location,
            tags = listOf(Place.Tags.Popular),
        ),
        Place(
            id = 49992714L,
            name = "Lovely little house near Eiffel Tower 2/4P",
            description = "Indulge in the ultimate Parisian experience at our luxurious apartment. With breathtaking views and stunning architectural details, every element of this space is designed for an unforgettable adventure. Relax in the living room and enjoy a majestic panorama of the Eiffel Tower and the Seine River, our balconies offer unobstructed views of the iconic landmark.",
            coverImage = "://a0.muscache.com/im/pictures/miso/Hosting-49992714/original/e66017bc-ca03-4a9f-99d8-76016b90d149.png?im_w=720",
            lat = 48.85531f,
            lng = 2.30472f,
            reviewsCount = 39,
            rating = 4.41f,
            address = "Paris, Île-de-France, France",
            amenities = listOf(
                Amenity.Wifi,
                Amenity.Kitchen,
                Amenity.Washer,
                Amenity.Heating,
            ),
            price = Place.Price(
                rate = 189,
                currency = "USD",
                total = 189f,
                priceItems = listOf(
                    Place.Price.Item(
                        title = "\$106 x 1 night",
                        amount = 106f,
                    ),
                    Place.Price.Item(
                        title = "Cleaning fee",
                        amount = 51f,
                    ),
                    Place.Price.Item(
                        title = "Service fee",
                        amount = 26f,
                    ),
                    Place.Price.Item(
                        title = "Occupancy taxes and fees",
                        amount = 6f,
                    ),
                ),
            ),
            type = PlaceType.Location,
            tags = listOf(Place.Tags.Recommended),
        ),
        Place(
            id = 19796151L,
            name = "MAGNIFICENT VIEW EIFFEL TOWER PRIVATE STUDIO AND BALCONY",
            description = "The apartment is divided into 2 homes each closing with their key. The entrance to the apartment is common to both units. The accommodation for guests with a view on the Eiffel Tower is not shared, it's like a studio with a balcony. There is a bedroom area with a queen-size bed, 1 wardrobe, floor lamps, equipped kitchen, and small shower area. WC. The toilet closet is reserved for the cleaner, please do not use it. On the balcony: 1 table and 2 chairs, a space is reserved for gardening.",
            coverImage = "://a0.muscache.com/im/pictures/miso/Hosting-19796151/original/e92d2f61-68b1-4977-ade9-e7bad78bd637.jpeg?im_w=720",
            lat = 48.8543f,
            lng = 2.29572f,
            reviewsCount = 371,
            rating = 4.86f,
            address = "Paris, Île-de-France, France",
            amenities = listOf(
                Amenity.Wifi,
                Amenity.Kitchen,
                Amenity.Heating,
            ),
            price = Place.Price(
                rate = 454,
                currency = "USD",
                total = 454f,
                priceItems = listOf(
                    Place.Price.Item(
                        title = "\$305 x 1 night",
                        amount = 305f,
                    ),
                    Place.Price.Item(
                        title = "Cleaning fee",
                        amount = 79f,
                    ),
                    Place.Price.Item(
                        title = "Service fee",
                        amount = 64f,
                    ),
                    Place.Price.Item(
                        title = "Occupancy taxes and fees",
                        amount = 6f,
                    ),
                ),
            ),
            type = PlaceType.Location,
            tags = listOf(Place.Tags.Recommended),
        ),
        Place(
            id = 9825542L,
            name = "Charm in Heart of Paris Vaugirard",
            description = "Comfortable, spacious , near all amenities , on the ground floor, the 2 pieces of 50m2 consists of a large living room , a bedroom with large dressing room in the basement, a large kitchen, and a bathroom bathroom with toilet. Near the Eiffel Tower, of Champs Elysées and Paris Expo Porte de Versailles",
            coverImage = "https://a0.muscache.com/im/pictures/21f05d85-1f77-481f-9156-c590f76ddd63.jpg?im_w=720",
            lat = 48.83757f,
            lng = 2.29666f,
            reviewsCount = 414,
            rating = 4.41f,
            address = "Paris, Île-de-France, France",
            amenities = listOf(
                Amenity.Wifi,
                Amenity.Kitchen,
                Amenity.Washer,
            ),
            price = Place.Price(
                rate = 135,
                currency = "USD",
                total = 135f,
                priceItems = listOf(
                    Place.Price.Item(
                        title = "\$78 x 1 night",
                        amount = 78f,
                    ),
                    Place.Price.Item(
                        title = "Cleaning fee",
                        amount = 33f,
                    ),
                    Place.Price.Item(
                        title = "Service fee",
                        amount = 19f,
                    ),
                    Place.Price.Item(
                        title = "Occupancy taxes and fees",
                        amount = 5f,
                    ),
                ),
            ),
            type = PlaceType.Location,
            tags = listOf(Place.Tags.Recommended),
        ),
        Place(
            id = 15297775L,
            name = "TOUR EIFFEL VUE FANTASTIQU STUDIO ET BALCON PRIVES",
            description = "Located on the 9th and last floor, my apartment has a common entrance to two accommodations: 1.) the guest accommodation overlooking the Eiffel Tower is a bedroom, locked, with balcony, a bar separates the kitchen area, there is also a shower with sink. The toilets are not in the room they are in front of the door of the room and are not shared. 2.) the other dwelling belongs to the owner.",
            coverImage = "https://a0.muscache.com/im/pictures/b1cc348d-d8f0-44f5-8184-274fd4123243.jpg?im_w=720",
            lat = 48.85354f,
            lng = 2.29519f,
            reviewsCount = 314,
            rating = 4.78f,
            address = "Paris, Île-de-France, France",
            amenities = listOf(
                Amenity.Wifi,
                Amenity.Kitchen,
                Amenity.Washer,
                Amenity.Heating,
            ),
            price = Place.Price(
                rate = 454,
                currency = "USD",
                total = 454f,
                priceItems = listOf(
                    Place.Price.Item(
                        title = "\$305 x 1 night",
                        amount = 305f,
                    ),
                    Place.Price.Item(
                        title = "Cleaning fee",
                        amount = 79f,
                    ),
                    Place.Price.Item(
                        title = "Service fee",
                        amount = 64f,
                    ),
                    Place.Price.Item(
                        title = "Occupancy taxes and fees",
                        amount = 6f,
                    ),
                ),
            ),
            type = PlaceType.Location,
            tags = listOf(Place.Tags.Recommended),
        ),
    )
}