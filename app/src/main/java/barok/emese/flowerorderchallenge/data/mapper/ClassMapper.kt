package barok.emese.flowerorderchallenge.data.mapper

import barok.emese.flowerorderchallenge.data.remote.flower_order.FlowerOrderDto
import barok.emese.flowerorderchallenge.data.remote.geocode.GeocodesDto
import barok.emese.flowerorderchallenge.domain.model.FlowerOrder
import com.google.android.gms.maps.model.LatLng

fun List<FlowerOrderDto>.toFlowerOrders(): List<FlowerOrder> {
    return map {
        FlowerOrder(it.id, it.description, it.price, it.userName, it.deliverTo, FlowerOrder.OrderStatus.NEW)
    }
}

fun GeocodesDto.toLocationCoordinates(): LatLng? {
    if (results == null || results[0].geometry == null || results[0].geometry.coordinates == null) {
        return null
    }
    return LatLng(results[0].geometry.coordinates[1], results[0].geometry.coordinates[0])
}