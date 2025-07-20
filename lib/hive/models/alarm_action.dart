import 'package:hive/hive.dart';

part 'alarm_action.g.dart';

@HiveType(typeId: 0) //typeId should be unique for each model
class AlarmAction {
  AlarmAction(this.actionType, this.timestamp);

  @HiveField(0)
  final String actionType;
  @HiveField(1)
  final DateTime timestamp;
}
