package com.example.flutter_alarm_manager_poc

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.flutter_alarm_manager_poc.alarmNotificationService.AlarmNotificationService
import com.example.flutter_alarm_manager_poc.alarmNotificationService.AlarmNotificationServiceImpl
import com.example.flutter_alarm_manager_poc.alarmScheduler.AlarmScheduler
import com.example.flutter_alarm_manager_poc.alarmScheduler.AlarmSchedulerImpl
import com.example.flutter_alarm_manager_poc.model.AlarmItem
import com.example.flutter_alarm_manager_poc.receiver.AlarmReceiver
import com.example.flutter_alarm_manager_poc.utils.turnScreenOffAndKeyguardOn
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.util.Calendar

class MainActivity : FlutterActivity() {

    private val CHANNEL = "com.example/alarm_manager"
    private val TAG = "POC"

    private lateinit var alarmScheduler: AlarmScheduler


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        alarmScheduler = AlarmSchedulerImpl(this)

        turnScreenOffAndKeyguardOn()

        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            if (call.method == "scheduleAlarm") {
                Log.d(TAG, "Method Channel Invoked,Alarm Scheduling")
                scheduleAlarm()
                result.success(null)
            } else {
                result.notImplemented()
            }
        }
    }

    private fun scheduleAlarm() {

        val alarmItem = AlarmItem(
            id = 1,
            message = "Alarm has been ringing"
        )
        alarmScheduler.schedule(alarmItem)
    }
}
