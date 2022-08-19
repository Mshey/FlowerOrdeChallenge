package barok.emese.flowerorderchallenge.domain.repository

import barok.emese.flowerorderchallenge.domain.util.GenericCallback
import com.google.android.gms.maps.model.LatLng

interface GeocodingRepository {
    suspend fun getLatLngFromAddress(address: String): GenericCallback<LatLng>
}