package presentation

sealed class AppDestination(
    val path: String,
) {
    data object Onboarding : AppDestination("/onboarding")
    data object Main : AppDestination("/main")
    data class PlaceDetail(val placeId: Long): AppDestination("/place/$placeId") {
        companion object {
            const val ARG_NAME = "placeId"
            const val PATH = "/place/{${ARG_NAME}}"
        }
    }
}