package com.xsat.bocchitherock.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object Favorite : Screen("favorite")
    object DetailBocchi : Screen("detail/{bocchiId}") {
        fun createRoute(bocchiId: String) = "detail/$bocchiId"
    }
}
