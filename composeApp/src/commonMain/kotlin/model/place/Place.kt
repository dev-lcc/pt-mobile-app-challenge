package model.place

import model.CommonParcelable
import model.CommonParcelize

@CommonParcelize
data class Place(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val hostThumbnail: String? = null,  // Cover Image
    val lat: Double? = null,    // latitude
    val lng: Double? = null,    // longiture
    val reviewsCount: Long? = null,
    val rating: Double? = null,
    val address: String? = null,    // fully qualitified address. i.e. "Clichy, Île-de-France, France" | "Paris, Île-de-France, France"
    val amenities: List<Amenity> = listOf(),
    val price: Price? = null,
    val type: PlaceType? = null,
    val tags: List<String> = listOf(),
): CommonParcelable {
    
    @CommonParcelize
    data class Price(
        val rate: Int? = null,
        val currency: /*Currency*/String? = null,
        val total: Float? = null,
        val priceItems: List<Item> = listOf(),
    ): CommonParcelable {
        
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