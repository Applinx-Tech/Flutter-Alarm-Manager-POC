import 'dart:developer';

import 'package:flutter/services.dart';
import 'package:flutter_alarm_manager_poc/hive/service/database_service.dart';

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
    // var alarmBox = Hive.box<AlarmAction>('alarm_actions');

    switch (call.method) {
      case 'alarmAccepted':
        log(name: name, 'Alarm was accepted');
        //   await alarmBox.add(AlarmAction('accept', DateTime.now()));

        await DatabaseService.instance.storeAlarmAction("accept");

        // Handle alarm accepted
        // You can call a function or update state here
        break;
      case 'alarmSnoozed':
        log(name: name, 'Alarm was snoozed');
        // await alarmBox.add(AlarmAction('snooze', DateTime.now()));

        await DatabaseService.instance.storeAlarmAction("snooze");

        // Handle alarm snoozed
        // You can call a function or update state here
        break;
      default:
        log('Unrecognized method ${call.method}');
    }
  }
}
