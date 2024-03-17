package model.place

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.CommonParcelable
import model.CommonParcelize

@Serializable
@CommonParcelize
enum class Amenity(val rawValue: String): CommonParcelable {
    @SerialName("Heating") Heating("Heating"),
    @SerialName("Kitchen") Kitchen("Kitchen"),
    @SerialName("Washer") Washer("Washer"),
    @SerialName("Wifi") Wifi("Wifi"),
    @SerialName("Tub") Tub("Tub"),
    @SerialName("Pool") Pool("Pool"),
}