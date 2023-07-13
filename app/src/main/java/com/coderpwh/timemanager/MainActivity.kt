package com.coderpwh.timemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coderpwh.timemanager.config.APPRoute
import com.coderpwh.timemanager.ui.screen.mainnav.MainNavView
import com.coderpwh.timemanager.ui.screen.start.StartPageView
import com.coderpwh.timemanager.ui.theme.TimeManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeManagerTheme {
                val appNavController = rememberNavController()
                NavHost(navController = appNavController, startDestination = APPRoute.START_SCREEN) {
                    composable(APPRoute.START_SCREEN) {
                        StartPageView(appNavController)
                    }
                    composable(APPRoute.MAIN_NAV) {
                        MainNavView()
                    }
                }
            }
        }
    }
}
