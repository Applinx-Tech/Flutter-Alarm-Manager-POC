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
  late final Box<AlarmAction> _box;

  @override
  void initState() {
    super.initState();
    _box = Hive.box<AlarmAction>(DatabaseService.alarmBoxName);

    log(_box.values.toString());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Alarm Actions'),
      ),
      body: ValueListenableBuilder(
        valueListenable: _box.listenable(),
        builder: (context, Box<AlarmAction> box, _) {
          final actions = box.values.toList();
          if (actions.isEmpty) {
            return const Center(child: Text('No alarm actions recorded.'));
          }
          return ListView.builder(
            itemCount: actions.length,
            itemBuilder: (context, index) {
              final action = actions[index];
              return ListTile(
                title: Text(action.actionType),
                subtitle: Text(action.timestamp.toString()),
              );
            },
          );
        },
      ),
    );
  }
}
