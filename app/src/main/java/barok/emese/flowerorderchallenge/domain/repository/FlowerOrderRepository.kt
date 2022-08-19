package barok.emese.flowerorderchallenge.domain.repository

import barok.emese.flowerorderchallenge.domain.model.FlowerOrder
import barok.emese.flowerorderchallenge.domain.util.GenericCallback

interface FlowerOrderRepository {
    suspend fun getFlowerOrders(): GenericCallback<List<FlowerOrder>>
}