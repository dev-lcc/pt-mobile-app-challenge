package presentation.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import model.place.Place
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ptmobileappchallenge.composeapp.generated.resources.Res
import ptmobileappchallenge.composeapp.generated.resources.ic_favorite_red
import ptmobileappchallenge.composeapp.generated.resources.ic_star_mini

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PlaceItemCardFullView(
    modifier: Modifier,
    item: Place,
) {

    Box(
        modifier = modifier
            .aspectRatio(0.75f)
            .clip(
                shape = RoundedCornerShape(24.dp)
            )
            .background(Color.LightGray)
    ) {

        if(item.coverImage != null) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = item.coverImage,
                contentScale = ContentScale.FillHeight,
                contentDescription = null,
            )
        } else {
            Spacer(
                Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
            ,
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            Column(
                modifier = Modifier.weight(2f)
            ) {

                Text(
                    modifier = Modifier
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(59.dp)
                        )
                        .padding(
                            horizontal = 12.dp,
                            vertical = 4.dp,
                        )
                    ,
                    text = item.name ?: "",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                    ),
                    /*fontSize = TextUnit(16f, TextUnitType.Sp),*/
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(59.dp)
                        )
                        .padding(
                            horizontal = 12.dp,
                            vertical = 4.dp,
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(resource = Res.drawable.ic_star_mini),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        modifier = Modifier,
                        text = item.rating?.toString() ?: "---",
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = TextUnit(14f, TextUnitType.Sp),
                    )
                    Spacer(Modifier.width(4.dp))
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
            ) {
                if(item.isFavorite == true) {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(resource = Res.drawable.ic_favorite_red),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,
                    )
                }
            }

        }


    }

}

