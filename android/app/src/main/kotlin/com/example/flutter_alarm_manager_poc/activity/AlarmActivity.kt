package com.example.flutter_alarm_manager_poc.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.flutter_alarm_manager_poc.screens.AlarmScreen
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel


class AlarmActivity : ComponentActivity() {
    private val ENGINE_ID = "alarm_manager_engine"
    private val CHANNEL = "com.example/alarm_manager"
    private var flutterEngine: FlutterEngine? = null
    private var isNewEngineCreated = false // create new engine when app is closed and use existing when app is resumed state

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()

        // Check if a cached engine is available
        flutterEngine = FlutterEngineCache.getInstance().get(ENGINE_ID)

        if (flutterEngine == null) {
            // If no cached engine is found (app was killed), create a new one
            flutterEngine = FlutterEngine(this).apply {
                dartExecutor.executeDartEntrypoint(
                    DartExecutor.DartEntrypoint.createDefault()
                )
            }
            // Optionally, cache this new engine if needed later
            FlutterEngineCache.getInstance().put(ENGINE_ID, flutterEngine)
            isNewEngineCreated = true;
        }

        // Set up the MethodChannel
        val channel = MethodChannel(flutterEngine!!.dartExecutor.binaryMessenger, CHANNEL)

        // Set the content of the AlarmActivity using Jetpack Compose
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

    override fun onDestroy() {
        super.onDestroy()

        // Only destroy the engine when the notification activity is launched from killed state
        // Do not kill the engine when the app is in running state otherwise it will lead to multiple flutter main-call stacks.
        if (isNewEngineCreated) {
            flutterEngine?.destroy()
            FlutterEngineCache.getInstance().remove(ENGINE_ID)
        }
    }
}