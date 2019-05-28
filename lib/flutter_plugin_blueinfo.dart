import 'dart:async';

import 'package:flutter/services.dart';

class FlutterPluginBlueinfo {
  static const MethodChannel _channel =
      const MethodChannel('blue.gds/blue_info');

  String blueState = "";

  static Future<void> get enableBluetooth async {
    await _channel.invokeMethod('EnableBluetooth');
  }

  static Future<void> get closeBluetooth async {
    await _channel.invokeMethod('CloseBluetooth');
  }

  static Future<String> get blueStae async {
    final blueState = await _channel.invokeMethod('getBlueStateInfo');
    return blueState;
  }
}
