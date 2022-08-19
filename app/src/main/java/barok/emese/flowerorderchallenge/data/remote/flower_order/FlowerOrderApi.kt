package barok.emese.flowerorderchallenge.data.remote.flower_order

import retrofit2.http.GET

interface FlowerOrderApi {

    @GET("flowerorders")
    suspend fun getFlowerOrders(): List<FlowerOrderDto>
}