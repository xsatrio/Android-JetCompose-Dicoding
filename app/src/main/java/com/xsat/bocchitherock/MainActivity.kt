package com.xsat.bocchitherock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xsat.bocchitherock.ui.navigation.Screen
import com.xsat.bocchitherock.ui.screen.about.AboutScreen
import com.xsat.bocchitherock.ui.screen.detail.DetailScreen
import com.xsat.bocchitherock.ui.screen.favorite.FavoriteScreen
import com.xsat.bocchitherock.ui.screen.home.HomeScreen
import com.xsat.bocchitherock.ui.theme.BocchiTheRockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BocchiTheRockTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.About.route) {
            AboutScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Screen.DetailBocchi.route) {
            val bocchiId = it.arguments?.getString("bocchiId") ?: ""
            DetailScreen(id = bocchiId, onBackClick = { navController.popBackStack() })
        }
    }
}