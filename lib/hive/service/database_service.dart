import 'dart:developer';

import 'package:hive_flutter/hive_flutter.dart';

import '../models/alarm_action.dart';

class DatabaseService {
  static const String alarmBoxName = 'alarm_actions';

  // Initialize Hive and open the alarm actions box
  static Future<void> initializeHive() async {
    try {
      await Hive.initFlutter();
      Hive.registerAdapter(AlarmActionAdapter()); //add TypeAdapater
      await Hive.openBox<AlarmAction>(alarmBoxName);
      log('Hive initialized and box opened successfully.');
    } catch (e) {
      log('Failed to initialize Hive or open box: $e');
    }
  }

  // Add an alarm action to the Hive box
  static Future<void> storeAlarmAction(String actionType) async {
    try {
      var alarmBox = Hive.box<AlarmAction>(alarmBoxName);

      await alarmBox.add(
        AlarmAction(actionType, DateTime.now()),
      );
      log('Stored alarm action: $actionType');

      var actions = getAllAlarmActions();
      log('Retrieved ${actions.length} alarm actions.');
    } catch (e) {
      log('Failed to store alarm action: $e');
    }
  }

  // Retrieve all alarm actions from the Hive box
  static List<AlarmAction> getAllAlarmActions() {
    try {
      var alarmBox = Hive.box<AlarmAction>(alarmBoxName);
      var actions = alarmBox.values;
      log('Retrieved ${actions.length} alarm actions.');
      return actions.toList();
    } catch (e) {
      log('Failed to retrieve alarm actions: $e');
      return [];
    }
  }

  // Clear all alarm actions (if needed)
  static Future<void> clearAllAlarmActions() async {
    try {
      var alarmBox = Hive.box<AlarmAction>(alarmBoxName);
      await alarmBox.clear();
      log('All alarm actions cleared.');
    } catch (e) {
      log('Failed to clear alarm actions: $e');
    }
  }
}
