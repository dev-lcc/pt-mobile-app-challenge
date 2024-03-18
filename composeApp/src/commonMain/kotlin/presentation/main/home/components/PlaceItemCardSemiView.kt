package presentation.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import designsystem.theme.AppColor
import model.place.Place
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ptmobileappchallenge.composeapp.generated.resources.Res
import ptmobileappchallenge.composeapp.generated.resources.ic_trending_up

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PlaceItemCardSemiView(
    modifier: Modifier,
    item: Place,
) {

    Column(
        modifier = modifier
            .padding(bottom = 4.dp)
            .clip(
                shape = RoundedCornerShape(24.dp)
            )
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if(item.coverImage != null) {
                AsyncImage(
                    modifier = Modifier
                        .height(124.dp)
                        .fillMaxWidth()
                        .clip(
                            shape = RoundedCornerShape(24.dp)
                        )
                        .background(Color.LightGray)
                    ,
                    model = item.coverImage,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            } else {
                Spacer(
                    Modifier
                        .height(124.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = -8.dp, y = 12.dp)
                    .background(
                        color = AppColor.DarkestGreenAccent,
                        shape = RoundedCornerShape(59.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(59.dp),
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                ,
                text = "4D/3N",
                /*style = MaterialTheme.typography.labelMedium,*/
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = TextUnit(14f, TextUnitType.Sp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .padding(
                    horizontal = 12.dp,
                )
            ,
            text = item.name ?: "",
            /*style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),*/
            color = AppColor.TextPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(16f, TextUnitType.Sp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )

        Row(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp,
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(resource = Res.drawable.ic_trending_up),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
            Spacer(Modifier.width(6.dp))
            Text(
                modifier = Modifier,
                text = "${item.price?.currency ?: ""} ${item.price?.rate?.toString(10) ?: "---"}",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = AppColor.TextSecondary,
                ),
            )
            Spacer(Modifier.width(4.dp))
        }

    }

}

