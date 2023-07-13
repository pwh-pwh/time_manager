package com.coderpwh.timemanager.ui.screen.mainnav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coderpwh.timemanager.R
import com.coderpwh.timemanager.config.MainNavRoute
import com.coderpwh.timemanager.ui.screen.mainnav.countdown.CountDownView
import com.coderpwh.timemanager.ui.screen.mainnav.home.HomeView
import com.coderpwh.timemanager.ui.screen.mainnav.stopwatch.StopWatchView

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainNavView() {
    val navList = listOf(
        Pair("主页", R.drawable.home),
        Pair("倒计时", R.drawable.count),
        Pair("秒表", R.drawable.second)
    )
    var nowActiveIndex by remember {
        mutableStateOf(0)
    }

    val mainNavController = rememberNavController()
    mainNavController.addOnDestinationChangedListener {
        _,_,_ -> val a = {}
        println(a)
    }
    mainNavController.addOnDestinationChangedListener {
        _,destination,_ ->
            MainNavRoute.apply {
                when (destination.route) {
                    HOME -> {
                        nowActiveIndex = 0
                    }
                    COUNTDOWN -> {
                        nowActiveIndex = 1
                    }
                    STOPWATCH -> {
                        nowActiveIndex = 2
                    }
                }
            }
    }
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBar {
            navList.forEachIndexed { index, pair ->
                NavigationBarItem(
                    selected = nowActiveIndex == index,
                    onClick = {
                        nowActiveIndex = index
                        when (index) {
                            0 -> {
                                mainNavController.mainNavTo(MainNavRoute.HOME)
                            }
                            1 -> {
                                mainNavController.mainNavTo(MainNavRoute.COUNTDOWN)
                            }
                            2 -> {
                                mainNavController.mainNavTo(MainNavRoute.STOPWATCH)
                            }
                        }
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = pair.second),
                            contentDescription = pair.first,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    label = {
                        Text(text = pair.first)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedTextColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(navController = mainNavController, startDestination = MainNavRoute.HOME) {
                composable(route = MainNavRoute.HOME) {
                    HomeView()
                }
                composable(route = MainNavRoute.COUNTDOWN) {
                    CountDownView()
                }
                composable(route = MainNavRoute.STOPWATCH) {
                    StopWatchView()
                }
            }
        }
    }
}

fun NavHostController.mainNavTo(route:String) {
    this.navigate(route) {
        popUpTo(this@mainNavTo.graph.findStartDestination().id)
    }
}