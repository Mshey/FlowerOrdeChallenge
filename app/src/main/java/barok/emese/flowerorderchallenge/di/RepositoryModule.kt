package barok.emese.flowerorderchallenge.di

import barok.emese.flowerorderchallenge.data.repository.FlowerOrderRepositoryImpl
import barok.emese.flowerorderchallenge.data.repository.GeocodingRepositoryImpl
import barok.emese.flowerorderchallenge.domain.repository.FlowerOrderRepository
import barok.emese.flowerorderchallenge.domain.repository.GeocodingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFlowerOrderRepository(flowerOrderRepositoryImpl: FlowerOrderRepositoryImpl): FlowerOrderRepository

    @Binds
    @Singleton
    abstract fun bingGeocodingRepository(geocodingRepositoryImpl: GeocodingRepositoryImpl): GeocodingRepository
}