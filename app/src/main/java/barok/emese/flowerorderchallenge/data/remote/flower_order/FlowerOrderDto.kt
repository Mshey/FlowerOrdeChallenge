package barok.emese.flowerorderchallenge.data.remote.flower_order

import com.squareup.moshi.Json

data class FlowerOrderDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "price")
    val price: Int,
    @field:Json(name = "user_name")
    val userName: String,
    @field:Json(name = "deliver_to") // address where we should deliver
    val deliverTo: String
)
