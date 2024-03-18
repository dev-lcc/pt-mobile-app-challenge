package io.github.devlcc.ptmobileappchallenge.preview.presentation.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import designsystem.theme.AppTheme
import io.github.devlcc.ptmobileappchallenge.preview.presentation.main.home.components.TestDataPlaceItemCardFullView
import io.github.devlcc.ptmobileappchallenge.preview.presentation.main.home.components.TestDataPlaceItemCardSemiView
import model.place.PlaceType
import presentation.main.home.HomeScreen
import presentation.main.home.HomeUiState

@Preview
@Composable
fun HomeScreenPreview_Empty() {
    AppTheme(/*typography = MaterialTheme.typography*/) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.inverseSurface
        ) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = HomeUiState(
                    isLoading = false,
                    selectedType = PlaceType.Location,
                    listings = HomeUiState.Listings.Empty,
                )
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview_Listings_Content_WithPopular_NoRecommended() {
    AppTheme(/*typography = MaterialTheme.typography*/) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.inverseSurface
        ) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = HomeUiState(
                    isLoading = false,
                    selectedType = PlaceType.Location,
                    listings = HomeUiState.Listings.Content(
                        popular = HomeUiState.Listings.Content.Popular.Content(
                            TestDataPlaceItemCardFullView.places
                        ),
                        recommended = HomeUiState.Listings.Content.Recommended.Empty
                    ),
                )
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview_Listings_Content_NoPopular_WithRecommended() {
    AppTheme(/*typography = MaterialTheme.typography*/) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.inverseSurface
        ) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = HomeUiState(
                    isLoading = false,
                    selectedType = PlaceType.Location,
                    listings = HomeUiState.Listings.Content(
                        popular = HomeUiState.Listings.Content.Popular.Empty,
                        recommended = HomeUiState.Listings.Content.Recommended.Content(
                            TestDataPlaceItemCardSemiView.places
                        )
                    ),
                )
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview_Listings_Content_Full() {
    AppTheme(/*typography = MaterialTheme.typography*/) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.inverseSurface
        ) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = HomeUiState(
                    isLoading = false,
                    selectedType = PlaceType.Location,
                    listings = HomeUiState.Listings.Content(
                        popular = HomeUiState.Listings.Content.Popular.Content(
                            TestDataPlaceItemCardFullView.places
                        ),
                        recommended = HomeUiState.Listings.Content.Recommended.Content(
                            TestDataPlaceItemCardSemiView.places
                        )
                    ),
                )
            )
        }
    }
}