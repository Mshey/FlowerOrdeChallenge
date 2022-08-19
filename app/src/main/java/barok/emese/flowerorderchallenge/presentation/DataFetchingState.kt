package barok.emese.flowerorderchallenge.presentation

data class DataFetchingState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
