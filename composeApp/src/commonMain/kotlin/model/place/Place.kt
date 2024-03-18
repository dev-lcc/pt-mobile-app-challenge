package model.place

import kotlinx.serialization.Serializable
import model.CommonParcelable
import model.CommonParcelize

@Serializable
@CommonParcelize
data class Place(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val coverImage: String? = null,  // Cover Image
    val lat: Float? = null,    // latitude
    val lng: Float? = null,    // longiture
    val reviewsCount: Long? = null,
    val rating: Float? = null,
    val address: String? = null,    // fully qualitified address. i.e. "Clichy, Île-de-France, France" | "Paris, Île-de-France, France"
    val amenities: List<Amenity> = listOf(),
    val price: Price? = null,
    val type: PlaceType? = null,
    val tags: List<String> = listOf(),
    val isFavorite: Boolean? = null,
): CommonParcelable {
    
    @Serializable
    @CommonParcelize
    data class Price(
        val rate: Int? = null,
        val currency: /*Currency*/String? = null,
        val total: Float? = null,
        val priceItems: List<Item> = listOf(),
    ): CommonParcelable {
        
        @Serializable
        @CommonParcelize
        data class Item(
            val title: String? = null,
            val amount: Float? = null,
        ): CommonParcelable
    }

    object Tags {
        const val Popular = "popular"
        const val Recommended = "recommended"
    }
}