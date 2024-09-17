import 'package:flutter/material.dart';
import 'package:flutter_alarm_manager_poc/alarm_manager_screen.dart';
import 'package:flutter_alarm_manager_poc/hive/models/alarm_action.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'utils/alarm_method_channel.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Hive.initFlutter();
  Hive.registerAdapter(AlarmActionAdapter()); //add TypeAdapater
  await Hive.openBox<AlarmAction>('alarm_actions');

  AlarmMethodChannel.initialize();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        themeMode: ThemeMode.dark,
        darkTheme: ThemeData(
          brightness: Brightness.dark,
        ),
        theme: ThemeData(
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
          useMaterial3: true,
        ),
        home: const AlarmManagerScreen());
  }
}
