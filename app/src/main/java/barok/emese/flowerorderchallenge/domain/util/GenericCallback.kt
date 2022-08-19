package barok.emese.flowerorderchallenge.domain.util

sealed class GenericCallback<T>(val data: T? = null, val message: String? = null) {
    class GenericSuccess<T>(data: T?): GenericCallback<T>(data)
    class GenericError<T>(message: String, data: T? = null): GenericCallback<T>(data, message)
}
