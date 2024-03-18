import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.AppNavigation
import presentation.onboarding.OnboardingScreen
import ptmobileappchallenge.composeapp.generated.resources.Res
import ptmobileappchallenge.composeapp.generated.resources.compose_multiplatform
import kotlin.random.Random

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()

        NavHost(
            navigator = navigator,
            initialRoute = AppNavigation.Onboarding.path,
        ) {

            //
            //  "/onboarding"
            //
            scene(
                route = AppNavigation.Onboarding.path,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OnboardingScreen(
                        modifier = Modifier.fillMaxSize(),
                        onClickExplore = {
                            navigator.navigate(AppNavigation.Main.path)
                        }
                    )
                }
            }

            //
            //  "/main"
            //
            scene(
                route = AppNavigation.Main.path,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        modifier = Modifier.size(64.dp),
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier,
                        fontSize = 18.sp,
                        text = "Main Screen"
                    )
                    Spacer(Modifier.height(36.dp))
                    AppFilledButton(
                        onClick = {
                            navigator.navigate(
                                AppNavigation.PlaceDetail(Random.nextLong(999_999_999L)).path
                            )
                        },
                        modifier = Modifier,
                        colors = AppButtonDefaults.filledButtonColors(
                            containerColor = AppColor.DarkerBlueAccent,
                            contentColor = Color.White
                        ),
                        cornerRadius = 18.dp,
                    ) {
                        Text("View Place")
                    }
                }
            }

            //
            //  "/place/{placeId}"
            //
            scene(
                route = AppNavigation.PlaceDetail.PATH,
            ) { navBackStackEntry ->
                
                val placeId = navBackStackEntry.path<String>(AppNavigation.PlaceDetail.ARG_NAME)
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
