package barok.emese.flowerorderchallenge.presentation.flower_order_details

import android.location.Location
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barok.emese.flowerorderchallenge.domain.location.LocationHandler
import barok.emese.flowerorderchallenge.domain.model.FlowerOrder
import barok.emese.flowerorderchallenge.domain.repository.GeocodingRepository
import barok.emese.flowerorderchallenge.domain.util.GenericCallback
import barok.emese.flowerorderchallenge.presentation.DataFetchingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class FlowerOrderDetailsViewModel @Inject constructor(
    private val repository: GeocodingRepository,
    private val locationHandler: LocationHandler,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(DataFetchingState<Pair<String, Float>>())
        private set

    private val _flowerOrder = mutableStateOf(FlowerOrder())
    val flowerOrder: State<FlowerOrder> = _flowerOrder

    init {
        savedStateHandle.get<FlowerOrder>("flowerOrder")?.let {
            _flowerOrder.value = it
        }

        getDistance()
    }

    private fun getDistance() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationHandler.getCurrentLocation()?.let { location ->
                when (val result = repository.getLatLngFromAddress(_flowerOrder.value.deliverTo)) {
                    is GenericCallback.GenericSuccess -> {
                        if (result == null || result.data == null) {
                            state = state.copy(
                                data = null,
                                isLoading = false,
                                error = "Returned location is null."
                            )
                        } else {
                            val delivery = Location("delivery")
                            delivery.latitude = result.data.latitude
                            delivery.longitude = result.data.longitude
                            val results = FloatArray(1)
                            Location.distanceBetween(
                                location.latitude, location.longitude,
                                result.data.latitude, result.data.longitude, results
                            )
                            val unit = if (results[0] > 100) "km" else "m"
                            var distance = if (results[0] > 100) results[0] / 1000 else results[0]
                            distance =
                                BigDecimal(distance.toDouble()).setScale(2, RoundingMode.HALF_EVEN)
                                    .toFloat()
                            state = state.copy(
                                data = Pair(unit, distance),
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is GenericCallback.GenericError -> {
                        state = state.copy(
                            data = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    data = null,
                    error = "Couldn't retrieve location, make sure to grant permission and turn on gps.",
                    isLoading = false
                )
            }
        }
    }
}