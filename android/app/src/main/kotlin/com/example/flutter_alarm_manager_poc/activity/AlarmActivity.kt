package com.example.flutter_alarm_manager_poc.activity

import android.os.Bundle
import androidx.activity.compose.setContent

import android.widget.Button
import androidx.activity.ComponentActivity

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.flutter_alarm_manager_poc.R
import com.example.flutter_alarm_manager_poc.screens.AlarmScreen
import com.example.flutter_alarm_manager_poc.utils.turnScreenOnAndKeyguardOff
import io.flutter.embedding.android.FlutterActivity

class AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        turnScreenOnAndKeyguardOff()

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.onSurface) {
                    AlarmScreen(
                        onAccept = { finish() },
                        onReject = { finish() },
                        onSnooze = {
                            // Handle snooze logic
                            finish()
                        }
                    )
                }
            }
        }
    }}