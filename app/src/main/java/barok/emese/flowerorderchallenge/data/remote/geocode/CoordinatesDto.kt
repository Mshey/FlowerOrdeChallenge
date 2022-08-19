package barok.emese.flowerorderchallenge.data.remote.geocode

import com.squareup.moshi.Json

data class CoordinatesDto(
    @field:Json(name = "coordinates")
    val coordinates: List<Double>
)
