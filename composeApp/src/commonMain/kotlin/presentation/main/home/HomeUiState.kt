package presentation.main.home

import model.place.Place
import model.place.PlaceType

data class HomeUiState(
    val isLoading: Boolean,
    val selectedType: PlaceType = PlaceType.Location,
    val listings: Listings = Listings.Empty,
) {
    sealed class Listings {
        data object Empty: Listings()
        data class Content(
            val popular: Popular = Popular.Empty,
            val recommended: Recommended = Recommended.Empty,
        ): Listings() {
            sealed class Popular {
                data object Empty: Popular()
                data class Content(val places: List<Place>): Popular()
            }
            sealed class Recommended {
                data object Empty: Recommended()
                data class Content(val places: List<Place>): Recommended()
            }
        }
    }

}