package com.example.flutter_alarm_manager_poc.activity

import android.os.Bundle
import androidx.activity.compose.setContent

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.flutter_alarm_manager_poc.screens.AlarmScreen

class AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  turnScreenOnAndKeyguardOff()
        actionBar?.hide()

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.onSurface) {
                    AlarmScreen(
                        onAccept = { finish() },
                        onSnooze = { finish() },

                        )
                }
            }
        }
    }
}