package model.place

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.CommonParcelable
import model.CommonParcelize

@Serializable
@CommonParcelize
enum class PlaceType(val rawValue: String): CommonParcelable {
    @SerialName("location") Location("location"),
    @SerialName("hotel") Hotel("hotel"),
    @SerialName("food") Food("food"),
    @SerialName("adventure") Adventure("adventure"),
}