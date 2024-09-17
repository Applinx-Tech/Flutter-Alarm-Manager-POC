// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'alarm_action.dart';

// **************************************************************************
// TypeAdapterGenerator
// **************************************************************************

class AlarmActionAdapter extends TypeAdapter<AlarmAction> {
  @override
  final int typeId = 0;

  @override
  AlarmAction read(BinaryReader reader) {
    final numOfFields = reader.readByte();
    final fields = <int, dynamic>{
      for (int i = 0; i < numOfFields; i++) reader.readByte(): reader.read(),
    };
    return AlarmAction(
      fields[0] as String,
      fields[1] as DateTime,
    );
  }

  @override
  void write(BinaryWriter writer, AlarmAction obj) {
    writer
      ..writeByte(2)
      ..writeByte(0)
      ..write(obj.actionType)
      ..writeByte(1)
      ..write(obj.timestamp);
  }

  @override
  int get hashCode => typeId.hashCode;

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is AlarmActionAdapter &&
          runtimeType == other.runtimeType &&
          typeId == other.typeId;
}
