package presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import designsystem.theme.AppColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ptmobileappchallenge.composeapp.generated.resources.Res
import ptmobileappchallenge.composeapp.generated.resources.bottom_nav_label_account
import ptmobileappchallenge.composeapp.generated.resources.bottom_nav_label_favorites
import ptmobileappchallenge.composeapp.generated.resources.bottom_nav_label_home
import ptmobileappchallenge.composeapp.generated.resources.bottom_nav_label_tickets
import ptmobileappchallenge.composeapp.generated.resources.ic_nav_account
import ptmobileappchallenge.composeapp.generated.resources.ic_nav_favorites
import ptmobileappchallenge.composeapp.generated.resources.ic_nav_home
import ptmobileappchallenge.composeapp.generated.resources.ic_nav_tickets

class HomeNavigationItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit,
    val label: @Composable () -> Unit
) {
    companion object {
        @OptIn(ExperimentalResourceApi::class)
        fun buildNavigationItems(
            currentDestination: MainDestination, onNavigate: (destination: MainDestination) -> Unit
        ): List<HomeNavigationItem> {
            return listOf(
                MainDestination.Home,
                MainDestination.Tickets,
                MainDestination.Favorites,
                MainDestination.Account,
            ).map { rootDestination ->
                val textLabelResId = when (rootDestination) {
                    is MainDestination.Home -> Res.string.bottom_nav_label_home
                    is MainDestination.Tickets -> Res.string.bottom_nav_label_tickets
                    is MainDestination.Favorites -> Res.string.bottom_nav_label_favorites
                    is MainDestination.Account -> Res.string.bottom_nav_label_account
                }
                val selected = when (rootDestination) {
                    is MainDestination.Home -> currentDestination is MainDestination.Home
                    is MainDestination.Tickets -> currentDestination is MainDestination.Tickets
                    is MainDestination.Favorites -> currentDestination is MainDestination.Favorites
                    is MainDestination.Account -> currentDestination is MainDestination.Account
                }

                HomeNavigationItem(
                    selected = selected,
                    onClick = {
                        onNavigate(rootDestination)
                    },
                    icon = {
                        when (rootDestination) {
                            is MainDestination.Home -> {
                                Image(
                                    modifier = Modifier.size(32.dp),
                                    imageVector = vectorResource(Res.drawable.ic_nav_home),
                                    colorFilter = ColorFilter.tint(
                                        if (selected) AppColor.BlueAccent else AppColor.Disabled
                                    ),
                                    contentDescription = stringResource(Res.string.bottom_nav_label_home),
                                )
                            }

                            is MainDestination.Tickets -> {
                                Image(
                                    modifier = Modifier.size(32.dp),
                                    imageVector = vectorResource(Res.drawable.ic_nav_tickets),
                                    colorFilter = ColorFilter.tint(
                                        if (selected) AppColor.BlueAccent else AppColor.Disabled
                                    ),
                                    contentDescription = null,
                                )
                            }

                            is MainDestination.Favorites -> {
                                Image(
                                    modifier = Modifier.size(32.dp),
                                    imageVector = vectorResource(Res.drawable.ic_nav_favorites),
                                    colorFilter = ColorFilter.tint(
                                        if (selected) AppColor.BlueAccent else AppColor.Disabled
                                    ),
                                    contentDescription = null,
                                )
                            }

                            is MainDestination.Account -> {
                                Image(
                                    modifier = Modifier.size(32.dp),
                                    imageVector = vectorResource(Res.drawable.ic_nav_account),
                                    colorFilter = ColorFilter.tint(
                                        if (selected) AppColor.BlueAccent else AppColor.Disabled
                                    ),
                                    contentDescription = null,
                                )
                            }
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(textLabelResId),
                            fontSize = 10.sp,
                        )
                    },

                )
            }
        }
    }
}