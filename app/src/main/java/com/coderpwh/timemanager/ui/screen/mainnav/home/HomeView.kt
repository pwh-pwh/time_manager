package com.coderpwh.timemanager.ui.screen.mainnav.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coderpwh.timemanager.ui.theme.TimeManagerTheme

@Composable
fun HomeView() {
    val homeViewModel:HomeViewModel = viewModel()
    val timeState by homeViewModel.timeState.collectAsState()
    val uiState by homeViewModel.uiState.collectAsState()
    val dateState by homeViewModel.dateState.collectAsState()
    val orientationState by rememberUpdatedState(newValue =  LocalConfiguration.current.orientation)
   LaunchedEffect(orientationState) {
        if(orientationState==1) {
            homeViewModel.sendUIIntent(UIIntent.ChangeImmersionState(false))
        } else {
            homeViewModel.sendUIIntent(UIIntent.ChangeImmersionState(true))
        }
   }
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    val pmActive = when(uiState.timeMode) {
                        TimeMode.PM -> true
                        TimeMode.AM -> false
                    }
                    OutlinedButton(onClick = { /*TODO*/ },colors=ButtonDefaults.buttonColors(
                        containerColor = if(pmActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
                    ), shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)) {
                        Text(text = "PM", color = if (pmActive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                    OutlinedButton(onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(topEnd = 10.dp,bottomEnd = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if(!pmActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Text(text = "AM",color = if (!pmActive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                }
                Switch(checked = uiState.immersionShow, onCheckedChange = {
                    homeViewModel.sendUIIntent(UIIntent.ChangeImmersionState(!uiState.immersionShow))
                })
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column {
                    Text(text = "${dateState.day}${dateState.week}",
                        modifier = Modifier.padding(bottom = 15.dp, start = 5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TimeTag(content = timeState.hour)
                        Text(text = ":")
                        TimeTag(content = timeState.minute)
                        Text(text = ":")
                        TimeTag(content = timeState.second)
                    }
                }
            }

        }
    }
}

@Composable
fun TimeTag(content: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ), modifier = Modifier.padding(vertical = 5.dp)
    ) {
        Text(text = content.toString(), Modifier.padding(horizontal = 10.dp, vertical = 12.dp))
    }
}

@Composable
@Preview
fun HomeViewPreview() {
    TimeManagerTheme {
        HomeView()
    }
}