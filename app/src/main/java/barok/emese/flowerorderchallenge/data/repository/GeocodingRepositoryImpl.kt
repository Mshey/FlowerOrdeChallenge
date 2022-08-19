package barok.emese.flowerorderchallenge.data.repository

import barok.emese.flowerorderchallenge.data.mapper.toLocationCoordinates
import barok.emese.flowerorderchallenge.data.remote.geocode.GeocodingApi
import barok.emese.flowerorderchallenge.domain.repository.GeocodingRepository
import barok.emese.flowerorderchallenge.domain.util.GenericCallback
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class GeocodingRepositoryImpl @Inject constructor(
    private val api: GeocodingApi
) : GeocodingRepository {
    override suspend fun getLatLngFromAddress(address: String): GenericCallback<LatLng> {
        return try {
            GenericCallback.GenericSuccess(
                data = api.getLatLngForAddress(address).toLocationCoordinates()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            GenericCallback.GenericError(
                message = e.message ?: "An error occurred while fetching the location coordinates."
            )
        }
    }
}