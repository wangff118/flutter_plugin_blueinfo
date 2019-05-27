import 'dart:async';

import 'package:flutter/services.dart';

class FlutterPluginBlueinfo {

  static const MethodChannel _channel =
      const MethodChannel('blue.gds/blue_info');

  String blueState="";

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }



  static Future<String> get blueStae async {
    final  blueState =  await _channel.invokeMethod('getBlueStateInfo');
    return blueState;
  }

  }


