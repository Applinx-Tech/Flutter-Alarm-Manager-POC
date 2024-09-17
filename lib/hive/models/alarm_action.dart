import 'package:hive/hive.dart';

part 'alarm_action.g.dart';

@HiveType(typeId: 0) //typeId should be unique for each model
class AlarmAction {
  @HiveField(0)
  final String actionType;
  @HiveField(1)
  final DateTime timestamp;

  AlarmAction(this.actionType, this.timestamp);
}
