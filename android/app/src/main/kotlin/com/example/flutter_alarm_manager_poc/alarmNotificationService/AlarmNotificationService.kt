package com.example.flutter_alarm_manager_poc.alarmNotificationService

import com.example.flutter_alarm_manager_poc.model.AlarmItem

interface AlarmNotificationService {
    fun createNotificationChannel()
    fun showNotification(alarmItem: AlarmItem)
    fun cancelNotification(id: Int)
}