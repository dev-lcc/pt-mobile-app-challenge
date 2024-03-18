package presentation.main

sealed class MainDestination(
    val path: String,
) {
    data object Home: MainDestination("/home")
    data object Tickets: MainDestination("/tickets")
    data object Favorites: MainDestination("/favorites")
    data object Account: MainDestination("/account")

    companion object {
        fun fromString(path: String): MainDestination? =
            when(path) {
                Home.path -> Home
                Tickets.path -> Tickets
                Favorites.path -> Favorites
                Account.path -> Account
                else -> null
            }
    }
}