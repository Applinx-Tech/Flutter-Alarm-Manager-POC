import 'package:flutter/material.dart';
import 'package:flutter_alarm_manager_poc/alarm_actions_screen.dart';
import 'package:flutter_alarm_manager_poc/model/alarm_model.dart';
import 'package:flutter_alarm_manager_poc/utils/alarm_method_channel.dart';

import 'hive/service/database_service.dart';

class AlarmManagerScreen extends StatelessWidget {
  const AlarmManagerScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Alarm Manager Screen"),
        centerTitle: true,
        actions: [
          IconButton(
            icon: const Icon(Icons.download),
            onPressed: () {
              Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (_) => const AlarmActionsScreen()));
            },
          )
        ],
      ),
      body: Center(
        child: ElevatedButton(
            onPressed: () async {
              List<AlarmModel> alarms = [
                AlarmModel(
                    id: 1,
                    message: 'Wake up!',
                    time: DateTime.now().add(Duration(minutes: 1))),
                AlarmModel(
                    id: 2,
                    message: 'Time for lunch',
                    time: DateTime.now().add(Duration(minutes: 2))),
              ];

              await AlarmMethodChannel.scheduleAlarms(alarms);
            },
            child: const Text("Schedule Alarm")),
      ),
    );
  }
}
