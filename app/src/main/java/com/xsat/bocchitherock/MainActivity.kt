package com.xsat.bocchitherock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xsat.bocchitherock.ui.screen.about.AboutScreen
import com.xsat.bocchitherock.ui.screen.detail.DetailScreen
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
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("about") {
            AboutScreen(onBackClick = { navController.popBackStack() })
        }
        composable("detail/{name}/{photoUrl}/{detail}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val photoUrl = backStackEntry.arguments?.getString("photoUrl") ?: ""
            val detail = backStackEntry.arguments?.getString("detail") ?: ""

            DetailScreen(name, photoUrl, detail, onBackClick = { navController.popBackStack() })
        }
    }
}