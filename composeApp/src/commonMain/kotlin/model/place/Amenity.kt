package model.place

import model.CommonParcelable
import model.CommonParcelize

@CommonParcelize
enum class Amenity(val rawValue: String): CommonParcelable {
    Heating("Heating"),
    Kitchen("Kitchen"),
    Washer("Washer"),
    Wifi("Wifi"),
    Tub("Tub"),
    Pool("Pool"),
}