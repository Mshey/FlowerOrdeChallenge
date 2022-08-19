package barok.emese.flowerorderchallenge.di

import android.app.Application
import barok.emese.flowerorderchallenge.data.remote.flower_order.FlowerOrderApi
import barok.emese.flowerorderchallenge.data.remote.geocode.GeocodingApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFlowerOrderApi(): FlowerOrderApi {
        return Retrofit.Builder()
            .baseUrl("http://demo5299756.mockable.io/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideGeocodingApi(): GeocodingApi {
        return Retrofit.Builder()
            .baseUrl("https://api.geoapify.com/v1/geocode/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
}