import 'package:flutter/material.dart';
import 'package:flutter_alarm_manager_poc/alarm_actions_screen.dart';
import 'package:flutter_alarm_manager_poc/utils/alarm_method_channel.dart';
import 'package:permission_handler/permission_handler.dart';

class AlarmManagerScreen extends StatelessWidget {
  const AlarmManagerScreen({super.key});

  Future<void> _requestNotificationPermission(BuildContext context) async {
    final status = await Permission.notification.request();
    if (status.isGranted) {
      await AlarmMethodChannel.scheduleAlarm();
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content:
              Text('Notification permission is required to schedule alarms.'),
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Alarm Manager Screen"),
        centerTitle: true,
        actions: [
          IconButton(
            icon: const Icon(Icons.access_alarm),
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
              await _requestNotificationPermission(context);
            },
            child: const Text("Schedule Alarm")),
      ),
    );
  }
}
