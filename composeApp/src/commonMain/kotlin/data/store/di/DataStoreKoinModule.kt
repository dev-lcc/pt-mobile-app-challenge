package data.store.di

import PlaceStore
import data.store.ListPlacesStore
import org.koin.core.module.Module
import org.koin.dsl.module


fun getDataStoreModule(): Module = module {
    
    single {
        ListPlacesStore(
            placesApiService = get(),
            placesLocalDao = get(),
            json = get(),
        )
    }
    
    single {
        PlaceStore(
            placesApiService = get(),
            placesLocalDao = get(),
            json = get(),
        )
    }
    
}