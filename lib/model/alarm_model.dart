class AlarmModel {
  final int id;
  final String message;
  final DateTime time;

  AlarmModel({required this.id, required this.message, required this.time});



  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'message': message,
      'time': time.millisecondsSinceEpoch,
    };
  }

  static AlarmModel fromJson(Map<String, dynamic> json) {
    return AlarmModel(
      id: json['id'],
      message: json['message'],
      time: DateTime.fromMillisecondsSinceEpoch(json['time']),
    );
  }
}
