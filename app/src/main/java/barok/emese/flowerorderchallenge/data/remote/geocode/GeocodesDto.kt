package barok.emese.flowerorderchallenge.data.remote.geocode

import com.squareup.moshi.Json

data class GeocodesDto(
    @field:Json(name = "features")
    val results: List<GeocodeDto>
)
