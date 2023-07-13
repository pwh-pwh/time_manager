package com.coderpwh.timemanager.ui.screen.start


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.coderpwh.timemanager.config.APPRoute
import com.coderpwh.timemanager.ui.theme.TimeManagerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StartPageView(navC: NavHostController) {
    val circleColor = MaterialTheme.colorScheme.primaryContainer
    val lineColor = MaterialTheme.colorScheme.onPrimaryContainer
    var lineAlpha by remember {
        mutableStateOf(0f)
    }
    val animatable = remember {
        Animatable(0f)
    }
    var contentShow by remember {
        mutableStateOf(false)
    }
    var iconShow by remember {
        mutableStateOf(false)
    }
    var lineShow by remember {
        mutableStateOf(false)
    }
    val canvasRotation by animateFloatAsState(targetValue = if(lineAlpha==1f) 360f else 0f,
        animationSpec = tween(durationMillis = 1000),label = "")
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            delay(500)
            iconShow = true
            delay(500)
            lineShow = true
            animatable.animateTo(
                targetValue = 1f, animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
        }
        withContext(Dispatchers.IO) {
            delay(1500)
            contentShow = true
        }
        delay(1000)
        navC.navigate(APPRoute.MAIN_NAV)
    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(visible = iconShow, enter = scaleIn()) {
                Canvas(modifier = Modifier.size(100.dp).rotate(canvasRotation)) {
                    drawCircle(
                        color = circleColor,
                        radius = size.minDimension / 2, center = Offset(size.width / 2, size.height / 2)
                    )
                    if (lineShow) {
                        lineAlpha = animatable.value
                        drawLine(
                            color = lineColor,
                            start = Offset(size.width / 2, size.height / 2),
                            end = Offset(size.width / 2, size.height / 2 + size.minDimension*5/12),
                            strokeWidth = 3.dp.toPx(),
                            cap = StrokeCap.Round,
                            alpha = lineAlpha
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = contentShow,
                enter = expandVertically(expandFrom = Alignment.Bottom) + fadeIn(
                    initialAlpha = 0.1f
                )
            ) {
                Text(text = "时间管理大师", fontWeight = FontWeight.Bold)
            }

        }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun StartPageViewPreview() {
    val circleColor = MaterialTheme.colorScheme.primaryContainer
    val lineColor = MaterialTheme.colorScheme.onPrimaryContainer
    var lineAlpha by remember {
        mutableStateOf(0f)
    }
    val animatable = remember {
        Animatable(0f)
    }
    var contentShow by remember {
        mutableStateOf(false)
    }
    var iconShow by remember {
        mutableStateOf(false)
    }
    var lineShow by remember {
        mutableStateOf(false)
    }
    val canvasRotation by animateFloatAsState(targetValue = if(lineAlpha==1f) 360f else 0f,
        animationSpec = tween(durationMillis = 1000),label = "")
    LaunchedEffect(Unit) {
        launch {
            delay(500)
            iconShow = true
            delay(500)
            lineShow = true
            animatable.animateTo(
                targetValue = 1f, animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
        }
        launch {
            delay(1500)
            contentShow = true
        }
        delay(1000)
    }
    TimeManagerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(visible = iconShow, enter = scaleIn()) {
                Canvas(modifier = Modifier.size(100.dp).rotate(canvasRotation)) {
                    drawCircle(
                        color = circleColor,
                        radius = size.minDimension / 2, center = Offset(size.width / 2, size.height / 2)
                    )
                    if (lineShow) {
                        lineAlpha = animatable.value
                        drawLine(
                            color = lineColor,
                            start = Offset(size.width / 2, size.height / 2),
                            end = Offset(size.width / 2, size.height / 2 + size.minDimension*5/12),
                            strokeWidth = 3.dp.toPx(),
                            cap = StrokeCap.Round,
                            alpha = lineAlpha
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = contentShow,
                enter = expandVertically(expandFrom = Alignment.Bottom) + fadeIn(
                    initialAlpha = 0.1f
                )
            ) {
                Text(text = "时间管理大师", fontWeight = FontWeight.Bold)
            }

        }
    }
}