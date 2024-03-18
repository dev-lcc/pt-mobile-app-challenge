package presentation.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import designsystem.theme.AppColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import presentation.main.home.HomeEvent
import ptmobileappchallenge.composeapp.generated.resources.Res
import ptmobileappchallenge.composeapp.generated.resources.ic_location_blue
import ptmobileappchallenge.composeapp.generated.resources.ic_arrow_down
import ptmobileappchallenge.composeapp.generated.resources.ic_search

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeTopBarView(
    modifier: Modifier,
    onEvent: ((HomeEvent) -> Unit) = {},
) {

    var searchTextValue by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier,
        ) {
            Column(
                modifier = Modifier,
            ) {
                Text(
                    modifier = Modifier,
                    text = "Explore",/*stringResource(Res.string.home_header_popular)*/
                    color = AppColor.TextPrimary,
                    /*style = MaterialTheme.typography.headlineMedium,*/
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                )
                Text(
                    modifier = Modifier,
                    text = "Aspen",/*stringResource(Res.string.home_header_popular)*/
                    color = AppColor.TextPrimary,
                    /*style = MaterialTheme.typography.headlineMedium,*/
                    fontWeight = FontWeight.Medium,
                    fontSize = 26.sp,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 12.dp,
                        vertical = 4.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(Res.drawable.ic_location_blue),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    modifier = Modifier,
                    text = "Aspen, USA",
                    color = AppColor.TextSecondary,
                    fontWeight = FontWeight.Normal,
                    fontSize = TextUnit(12f, TextUnitType.Sp),
                )
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(resource = Res.drawable.ic_arrow_down),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
                Spacer(Modifier.width(4.dp))
            }
        }

        Spacer(Modifier.height(24.dp))
        
        // InputTextField
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchTextValue,
            singleLine = true,
            textStyle = MaterialTheme.typography.labelMedium.copy(
                fontFamily = FontFamily.Default,
                color = AppColor.TextTertiary,
            ),
            leadingIcon = {
                Image(
                    imageVector = vectorResource(Res.drawable.ic_search),
                    colorFilter = ColorFilter.tint(AppColor.TextTertiary.copy(alpha = 0.25f)),
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(text = "Find things to do")
            },
            onValueChange = { newText ->
                searchTextValue = newText
                onEvent(HomeEvent.SearchTextInput(newText.text))
            },
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = AppColor.LightBlueAccent.copy(alpha = 0.05f),
                unfocusedContainerColor = AppColor.LightBlueAccent.copy(alpha = 0.05f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }

}