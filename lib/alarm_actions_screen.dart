import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter_alarm_manager_poc/hive/models/alarm_action.dart';
import 'package:flutter_alarm_manager_poc/hive/service/database_service.dart';
import 'package:hive_flutter/hive_flutter.dart';

class AlarmActionsScreen extends StatefulWidget {
  const AlarmActionsScreen({super.key});

  @override
  State<AlarmActionsScreen> createState() => _AlarmActionsScreenState();
}

class _AlarmActionsScreenState extends State<AlarmActionsScreen> {
  @override
  void initState() {
    super.initState();

    var actions = DatabaseService.getAllAlarmActions();

    if (actions.isEmpty) {
      log("Empty");
    } else {
      for (var alarm in actions) {
        log('Action: ${alarm.actionType}, Timestamp: ${alarm.timestamp}');
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
