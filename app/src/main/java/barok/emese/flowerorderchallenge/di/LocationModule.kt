package barok.emese.flowerorderchallenge.di

import barok.emese.flowerorderchallenge.data.location.LocationHandlerImpl
import barok.emese.flowerorderchallenge.domain.location.LocationHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(locationHandlerImpl: LocationHandlerImpl): LocationHandler
}