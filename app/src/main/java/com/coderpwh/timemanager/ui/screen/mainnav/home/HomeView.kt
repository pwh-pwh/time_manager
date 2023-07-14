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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coderpwh.timemanager.ui.theme.TimeManagerTheme

@Composable
fun HomeView() {
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    OutlinedButton(onClick = { /*TODO*/ },colors=ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ), shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)) {
                        Text(text = "PM")
                    }
                    OutlinedButton(onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(topEnd = 10.dp,bottomEnd = 10.dp)
                    ) {
                        Text(text = "AM")
                    }
                }
                Switch(checked = false, onCheckedChange = {})
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column {
                    Text(text = "2020/02/11",
                        modifier = Modifier.padding(bottom = 15.dp, start = 5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TimeTag(text = "11")
                        TimeTag(text = "11")
                        TimeTag(text = ":")
                        TimeTag(text = "11")
                        TimeTag(text = "11")
                    }
                }
            }

        }
    }
}

@Composable
fun TimeTag(text: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ), modifier = Modifier.padding(vertical = 5.dp)
    ) {
        Text(text = text, Modifier.padding(horizontal = 10.dp, vertical = 12.dp))
    }
}

@Composable
@Preview
fun HomeViewPreview() {
    TimeManagerTheme {
        HomeView()
    }
}