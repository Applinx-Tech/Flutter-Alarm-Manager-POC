package com.example.flutter_alarm_manager_poc.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.flutter_alarm_manager_poc.R
import com.example.flutter_alarm_manager_poc.utils.turnScreenOnAndKeyguardOff
import io.flutter.embedding.android.FlutterActivity

class AlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        turnScreenOnAndKeyguardOff()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val acceptButton: Button = findViewById(R.id.acceptButton)
        val rejectButton: Button = findViewById(R.id.rejectButton)
        val snoozeButton: Button = findViewById(R.id.snoozeButton)

        acceptButton.setOnClickListener { finish() }
        rejectButton.setOnClickListener { finish() }
        snoozeButton.setOnClickListener {
            // Handle snooze logic
            finish()
        }
    }
}