package io.github.devlcc.ptmobileappchallenge.preview.presentation.main.home.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import designsystem.theme.AppTheme
import model.place.Amenity
import model.place.Place
import model.place.PlaceType
import presentation.main.home.components.PlaceItemCardSemiView
import presentation.main.home.test.HomeScreenTestData

@Preview
@Composable
fun Preview_PlaceItemCardSemiView_FavoriteTrue() {
    AppTheme {
        Surface(
            color = Color.Transparent,
        ) {
            PlaceItemCardSemiView(
                modifier = Modifier.width(194.dp),
                item = HomeScreenTestData.places.filter { it.isFavorite == true }.random(),
            )
        }
    }
}

@Preview
@Composable
fun Preview_PlaceItemCardSemiView_FavoriteFalse() {
    PlaceItemCardSemiView(
        modifier = Modifier.width(194.dp),
        item = HomeScreenTestData.places.filter { it.isFavorite != true }.random(),
    )
}
