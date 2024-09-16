package com.example.flutter_alarm_manager_poc.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun convertMillisToTime(millis: Long): String {
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = millis
    return formatter.format(calendar.time)
}