package com.example.flutter_alarm_manager_poc

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.flutter_alarm_manager_poc.receiver.AlarmReceiver
import com.example.flutter_alarm_manager_poc.utils.turnScreenOffAndKeyguardOn
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.util.Calendar

class MainActivity : FlutterActivity() {

    private val CHANNEL = "com.example/alarm_manager"
    private val TAG = "POC"


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        turnScreenOffAndKeyguardOn()

        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            if (call.method == "scheduleAlarm") {
                Log.d(TAG, "Method Channel Invoked,Alarm Shceduling")
                scheduleAlarm()
                result.success(null)
            } else {
                result.notImplemented()
            }
        }
    }

    private fun scheduleAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val triggerTime = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            add(Calendar.SECOND, 10)  // Set alarm 10 seconds from now
        }.timeInMillis

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
    }
}
