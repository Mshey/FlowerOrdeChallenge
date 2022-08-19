package barok.emese.flowerorderchallenge.data.remote.geocode

import barok.emese.flowerorderchallenge.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApi {

    @GET("search?apiKey=${BuildConfig.GeoApiKey}")
    suspend fun getLatLngForAddress(@Query("text") address: String): GeocodesDto
}