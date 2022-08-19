package barok.emese.flowerorderchallenge.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import barok.emese.flowerorderchallenge.domain.model.FlowerOrder
import barok.emese.flowerorderchallenge.presentation.flower_order_details.FlowerOrderDetailsDisplay
import barok.emese.flowerorderchallenge.presentation.flower_orders.FlowerOrdersDisplay
import barok.emese.flowerorderchallenge.presentation.flower_orders.FlowerOrdersViewModel
import barok.emese.flowerorderchallenge.presentation.ui.theme.LightGreen
import barok.emese.flowerorderchallenge.presentation.ui.theme.FlowerOrderChallengeTheme
import barok.emese.flowerorderchallenge.presentation.util.Screen
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val flowerOrdersViewModel: FlowerOrdersViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )

        flowerOrdersViewModel.loadFlowerOrders()

        val gson = Gson()
        val flowerOrderNavType: NavType<FlowerOrder> = object : NavType<FlowerOrder>(false) {
            override val name: String
                get() = "flowerOrder"

            override fun get(bundle: Bundle, key: String): FlowerOrder? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FlowerOrder {
                return gson.fromJson(value, object : TypeToken<FlowerOrder>() {}.type)
            }

            override fun put(bundle: Bundle, key: String, value: FlowerOrder) {
                bundle.putParcelable(key, value)
            }
        }

        setContent {
            FlowerOrderChallengeTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LightGreen)
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.FlowerOrdersScreen.route
                    ) {
                        composable(route = Screen.FlowerOrdersScreen.route) {
                            FlowerOrdersDisplay(
                                navController = navController,
                                state = flowerOrdersViewModel.state
                            )
                            if (flowerOrdersViewModel.state.isLoading) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                            flowerOrdersViewModel.state.error?.let { error ->
                                Text(
                                    text = error,
                                    color = Color.Red,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                        composable(
                            route = Screen.FlowerOrderDetailsScreen.route +
                                    "?flowerOrder={flowerOrder}",
                            arguments = listOf(
                                navArgument(
                                    name = "flowerOrder"
                                ) {
                                    type = flowerOrderNavType
                                }
                            )
                        ) {
                            FlowerOrderDetailsDisplay(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}