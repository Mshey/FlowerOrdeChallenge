package barok.emese.flowerorderchallenge.presentation.flower_orders

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import barok.emese.flowerorderchallenge.domain.model.FlowerOrder
import barok.emese.flowerorderchallenge.presentation.DataFetchingState
import barok.emese.flowerorderchallenge.presentation.util.Screen
import com.google.gson.Gson

@Composable
fun FlowerOrdersDisplay(
    navController: NavController,
    state: DataFetchingState<List<FlowerOrder>>,
    modifier: Modifier = Modifier
) {
    state.data.let { flowerOrders ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            val gson = Gson()
            if (flowerOrders != null) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(flowerOrders) {
                        flowerOrders.forEach {
                            FlowerOrderItemDisplay(
                                flowerOrder = it,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(vertical = 16.dp)
                                    .clickable {
                                        navController.navigate(
                                            Screen.FlowerOrderDetailsScreen.route +
                                                    "?flowerOrder=${gson.toJson(it)}"
                                        )
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}