import 'dart:developer';
import 'package:flutter/foundation.dart';
import 'package:hive_flutter/hive_flutter.dart';
import '../models/alarm_action.dart';

class DatabaseService {
  static const String alarmBoxName = 'alarm_actions';
  static DatabaseService? _instance;
  late Box<AlarmAction> _alarmBox;

  // Private constructor
  DatabaseService._();

  // Singleton instance getter
  static DatabaseService get instance {
    _instance ??= DatabaseService._();
    return _instance!;
  }

  ValueListenable<Box<AlarmAction>> get alarmBoxListenable =>
      _alarmBox.listenable();

  // Initialize Hive and open the alarm actions box
  Future<void> initializeHive() async {
    try {
      await Hive.initFlutter();
      Hive.registerAdapter(AlarmActionAdapter());
      _alarmBox = await Hive.openBox<AlarmAction>(alarmBoxName);
      log('Hive initialized and box opened successfully.');
    } catch (e) {
      log('Failed to initialize Hive or open box: $e');
    }
  }

  // Add an alarm action to the Hive box
  Future<void> storeAlarmAction(String actionType) async {
    try {
      await _alarmBox.add(
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
  List<AlarmAction> getAllAlarmActions() {
    try {
      var actions = _alarmBox.values;
      log('Retrieved ${actions.length} alarm actions.');
      return actions.toList();
    } catch (e) {
      log('Failed to retrieve alarm actions: $e');
      return [];
    }
  }

  // Clear all alarm actions (if needed)
  Future<void> clearAllAlarmActions() async {
    try {
      await _alarmBox.clear();
      log('All alarm actions cleared.');
    } catch (e) {
      log('Failed to clear alarm actions: $e');
    }
  }
}
