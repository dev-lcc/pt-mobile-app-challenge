package presentation.main.home

import model.place.Place
import model.place.PlaceType

sealed class HomeEvent {

    data class SearchTextInput(val text: String): HomeEvent()

    data object Refresh: HomeEvent()

    /**
     * On Place Type(i.e. `Location`, `Hotels`, etc.) chip selected
     */
    data class SelectPlaceType(val selected: PlaceType): HomeEvent()

    /**
     * On tap Popular -> `See all` button
     */
    data object SeeAllPopularPlaces: HomeEvent()

    /**
     * On tap Place item
     */
    data class OpenPlaceDetail(val which: Place): HomeEvent()

}