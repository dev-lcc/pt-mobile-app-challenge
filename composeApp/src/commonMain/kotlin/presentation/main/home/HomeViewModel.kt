package presentation.main.home

import data.store.KeyListPlaces
import data.store.ListPlacesStore
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.place.Place
import model.place.PlaceType
/*import moe.tlaster.precompose.stateholder.SavedStateHolder*/
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.store.store5.StoreReadResponse
import org.mobilenativefoundation.store.store5.impl.extensions.fresh

class HomeViewModel(
    private val listPlacesStore: ListPlacesStore,
    /*private val savedStateHolder: SavedStateHolder,*/
) : ViewModel() {

    private val store: Store<KeyListPlaces, List<Place>> = listPlacesStore()

    private val _searchTextInput: MutableStateFlow<String> = MutableStateFlow("")
    private val _selectedType: MutableStateFlow<PlaceType> = MutableStateFlow(PlaceType.Location)
    private var offset: Int = 0

    @OptIn(FlowPreview::class)
    val uiState: StateFlow<HomeUiState> =
        combine(
            _searchTextInput
                .debounce(2_000L)
                .distinctUntilChanged(),
            _selectedType,
            store
                .stream(
                    request = StoreReadRequest.freshWithFallBackToSourceOfTruth(
                        KeyListPlaces(
                            offset,
                            LIMIT
                        )
                    )
                )
        ) { search: String, type: PlaceType, storeValue: StoreReadResponse<List<Place>> ->
            storeValue.throwIfError()

            val isLoading = storeValue.dataOrNull() == null
            val places = storeValue.dataOrNull()
                ?.filter {
                    it.type == type
                }
                ?.filter {
                    if(search.length >= 3) {
                        it.name?.contains(search)== true
                                || it.description?.contains(search)== true
                                || it.address?.contains(search)== true
                    } else true
                }
                ?: listOf()
            val isEmpty = places.isEmpty()
            val popular = places.filter { it.tags.contains(Place.Tags.Popular) }
            val popularIsEmpty = popular.isEmpty()
            val recommended = places.filter { it.tags.contains(Place.Tags.Recommended) }
            val recommendedIsEmpty = recommended.isEmpty()

            HomeUiState(
                isLoading = isLoading,
                selectedType = type,
                listings = when {
                    isEmpty -> HomeUiState.Listings.Empty
                    else -> {
                        HomeUiState.Listings.Content(
                            popular = if(popularIsEmpty) {
                                HomeUiState.Listings.Content.Popular.Empty
                            } else {
                                HomeUiState.Listings.Content.Popular.Content(popular)
                            },
                            recommended = if(recommendedIsEmpty) {
                                HomeUiState.Listings.Content.Recommended.Empty
                            } else {
                                HomeUiState.Listings.Content.Recommended.Content(recommended)
                            }
                        )
                    }
                }
            )
        }
            .catch { _ ->
                _effect.emit(HomeEffect.ErrorFetchPlaces)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = HomeUiState(
                    isLoading = true,
                )
            )

    private val _effect = MutableSharedFlow<HomeEffect>()
    val effect: SharedFlow<HomeEffect> = _effect.asSharedFlow()

    fun handle(event: HomeEvent) {
        viewModelScope.launch {
            when(event) {
                is HomeEvent.SearchTextInput -> {

                }
                is HomeEvent.Refresh -> {
                    this@HomeViewModel.offset = 0
                    store.fresh(KeyListPlaces(offset, LIMIT))
                }
                is HomeEvent.SelectPlaceType -> {
                    _selectedType.update { event.selected }
                }
                is HomeEvent.OpenPlaceDetail -> {
                    _effect.emit(HomeEffect.NavigateToPlaceDetail(event.which))
                }
                is HomeEvent.SeeAllPopularPlaces -> {
                    _effect.emit(HomeEffect.NavigateToPopularPlaceList)
                }
            }
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}