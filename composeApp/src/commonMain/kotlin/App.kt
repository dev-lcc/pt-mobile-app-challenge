import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import designsystem.component.AppButtonDefaults
import designsystem.component.AppFilledButton
import designsystem.theme.AppColor
import model.place.Place
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.AppDestination
import presentation.main.MainScreen
import presentation.onboarding.OnboardingScreen

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navigator = navigator,
            initialRoute = AppDestination.Onboarding.path,
        ) {

            //
            //  "/onboarding"
            //
            scene(
                route = AppDestination.Onboarding.path,
            ) {
                OnboardingScreen(
                    modifier = Modifier.fillMaxSize(),
                    onClickExplore = {
                        navigator.navigate(
                            route = AppDestination.Main.path,
                            options = NavOptions(
                                popUpTo = PopUpTo.First(true),
                            )
                        )
                    }
                )
            }

            //
            //  "/main"
            //
            scene(
                route = AppDestination.Main.path,
            ) {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigateToPlaceDetail = { place: Place ->
                        place.id?.let { placeId ->
                            navigator.navigate(
                                AppDestination.PlaceDetail(placeId).path,
                            )
                        }
                    },
                )
            }

            //
            //  "/place/{placeId}"
            //
            scene(
                route = AppDestination.PlaceDetail.PATH,
            ) { navBackStackEntry ->
                
                val placeId = navBackStackEntry.path<String>(AppDestination.PlaceDetail.ARG_NAME)
                    ?.toLong(10)

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        fontSize = 18.sp,
                        text = "Place Detail Screen"
                    )

                    if(placeId != null) {
                        Text(
                            modifier = Modifier,
                            fontSize = 16.sp,
                            text = "ID: $placeId"
                        )
                    } else {
                        Text(
                            modifier = Modifier,
                            fontSize = 16.sp,
                            text = "Place ID NOT Found!"
                        )
                    }
                    Spacer(Modifier.height(36.dp))
                    AppFilledButton(
                        onClick = {
                            navigator.goBack()
                        },
                        modifier = Modifier,
                        colors = AppButtonDefaults.filledButtonColors(
                            containerColor = AppColor.GreenAccent,
                            contentColor = Color.White
                        ),
                        cornerRadius = 18.dp,
                    ) {
                        Text("Go back")
                    }
                }
            }

        }

    }
}
