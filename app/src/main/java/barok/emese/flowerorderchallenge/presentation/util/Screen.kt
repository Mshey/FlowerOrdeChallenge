package barok.emese.flowerorderchallenge.presentation.util

sealed class Screen(val route: String) {
    object FlowerOrdersScreen: Screen("flower_orders_screen")
    object FlowerOrderDetailsScreen: Screen("flower_order_details_screen")
}
