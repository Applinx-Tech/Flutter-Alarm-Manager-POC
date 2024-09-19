package com.example.flutter_alarm_manager_poc

import android.util.Log
import com.example.flutter_alarm_manager_poc.alarmScheduler.AlarmScheduler
import com.example.flutter_alarm_manager_poc.alarmScheduler.AlarmSchedulerImpl
import com.example.flutter_alarm_manager_poc.model.AlarmItem
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {

    private val ENGINE_ID="alarm_manager_engine"
    private val CHANNEL = "com.example/alarm_manager"
    private val TAG = "POC"

    private lateinit var alarmScheduler: AlarmScheduler


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        FlutterEngineCache.getInstance().put(ENGINE_ID,flutterEngine)

        alarmScheduler = AlarmSchedulerImpl(this)


        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            when (call.method) {
                "scheduleAlarms" -> {
                    Log.d(TAG, "Method Channel Invoked, Alarms Scheduling")
                    val alarms = call.argument<List<Map<String, Any>>>("alarms")
                    if (alarms != null) {
                        scheduleAlarms(alarms)
                        result.success(null)
                    } else {
                        result.error("INVALID_ARGUMENT", "Alarms list is null", null)
                    }
                }
                "alarmAccepted" -> {
                    Log.d(TAG, "Alarm Accepted")
                    // Handle alarm accepted
                    result.success(null)
                }
                "alarmSnoozed" -> {
                    Log.d(TAG, "Alarm Snoozed")
                    // Handle alarm snoozed
                    result.success(null)
                }
                else -> result.notImplemented()
            }
        }
    }

    private fun scheduleAlarm() {

        val alarmItem = AlarmItem(
            id = 1,
            message = "Alarm has been ringing",
            time = 1700000
        )
        alarmScheduler.schedule(alarmItem)
    }

    private fun scheduleAlarms(alarms: List<Map<String, Any>>) {
        for (alarm in alarms) {
            val id = (alarm["id"] as? Number)?.toInt() ?: continue
            val message = alarm["message"] as? String ?: continue
            val time = (alarm["time"] as? Number)?.toLong() ?: continue

            val alarmItem = AlarmItem(
                id = id,
                message = message,
                time = time
            )
            alarmScheduler.schedule(alarmItem)
        }
    }
}
