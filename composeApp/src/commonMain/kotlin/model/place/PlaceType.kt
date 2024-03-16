package model.place

import model.CommonParcelable
import model.CommonParcelize

@CommonParcelize
enum class PlaceType(val rawValue: String): CommonParcelable {
    Location("location"),
    Hotel("hotel"),
    Food("food"),
    Adventure("adventure"),
}