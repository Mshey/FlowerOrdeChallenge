package barok.emese.flowerorderchallenge.domain.location

import android.location.Location

interface LocationHandler {
    suspend fun getCurrentLocation(): Location?


}