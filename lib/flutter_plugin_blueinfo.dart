import 'dart:async';

import 'package:flutter/services.dart';

class FlutterPluginBlueinfo {
  static const MethodChannel _channel =
      const MethodChannel('blue.gds/blue_info');

  String blueState = "";

//  static Future<String> get enableBluetooth async {
//    final blueState = await _channel.invokeMethod('EnableBluetooth');
//    return blueState;
//  }

  static Future<String> get closeBluetooth async {
    final blueState = await _channel.invokeMethod('CloseBluetooth');
    return blueState;
  }

  static Future<String> get blueStae async {
    final blueState = await _channel.invokeMethod('getBlueStateInfo');
    return blueState;
  }
}
