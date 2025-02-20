package com.xsat.bocchitherock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.xsat.bocchitherock.ui.screen.home.HomeActivity
import com.xsat.bocchitherock.ui.theme.BocchiTheRockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BocchiTheRockTheme {
                    HomeActivity()
            }
        }
    }
}