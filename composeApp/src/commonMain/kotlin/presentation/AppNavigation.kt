package presentation

sealed class AppNavigation(
    val path: String,
) {
    data object Onboarding : AppNavigation("/onboarding")
    data object Main : AppNavigation("/main")
    data class PlaceDetail(val placeId: Long): AppNavigation("/place/$placeId") {
        companion object {
            const val ARG_NAME = "placeId"
            const val PATH = "/place/{${ARG_NAME}}"
        }
    }
}