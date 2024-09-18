import 'package:flutter/material.dart';
import 'package:flutter_alarm_manager_poc/alarm_actions_screen.dart';
import 'package:flutter_alarm_manager_poc/utils/alarm_method_channel.dart';

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
              Navigator.push(context,
                  MaterialPageRoute(builder: (_) => const AlarmActionsScreen()));
            },
          )
        ],
      ),
      body: Center(
        child: ElevatedButton(
            onPressed: () async {
              await AlarmMethodChannel.scheduleAlarm();
            },
            child: const Text("Schedule Alarm")),
      ),
    );
  }
}
