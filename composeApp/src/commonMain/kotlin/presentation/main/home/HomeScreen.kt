package presentation.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import designsystem.component.AppButtonDefaults
import designsystem.component.AppTextButton
import designsystem.theme.AppColor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import model.place.Place
import model.place.PlaceType
import moe.tlaster.precompose.flow.flowWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.lifecycle.LocalLifecycleOwner
import presentation.main.home.components.HomeTopBarView
import presentation.main.home.components.PlaceItemCardFullView
import presentation.main.home.components.PlaceItemCardSemiView

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = koinViewModel(HomeViewModel::class),
    onNavigateToPlaceDetail: ((Place) -> Unit) = {},
    onNavigateToSeeAll: (() -> Unit) = {},
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleAwareFlow = remember(viewModel.uiState, lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle)
    }
    val uiState by lifecycleAwareFlow.collectAsState(initial = viewModel.uiState.value)

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::handle
    )

    LaunchedEffect(Unit) {
        viewModel.effect
            .onEach { effect ->
                when(effect) {
                    is HomeEffect.NavigateToPlaceDetail -> {
                        onNavigateToPlaceDetail(effect.which)
                    }
                    is HomeEffect.NavigateToPopularPlaceList -> {
                        onNavigateToSeeAll()
                    }
                    is HomeEffect.ErrorFetchPlaces -> {
                        // TODO:: Prompt Error
                    }
                }
            }
            .launchIn(this)
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    uiState: HomeUiState,
    onEvent: ((HomeEvent) -> Unit) = {},
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HomeTopBarView(
                modifier = Modifier.fillMaxWidth(),
                onEvent = onEvent,
            )
        },
    ) { paddingValues ->

        val pullRefreshState = rememberPullRefreshState(
            refreshing = uiState.isLoading,
            onRefresh = {
                onEvent(HomeEvent.Refresh)
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pullRefresh(pullRefreshState)
                .verticalScroll(rememberScrollState())
            ,
            contentAlignment = Alignment.TopStart,
        ) {

            PullRefreshIndicator(
                refreshing = uiState.isLoading,
                state = pullRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .width(36.dp)
                    .offset(y = 8.dp),
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                ,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(Modifier.height(24.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val items = PlaceType.entries
                    items(items.size) {
                        val isSelected = uiState.selectedType == items[it]
                        Box(Modifier.padding(end = 8.dp)) {
                            FilterChip(
                                selected = isSelected,
                                colors = FilterChipDefaults.filterChipColors(
                                    containerColor = Color.Transparent,
                                    selectedContainerColor = AppColor.LightBlueAccent.copy(alpha = 0.05f),
                                ),
                                border = null,
                                shape = RoundedCornerShape(32.dp),
                                label = {
                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = 12.dp,
                                            vertical = 12.dp
                                        ),
                                        text = items[it].rawValue.capitalize(Locale.current),
                                        color = if (isSelected) AppColor.LightBlueAccent else AppColor.Disabled,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                },
                                onClick = {
                                    // Toggle selected place type
                                    onEvent(HomeEvent.SelectPlaceType(items[it]))
                                },
                            )
                        }
                    }
                }


                when (val listings = uiState.listings) {
                    is HomeUiState.Listings.Empty -> {
                        // Empty Home Content
                    }

                    is HomeUiState.Listings.Content -> {

                        when (val popular = listings.popular) {
                            is HomeUiState.Listings.Content.Popular.Empty -> {
                                // TODO:: Empty Popular
                            }

                            is HomeUiState.Listings.Content.Popular.Content -> {
                                Spacer(Modifier.height(24.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(
                                        modifier = Modifier.padding(start = 16.dp),
                                        text = "Popular",/*stringResource(Res.string.home_header_popular)*/
                                        color = AppColor.TextPrimary,
                                        /*style = MaterialTheme.typography.headlineMedium,*/
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 26.sp,
                                    )
                                    Spacer(Modifier.weight(1f))
                                    AppTextButton(
                                        onClick = { onEvent(HomeEvent.SeeAllPopularPlaces) },
                                        small = true,
                                        colors = AppButtonDefaults.textButtonColors(
                                            containerColor = Color.Transparent,
                                            contentColor = AppColor.BlueAccent
                                        ),
                                        text = {
                                            Text(text = "See all")
                                        }
                                    )
                                }
                                Spacer(Modifier.height(12.dp))
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    items(
                                        count = popular.places.size,
                                        key = { index -> popular.places[index].id ?: 0 }
                                    ) { index ->
                                        PlaceItemCardFullView(
                                            modifier = Modifier
                                                .padding(
                                                    start = if (index > 0) 16.dp else 0.dp,
                                                    end = if (index < popular.places.size) 16.dp else 0.dp,
                                                )
                                                .width(240.dp)
                                                .clickable(
                                                    interactionSource = MutableInteractionSource(),
                                                    indication = rememberRipple(color = AppColor.LightBlueAccent),
                                                ) {
                                                    // Navigate to Place Detail
                                                    onEvent(HomeEvent.OpenPlaceDetail(popular.places[index]))
                                                },
                                            item = popular.places[index]
                                        )
                                    }
                                }
                            }
                        }

                        when (val recommended = listings.recommended) {
                            is HomeUiState.Listings.Content.Recommended.Empty -> {
                                // TODO:: Empty Recommended
                            }

                            is HomeUiState.Listings.Content.Recommended.Content -> {
                                Spacer(Modifier.height(24.dp))
                                Text(
                                    modifier = Modifier.padding(start = 16.dp),
                                    text = "Recommended",/*stringResource(Res.string.home_header_recommended)*/
                                    color = AppColor.TextPrimary,
                                    /*style = MaterialTheme.typography.headlineMedium,*/
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 26.sp,
                                )
                                Spacer(Modifier.height(12.dp))
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    items(
                                        count = recommended.places.size,
                                        key = { index -> recommended.places[index] },
                                    ) { index ->
                                        PlaceItemCardSemiView(
                                            modifier = Modifier
                                                .padding(
                                                    start = if (index > 0) 16.dp else 0.dp,
                                                    end = if (index < recommended.places.size) 16.dp else 0.dp,
                                                )
                                                .width(220.dp)
                                                .clickable(
                                                    interactionSource = MutableInteractionSource(),
                                                    indication = rememberRipple(color = AppColor.LightBlueAccent),
                                                ) {
                                                    // Navigate to Place Detail
                                                    onEvent(HomeEvent.OpenPlaceDetail(recommended.places[index]))
                                                },
                                            item = recommended.places[index]
                                        )
                                    }
                                }
                            }
                        }

                    }
                }

            }
        }

    }

}