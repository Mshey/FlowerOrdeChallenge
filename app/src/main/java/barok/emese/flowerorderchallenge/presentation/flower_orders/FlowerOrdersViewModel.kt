package barok.emese.flowerorderchallenge.presentation.flower_orders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barok.emese.flowerorderchallenge.domain.model.FlowerOrder
import barok.emese.flowerorderchallenge.domain.repository.FlowerOrderRepository
import barok.emese.flowerorderchallenge.domain.util.GenericCallback
import barok.emese.flowerorderchallenge.presentation.DataFetchingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowerOrdersViewModel @Inject constructor(
    private val repository: FlowerOrderRepository
) : ViewModel() {
    var state by mutableStateOf(DataFetchingState<List<FlowerOrder>>())
        private set

    fun loadFlowerOrders() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            state = when (val result = repository.getFlowerOrders()) {
                is GenericCallback.GenericSuccess -> {
                    state.copy(data = result.data, isLoading = false, error = null)
                }
                is GenericCallback.GenericError -> {
                    state.copy(data = null, isLoading = false, error = result.message)
                }
            }

        }
    }

}