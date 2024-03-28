package di

import org.koin.core.module.Module
import org.koin.dsl.module
import presentation.main.home.HomeViewModel

fun getViewModelsModule(): Module = module {
    factory<HomeViewModel> {
        HomeViewModel(
            listPlacesStore = get(),
        )
    }

    // TODO:: Add ViewModule declarations here...
}