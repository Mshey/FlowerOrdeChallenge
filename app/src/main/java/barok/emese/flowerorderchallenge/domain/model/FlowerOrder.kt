package barok.emese.flowerorderchallenge.domain.model

import android.os.Parcel
import android.os.Parcelable

data class FlowerOrder(
    val id: Int,
    val description: String,
    val price: Int,
    val userName: String,
    val deliverTo: String,
    var orderStatus: OrderStatus
) : Parcelable {

    constructor() : this(-1, "", -1, "", "", OrderStatus.NEW)

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        OrderStatus.valueOf(parcel.readString() ?: "")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(description)
        parcel.writeInt(price)
        parcel.writeString(userName)
        parcel.writeString(deliverTo)
        parcel.writeString(orderStatus.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FlowerOrder> {
        override fun createFromParcel(parcel: Parcel): FlowerOrder {
            return FlowerOrder(parcel)
        }

        override fun newArray(size: Int): Array<FlowerOrder?> {
            return arrayOfNulls(size)
        }
    }

    enum class OrderStatus {
        NEW,
        PENDING,
        DELIVERED
    }
}
