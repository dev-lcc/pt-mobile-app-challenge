package presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ptmobileappchallenge.composeapp.generated.resources.Res
import ptmobileappchallenge.composeapp.generated.resources.bottom_nav_label_account
import ptmobileappchallenge.composeapp.generated.resources.bottom_nav_label_favorites
import ptmobileappchallenge.composeapp.generated.resources.bottom_nav_label_home
import ptmobileappchallenge.composeapp.generated.resources.bottom_nav_label_tickets
import ptmobileappchallenge.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    onNavigateToPlaceDetail: ((Long/*Place.id*/) -> Unit) = {},
) {

    val mainNavigator = rememberNavigator("main")
    val currentDestination = mainNavigator.currentEntry.collectAsState(null)

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 24.dp,
                    )
                    .clip(
                        RoundedCornerShape(32.dp)
                    )
                ,
                currentDestination = currentDestination.value?.route?.route?.let(MainDestination::fromString) ?: MainDestination.Home,
                onNavigate = { destination ->
                    mainNavigator.navigate(
                        route = destination.path,
                        options = NavOptions(
                            launchSingleTop = true,
                            includePath = true,
                            popUpTo = PopUpTo(
                                route = ""/*MainDestination.Home.path*/,
                                inclusive = true,
                            ),
                        )
                    )
                }
            )
        }
    ) { paddingValues ->

        NavHost(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            navigator = mainNavigator,
            initialRoute = MainDestination.Home.path,
        ) {

            //
            // "/home"
            //
            scene(
                route = MainDestination.Home.path,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.weight(1f))
                    Image(
                        modifier = Modifier.size(128.dp),
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier,
                        fontSize = 18.sp,
                        text = "${stringResource(Res.string.bottom_nav_label_home)} Screen",
                    )
                    Spacer(Modifier.weight(1f))
                }
            }

            //
            // "/tickets"
            //
            scene(
                route = MainDestination.Tickets.path,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.weight(1f))
                    Image(
                        modifier = Modifier.size(128.dp),
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier,
                        fontSize = 18.sp,
                        text = "${stringResource(Res.string.bottom_nav_label_tickets)} Screen",
                    )
                    Spacer(Modifier.weight(1f))
                }
            }

            //
            // "/favorites"
            //
            scene(
                route = MainDestination.Favorites.path,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.weight(1f))
                    Image(
                        modifier = Modifier.size(128.dp),
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier,
                        fontSize = 18.sp,
                        text = "${stringResource(Res.string.bottom_nav_label_favorites)} Screen",
                    )
                    Spacer(Modifier.weight(1f))
                }
            }

            //
            // "/account"
            //
            scene(
                route = MainDestination.Account.path,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.weight(1f))
                    Image(
                        modifier = Modifier.size(128.dp),
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier,
                        fontSize = 18.sp,
                        text = "${stringResource(Res.string.bottom_nav_label_account)} Screen",
                    )
                    Spacer(Modifier.weight(1f))
                }
            }

        }

    }

}