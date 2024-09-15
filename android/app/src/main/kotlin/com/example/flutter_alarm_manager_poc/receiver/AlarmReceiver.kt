package com.example.flutter_alarm_manager_poc.receiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.flutter_alarm_manager_poc.R
import com.example.flutter_alarm_manager_poc.activity.AlarmActivity
import com.example.flutter_alarm_manager_poc.alarmNotificationService.AlarmNotificationService
import com.example.flutter_alarm_manager_poc.alarmNotificationService.AlarmNotificationServiceImpl
import com.example.flutter_alarm_manager_poc.model.AlarmItem

class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent?) {
        val alarmId = intent?.getIntExtra("ALARM_ID", -1) ?: -1
        val message = intent?.getStringExtra("ALARM_MESSAGE") ?: "Alarm!"
        val notificationService: AlarmNotificationService = AlarmNotificationServiceImpl(context)
        notificationService.showNotification(AlarmItem(alarmId, message))
    }
}