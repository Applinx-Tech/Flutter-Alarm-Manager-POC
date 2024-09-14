package com.example.flutter_alarm_manager_poc

import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity(){

    private val CHANNEL = "com.example/alarm_manager"
    private val TAG = "POC"


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "scheduleAlarm") {
                Log.d(TAG,"Method Channel Invoked,Alarm Shceduling")
                result.success(null)
            } else {
                result.notImplemented()
            }
        }
    }
}
