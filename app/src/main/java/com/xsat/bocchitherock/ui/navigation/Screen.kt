package com.xsat.bocchitherock.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object About : Screen("about")
    data object Favorite : Screen("favorite")
    data object DetailBocchi : Screen("detail/{bocchiId}") {
        fun createRoute(bocchiId: String) = "detail/$bocchiId"
    }
}
