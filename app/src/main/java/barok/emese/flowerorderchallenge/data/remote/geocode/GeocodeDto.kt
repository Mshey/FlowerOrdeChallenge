package barok.emese.flowerorderchallenge.data.remote.geocode

import com.squareup.moshi.Json

data class GeocodeDto(
    @field:Json(name = "geometry")
    val geometry: CoordinatesDto
)
