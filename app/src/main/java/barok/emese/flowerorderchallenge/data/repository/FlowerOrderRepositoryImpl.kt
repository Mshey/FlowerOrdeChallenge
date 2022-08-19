package barok.emese.flowerorderchallenge.data.repository

import barok.emese.flowerorderchallenge.data.mapper.toFlowerOrders
import barok.emese.flowerorderchallenge.data.remote.flower_order.FlowerOrderApi
import barok.emese.flowerorderchallenge.domain.model.FlowerOrder
import barok.emese.flowerorderchallenge.domain.repository.FlowerOrderRepository
import barok.emese.flowerorderchallenge.domain.util.GenericCallback
import javax.inject.Inject

class FlowerOrderRepositoryImpl @Inject constructor(
    private val api: FlowerOrderApi
): FlowerOrderRepository {
    override suspend fun getFlowerOrders(): GenericCallback<List<FlowerOrder>> {
        return try {
            GenericCallback.GenericSuccess(
                data = api.getFlowerOrders().toFlowerOrders()
            )
        } catch (e:Exception){
            e.printStackTrace()
            GenericCallback.GenericError(
                message = e.message?:"An error occurred while fetching flower order data."
            )
        }
    }
}