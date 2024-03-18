package presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import designsystem.component.AppButtonDefaults
import designsystem.component.AppFilledButton
import designsystem.theme.AppColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ptmobileappchallenge.composeapp.generated.resources.Montserrat_Bold
import ptmobileappchallenge.composeapp.generated.resources.Res
import ptmobileappchallenge.composeapp.generated.resources.bg_onboarding
import ptmobileappchallenge.composeapp.generated.resources.ic_aspen
import ptmobileappchallenge.composeapp.generated.resources.onboarding_btn_explore
import ptmobileappchallenge.composeapp.generated.resources.onboarding_subtitle1
import ptmobileappchallenge.composeapp.generated.resources.onboarding_subtitle2

@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier,
    onClickExplore: (() -> Unit) = {},
) {

    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            bitmap = imageResource(Res.drawable.bg_onboarding),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {

            Spacer(Modifier.height(48.dp))
            Image(
                painter = painterResource(Res.drawable.ic_aspen),
                modifier = Modifier
                    .fillMaxWidth(0.80f)
                    .align(Alignment.CenterHorizontally),
                contentDescription = null,
            )

            Spacer(Modifier.weight(1f))

            Text(
                modifier = Modifier,
                text = stringResource(Res.string.onboarding_subtitle1),
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                modifier = Modifier,
                text = stringResource(Res.string.onboarding_subtitle2),
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
            )

            Spacer(Modifier.height(24.dp))

            AppFilledButton(
                onClick = { onClickExplore() },
                modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth(),
                colors = AppButtonDefaults.filledButtonColors(
                    containerColor = AppColor.BlueAccent,
                    contentColor = Color.White
                ),
                cornerRadius = 18.dp,
            ) {
                Text(
                    text = stringResource(Res.string.onboarding_btn_explore),
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontFamily = FontFamily(Font(resource = Res.font.Montserrat_Bold, weight = FontWeight.Bold)),
                        fontWeight = FontWeight.Bold
                    ),
                )
            }

        }
    }

}