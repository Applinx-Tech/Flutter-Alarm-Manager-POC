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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.flutter_alarm_manager_poc.utils.convertMillisToDate
import com.example.flutter_alarm_manager_poc.utils.convertMillisToTime
import org.w3c.dom.Text

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
                text = convertMillisToDate(System.currentTimeMillis()),
                style = MaterialTheme.typography.titleMedium,
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
                color = Color(0xFF8A2BE2) // This will be the border color
            ) {
                Box(
                    modifier = Modifier.padding(4.dp), // This creates the border effect
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = "https://play-lh.googleusercontent.com/KGOmMhN6spxlHwZrtHvhQ1L0ZbokbKIHBAJTjmwF40yW9KVnCYt6AdpSQOFMVaLmj7o",
                        contentDescription = "Profile image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ButtonAction(icon = Icons.Filled.Check, text = "Accept", onClick = onAccept)
            ButtonAction(icon = Icons.Filled.Close, text = "Snooze", onClick = onSnooze)
        }


    }
}

@Composable
fun ButtonAction(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(60.dp)
                .background(color = Color.White, shape = CircleShape)
                .clickable {
                    onClick()
                }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint =  Color(0xFF8A2BE2), // This makes the icon white
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center) // Adjust the size of the icon as needed
            )

        }
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = text,
            style = TextStyle(color = Color.White, fontStyle = FontStyle.Normal)
        )
    }
}

@Preview
@Composable
private fun PrevAlarmScreen() {
    AlarmScreen(onAccept = { /*TODO*/ }, onSnooze = { /*TODO*/ })
}