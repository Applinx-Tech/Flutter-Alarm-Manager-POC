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

class AlarmReceiver : BroadcastReceiver() {
    private val CHANNEL_ID = "alarm_channel"
    private val NOTIFICATION_ID = 1

    override fun onReceive(context: Context, intent: Intent?) {
        createNotificationChannel(context)

        val fullScreenIntent = Intent(context, AlarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_USER_ACTION
        }

        val fullScreenPendingIntent = PendingIntent.getActivity(
            context,
            0,
            fullScreenIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.launch_background)
            .setContentTitle("Alarm")
            .setContentText("Your alarm is ringing!")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setFullScreenIntent(fullScreenPendingIntent, true)
            .setOngoing(true) // to make the notification persistent.
            .setAutoCancel(false) //to prevent the notification from being dismissed easily.

        //  make the notification sound repeat until the user responds.
        val notification = notificationBuilder.build().apply {
            // to make the notification sound repeat until the user responds.
            // flags = flags or Notification.FLAG_INSISTENT or Notification.FLAG_NO_CLEAR

        }


        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Cancel any existing notifications
        notificationManager.cancelAll()

        // Show the new notification
        notificationManager.notify(NOTIFICATION_ID, notification)

        // Directly start the full-screen activity
        // context.startActivity(fullScreenIntent)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Alarm Channel"
            val descriptionText = "Channel for Alarm Notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                setBypassDnd(true)
                setShowBadge(true)
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}