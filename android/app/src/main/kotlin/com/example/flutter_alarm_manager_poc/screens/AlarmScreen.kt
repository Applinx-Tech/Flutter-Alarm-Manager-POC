package com.example.flutter_alarm_manager_poc.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flutter_alarm_manager_poc.utils.convertMillisToTime

@Composable
fun AlarmScreen(
    onAccept: () -> Unit,
    onSnooze: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = convertMillisToTime(System.currentTimeMillis()),
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Text(
                text = "Alarm has been ringing!!!",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = CircleShape,
                        spotColor = Color(0xFF8A2BE2) // Purple shadow color
                    ),
                shape = CircleShape,
                color = Color(0xFF8A2BE2)
            ) {

            }

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(color = Color.Green, shape = CircleShape)
                        .clickable {
                            onAccept()
                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Tick Mark",
                        tint = Color.White, // This makes the icon white
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center) // Adjust the size of the icon as needed
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Accept",
                    style = TextStyle(color = Color.White, fontStyle = FontStyle.Normal)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(color = Color.Red, shape = CircleShape)
                        .clickable {
                            onSnooze()
                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Tick Mark",
                        tint = Color.White, // This makes the icon white
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center) // Adjust the size of the icon as needed
                    )

                }
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Snooze",
                    style = TextStyle(color = Color.White, fontStyle = FontStyle.Normal)
                )
            }
        }


    }
}

@Preview
@Composable
private fun PrevAlarmScreen() {
    AlarmScreen(onAccept = { /*TODO*/ }, onSnooze = { /*TODO*/ })
}