package data.network.dto.place

import kotlinx.serialization.Serializable

@Serializable
data class GetPlacesResponse(
    val limit: Long? = null,
    val offset: Long? = null,
    val qty: Long? = null,
    val totalQty: Long? = null,
    val data: List<PlaceDTO> = listOf(),
)