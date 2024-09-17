import 'dart:developer';

import 'package:flutter/services.dart';

class AlarmMethodChannel {
  static const name = "Flutter";
  static const platform = MethodChannel('com.example/alarm_manager');

  static Future<void> scheduleAlarm() async {
    try {
      await platform.invokeMethod('scheduleAlarm');
    } on PlatformException catch (e) {
      log("Failed to schedule alarm: '${e.message}'.");
    }
  }

  static void initialize() {
    platform.setMethodCallHandler(_handleMethodCall);
  }

  static Future<dynamic> _handleMethodCall(MethodCall call) async {
    switch (call.method) {
      case 'alarmAccepted':
        log(name: name, 'Alarm was accepted');
        // Handle alarm accepted
        // You can call a function or update state here
        break;
      case 'alarmSnoozed':
        log(name: name, 'Alarm was snoozed');
        // Handle alarm snoozed
        // You can call a function or update state here
        break;
      default:
        log('Unrecognized method ${call.method}');
    }
  }
}
