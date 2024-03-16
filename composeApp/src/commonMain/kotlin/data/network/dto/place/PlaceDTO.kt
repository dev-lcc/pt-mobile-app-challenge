package data.network.dto.place

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDTO(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val hostThumbnail: String? = null,  // Cover Image URL
    val lat: Float? = null,    // latitude
    val lng: Float? = null,    // longiture
    val reviewsCount: Long? = null,
    val rating: Float? = null,
    val address: String? = null,    // fully qualitified address. i.e. "Clichy, Île-de-France, France" | "Paris, Île-de-France, France"
    @SerialName("previewAmenities") val amenities: List<String> = listOf(),
    val price: PriceDTO? = null,
    val type: String? = null,   // "location", "hotel", "food", "adventure"
    val tags: List<String> = listOf(),
) {
    
    @Serializable
    data class PriceDTO(
        val rate: Int? = null,
        val currency: /*Currency*/String? = null,
        val total: Float? = null,
        val priceItems: List<Item> = listOf(),
    ) {
        
        @Serializable
        data class Item(
            val title: String? = null,
            val amount: Float? = null,
        )
    }
    
}