package presentation.main.home

import model.place.Place

sealed class HomeEffect {

    data class NavigateToPlaceDetail(val which: Place): HomeEffect()

    data object NavigateToPopularPlaceList: HomeEffect()

    data object ErrorFetchPlaces: HomeEffect()

}