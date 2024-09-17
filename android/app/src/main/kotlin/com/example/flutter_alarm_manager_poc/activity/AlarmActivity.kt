package com.example.flutter_alarm_manager_poc.activity

import android.os.Bundle
import androidx.activity.compose.setContent

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.flutter_alarm_manager_poc.screens.AlarmScreen
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class AlarmActivity : ComponentActivity() {
    private val CHANNEL = "com.example/alarm_manager"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()

        val flutterEngine = FlutterEngine(this)
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        val channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.onSurface) {
                    AlarmScreen(
                        onAccept = {
                            channel.invokeMethod("alarmAccepted", null)
                            finish()
                        },
                        onSnooze = {
                            channel.invokeMethod("alarmSnoozed", null)
                            finish()
                        }
                    )
                }
            }
        }
    }
}